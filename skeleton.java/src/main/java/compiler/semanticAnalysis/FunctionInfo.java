package compiler.semanticAnalysis;


import java.util.LinkedList;

import compiler.node.*;
import compiler.types.*;
import compiler.exceptions.*;
import compiler.semanticAnalysis.Info;

/*
This is a class to save the info needed for a function
*/
public class FunctionInfo extends Info {
    
    private Type                     rettype;
    private LinkedList<VariableInfo> argsByRef;
    private LinkedList<VariableInfo> argsByVal;
    
    public FunctionInfo(PDataType rettype, PFparList argList, String name) {
        
        if (rettype instanceof ANothDataType) {
            this.rettype   = new BuiltInType("nothing");
        }
        else if (rettype instanceof AIntDataType) {
            this.rettype   = new BuiltInType("int");
        }
        else if (rettype instanceof ACharDataType) {
            this.rettype   = new BuiltInType("char");
        } 
        
        
        this.argsByRef = new LinkedList<VariableInfo>();
        this.argsByVal = new LinkedList<VariableInfo>();
        
        /* If the func has arguments clone it else make an empty one */
        if (argList instanceof AExistingFparList) {
            
            /* Get all the definitions and place them in the 2 lists depending on the pass method */
            PFparDef fpar_def = null;
            for(int def = 0; def < ((AExistingFparList) argList).getArgs().size() ; def++) {
                
                fpar_def = ((AExistingFparList) argList).getArgs().get(def);
                if (fpar_def instanceof AByValFparDef) {
                    
                    System.out.println("By val");
                    
                    /* In case we have any array_dec by vall we throw exception */
                    if ( ((AType) ((AByValFparDef) fpar_def).getType()).getArrayDec() instanceof AExistingArrayDec) {
                        throw new TypeCheckingException("Error: passing by val an array as argument in " + name);
                    }
                    
                    
                    /* Get the PVariable list from the var def */
                    addAlltoList(((AByValFparDef) fpar_def).getIdList(), (AType) ((AByValFparDef) fpar_def).getType(), false);                    
                }
                else if (fpar_def instanceof AByRefFparDef) {
                    
                    System.out.println("By ref");
                    
                    /* Get the PVariable list from the var def */
                    addAlltoList(((AByRefFparDef) fpar_def).getIdList(), (AType) ((AByRefFparDef) fpar_def).getType(), true);
                }

            } 
        }
        
        /* Print the 2 lists */        
        System.out.println("Vars by ref in func");
        for (int vars = 0; vars < argsByRef.size(); vars++) {
            System.out.println("-->Name " + argsByRef.get(vars).getName());
            System.out.println("   Type " + argsByRef.get(vars).getType());
        }

        System.out.println("Vars by val in func");
        for (int vars = 0; vars < argsByVal.size(); vars++) {
            System.out.println("-->Name " + argsByVal.get(vars).getName());
            System.out.println("   Type " + argsByVal.get(vars).getType());
        }

        System.out.println();
    }

    public FunctionInfo(String rettype, LinkedList<VariableInfo> arglist, LinkedList<String> passBy) {
        this.rettype   = new BuiltInType(rettype);
        this.argsByRef = new LinkedList<VariableInfo>();
        this.argsByVal = new LinkedList<VariableInfo>();

        /* Get all the arguments and place them in the two lists depending on the pass method */
        for (int var = 0; var < arglist.size(); var++) {
            if (passBy.get(var) == "val") {
                System.out.println("In by val");
                this.argsByVal.add(arglist.get(var));
            } else {
                this.argsByRef.add(arglist.get(var));
            }
        }   
    }

    public void addAlltoList(LinkedList<TId> list, AType type, Boolean ByRef){
        LinkedList<VariableInfo> temp = new LinkedList<VariableInfo>();
        
        System.out.println("Makeing the table");
        for(int var = 0; var < list.size(); var++) {
            System.out.println(list.get(var).toString() + type);
            temp.add(new VariableInfo(list.get(var).toString(), type)); 
        }
        
        /* Add the temp list to the correct list */
        if (ByRef == true)
            this.argsByRef.addAll(temp);
        else
            this.argsByVal.addAll(temp);
    }
    
    /* Getter Functions */
    public LinkedList<VariableInfo> getArgsByRef() {
        return this.argsByRef;
    }
    
    public LinkedList<VariableInfo> getArgsByVal() {
        return this.argsByVal;
    }
   
    public Type getType() {
        return this.rettype;
    }

}