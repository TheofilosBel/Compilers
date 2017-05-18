package compiler.semanticAnalysis;

import java.util.LinkedList;
import java.util.List;

import compiler.node.*;
import compiler.semanticAnalysis.Type;
import compiler.semanticAnalysis.Variable;

/*
This is a class to save the info needed for a function
*/
public class FunctionType extends Type {
    
    private String                  rettype;
    private LinkedList<Variable>    argsByRef;
    private LinkedList<Variable>    argsByVall;
    

    public FunctionType(AType rettype, PFparList argList) {
        super();
        this.rettype = rettype.toString();
        this.argsByRef  = new LinkedList<Variable>();
        this.argsByVall = new LinkedList<Variable>();
        
        /* If the func has arguments clone it else make an empty one */
        if (argList instanceof AExistingFparList) {
            
            /* Get all the definitions and place them in the 2 lists depending on the pass method */
            PFparDef fpar_def = null;
            for(int def = 0; def < ((AExistingFparList) argList).getArgs().size() ; def++) {
                
                fpar_def = ((AExistingFparList) argList).getArgs().get(def);
                if (fpar_def instanceof AByvallFparDef) {
                    
                    /* Get the PVariable list from the var def */
                    addAlltoList(((AByvallFparDef) fpar_def).getVarList(), (AType) ((AByvallFparDef) fpar_def).getType(), false);                    
                }
                else if (fpar_def instanceof AByrefFparDef) {
                    
                    /* Get the PVariable list from the var def */
                    addAlltoList(((AByrefFparDef) fpar_def).getVarList(), (AType) ((AByvallFparDef) fpar_def).getType(), true);
                }

            } 
        } 
        else {
            this.argsByRef  = null;
            this.argsByVall = null;
        }
        
        
        /* Print the 2 lists */
        System.out.println("Vars by ref in func");
        for (int vars = 0; vars < argsByRef.size(); vars++) {
            System.out.println("-->Name " + argsByRef.get(vars).name);
            System.out.println("   Type " + argsByRef.get(vars).type);
        }
        
        System.out.println("Vars by vall in func");
        for (int vars = 0; vars < argsByVall.size(); vars++) {
            System.out.println("-->Name " + argsByRef.get(vars).name);
            System.out.println("   Type " + argsByRef.get(vars).type);
        }
        System.out.println();
        
        
        
    }
    
    public void addAlltoList(LinkedList<PVariable> list, AType type, Boolean byref){
        LinkedList<Variable> temp = new LinkedList<Variable>();
        
        
        for(int var=0; var < list.size(); var++) {
           temp.add(new Variable(((AVariable) list.get(var)).getId().toString(), type)); 
        }
        
        /* Add the temp list to the correct list */
        if (byref = true)
            this.argsByRef.addAll(temp);
        else 
            this.argsByVall.addAll(temp);
        
    }
    

    public String getType() {
        return this.rettype;
    }

}