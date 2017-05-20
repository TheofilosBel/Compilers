package compiler.semanticAnalysis;

import java.util.LinkedList;

import compiler.node.*;
import compiler.types.*;
import compiler.exceptions.*;
import compiler.semanticAnalysis.Info;

/*
 * Class to save the info needed for every function
 */
public class FunctionInfo extends Info {
    
    private Type returnType; /* Return type of the function */

    private LinkedList<VariableInfo> argsByRef; /* List of arguments passed by reference to the function */
    private LinkedList<VariableInfo> argsByVal; /* List of arguments passed by value to the function */

    /*
     * Constructs a FunctionInfo object
     *
     * @returnType Return type of the function
     * @argList List of arguments passed to the function
     * @name Name of the function
     */
    public FunctionInfo(PDataType returnType, PFparList argList, String name) {
        /* Find ans save the return type of the function */
        if (returnType instanceof ANothDataType) {
            this.returnType = new BuiltInType("nothing ");
        }
        else if (returnType instanceof AIntDataType) {
            this.returnType = new BuiltInType("int ");
        }
        else if (returnType instanceof ACharDataType) {
            this.returnType = new BuiltInType("char ");
        }

        /* Create the empty argument lists */
        this.argsByRef = new LinkedList<VariableInfo>();
        this.argsByVal = new LinkedList<VariableInfo>();
        
        /*
         * Check if there are any arguments passed to the function
         * and save them in the lists based on their pass method
         */
        if (argList instanceof AExistingFparList) {
            PFparDef fpar_def = null;

            for(int def = 0; def < ((AExistingFparList) argList).getArgs().size(); def++) {
                fpar_def = ((AExistingFparList) argList).getArgs().get(def);

                /* Get the pass method of the argument */
                if (fpar_def instanceof AByValFparDef) {
                    System.out.println("By val");

                    /* If an array has been passed by value raise an exception */
                    if (((AType) ((AByValFparDef) fpar_def).getType()).getArrayDec() instanceof AExistingArrayDec) {
                        throw new TypeCheckingException("Error: passing array by value as argument in function " + name);
                    }

                    /* Get every variable in the list of a multi-variable definition */
                    addAlltoList(((AByValFparDef) fpar_def).getIdList(), (AType) ((AByValFparDef) fpar_def).getType(), false);                    
                }
                else if (fpar_def instanceof AByRefFparDef) {
                    System.out.println("By ref");
                    
                    /* Get every variable in the list of a multi-variable definition */
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

    /*
     * Constructs a FunctionInfo object
     * This constructor is used for the grace built in library functions only
     *
     * @returnType Return type of the function
     * @argList List of arguments passed to the function
     * @passBy List of the pass method of every argument in argList
     */
    public FunctionInfo(String returnType, LinkedList<VariableInfo> argList, LinkedList<String> passBy) {
        this.returnType = new BuiltInType(returnType);
        this.argsByRef  = new LinkedList<VariableInfo>();
        this.argsByVal  = new LinkedList<VariableInfo>();

        /* Get all the arguments and place them in the two lists depending on the pass method */
        for (int var = 0; var < argList.size(); var++) {
            if (passBy.get(var) == "val") {
                System.out.println("In by val");
                this.argsByVal.add(argList.get(var));
            } else {
                System.out.println("In by ref");
                this.argsByRef.add(argList.get(var));
            }
        }   
    }

    /*
     * Adds every variable passed in the list argument to the right list based on its pass method
     *
     * @list List of variables to add to argsByRef or argsByVal
     * @type Type of the variables - It is the same for every variable since
     *       they have been extracted from a multi-variable definition
     * @byRef Boolean that indicates whether the variables were passed by reference or not
     */
    public void addAlltoList(LinkedList<TId> list, AType type, Boolean byRef){
        LinkedList<VariableInfo> temp = new LinkedList<VariableInfo>();
        
        /* Create a new VariableInfo object out of every variable in the list */
        System.out.println("Making the table");
        for (int var = 0; var < list.size(); var++) {
            System.out.println(list.get(var).toString() + type);
            temp.add(new VariableInfo(list.get(var).toString(), type)); 
        }
        
        /* Add the temp list to the correct list */
        if (byRef == true)
            this.argsByRef.addAll(temp);
        else
            this.argsByVal.addAll(temp);
    }
    
    /* Returns a list of the arguments that have been passed by reference */
    public LinkedList<VariableInfo> getArgsByRef() {
        return this.argsByRef;
    }
    
    /* Returns a list of the arguments that have been passed by value */
    public LinkedList<VariableInfo> getArgsByVal() {
        return this.argsByVal;
    }

    /* Returns the return type of the function */
    public Type getType() {
        return this.returnType;
    }

}