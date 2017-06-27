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
    
    private TId  name;
    private Type returnType; /* Return type of the function */
    private int nestingLevel;

    private LinkedList<VariableInfo> arguments; /* List of arguments passed to the function*/
    private LinkedList<String> passedBy;        /* List of Strings that show the way the arguments where passed by */

    /*
     * Constructs a FunctionInfo object
     *
     * @returnType Return type of the function
     * @argList List of arguments passed to the function
     * @name Name of the function
     */
    public FunctionInfo(PDataType returnType, PFparList argList, TId name, int nesting) {
        this.name = (TId) name.clone();
        this.nestingLevel = nesting;

        /* Find and save the return type of the function */
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
        this.arguments = new LinkedList<VariableInfo>();
        this.passedBy  = new LinkedList<String>();

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
                    /* If an array has been passed by value raise an exception */
                    if (((AType) ((AByValFparDef) fpar_def).getType()).getArrayDec() instanceof AExistingArrayDec) {
                        throw new TypeCheckingException(name.getLine(), name.getPos(),
                                "passing array by value as argument in function " + name.toString());
                    }

                    /* Get every variable in the list of a multi-variable definition */
                    addAlltoList(((AByValFparDef) fpar_def).getIdList(), (AType) ((AByValFparDef) fpar_def).getType(), false);                    
                }
                else if (fpar_def instanceof AByRefFparDef) {
                    /* Get every variable in the list of a multi-variable definition */
                    addAlltoList(((AByRefFparDef) fpar_def).getIdList(), (AType) ((AByRefFparDef) fpar_def).getType(), true);
                }
            } 
        }
    }

    /*
     * Constructs a FunctionInfo object
     * This constructor is used for the grace built in library functions only
     *
     * @returnType Return type of the function
     * @argList List of arguments passed to the function
     * @passBy List of the pass method of every argument in argList
     */
    public FunctionInfo(String returnType, LinkedList<VariableInfo> argList, LinkedList<String> passBy, int nesting) {
        this.returnType = new BuiltInType(returnType);
        this.arguments  = new LinkedList<VariableInfo>();
        this.passedBy   = new LinkedList<String>();
        this.nestingLevel = nesting;

        /* Get all the arguments and their passBy method */
        this.arguments.addAll(argList);
        this.passedBy.addAll(passBy);
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
        /* Create a new VariableInfo object out of every variable in the list */
        //System.out.println("Making the Argument list with types " + type.toString());
        for (int var = 0; var < list.size(); var++) {
            //System.out.println(list.get(var).toString() + type);

             /* Add the pass by method and the arguments */
            if (byRef == true) {
                this.passedBy.add("ref");
                this.arguments.add(new VariableInfo(list.get(var), type, "ref"));
            }
            else {
                this.passedBy.add("val");
                this.arguments.add(new VariableInfo(list.get(var), type, "val"));
            }
        }
    }

    /*
     * Check if 2 FunctionInfos hold the same information about  
     * their arguments and their return types. In case of error 
     * the function throws a runtime exception.
     */
    public void isEquivWith(FunctionInfo funcInfo) {
        /* Check that the arguments */
        if (this.getArguments().size() == funcInfo.getArguments().size() && funcInfo.getArguments().size() > 0) {
            for (int arg = 0; arg < funcInfo.getArguments().size(); arg++) {
                /* Get the info */
                TId name = funcInfo.getName();
                VariableInfo defvar  = funcInfo.getArguments().get(arg);
                VariableInfo declvar = this.getArguments().get(arg);
 
                /* If the args are not equivalent throw exception */
                if (!(funcInfo.getArguments().get(arg).getType().isEquivWith(this.getArguments().get(arg).getType()) == 0)) {
                    throw new TypeCheckingException(defvar.getName().getLine(), defvar.getName().getPos(),
                            "In function \"" + name.getText() + "\": passing variable: \"" + defvar.getName().getText()
                            + "\" with type " + defvar.getType().toString() +
                            " but declared type is " + declvar.getType().toString());
                }

                /* If the pass by methods are not the same throw exception */
                if (!funcInfo.getPassByMethods().get(arg).equals(this.getPassByMethods().get(arg))) {
                    throw new TypeCheckingException(defvar.getName().getLine(), defvar.getName().getPos(),
                            "In function \"" + name.getText() + "\": passing variable: \"" + defvar.getName().getText()
                            + "\" by " + funcInfo.getPassByMethods().get(arg) +
                            " but in declaration it is passed by " +  this.getPassByMethods().get(arg));
                }
            }
        }
        else if (this.getArguments().size() != funcInfo.getArguments().size()) {
            /* In case of miss matching arguments size */
            TId name = funcInfo.getName();
            throw new TypeCheckingException(name.getLine(), name.getPos(),
                    "In function \"" + name.getText() + "\":\n"
                    + "Not matching arguments number between function declaration and function definition.");
        }

        if (!(funcInfo.getType().isEquivWith(this.getType()) == 0)) {
            TId name = funcInfo.getName();
            throw new TypeCheckingException(name.getLine(), name.getPos(),
                    "In function \"" + name.getText() + "\" returned type is declared as " + this.getType()
                    + " but defined as " + funcInfo.getType());
        }
    }

    /* Returns a list of the arguments that have been passed by reference */
    public LinkedList<VariableInfo> getArguments() {
        return this.arguments;
    }

    /* Returns a list of the arguments' passed by methods */
    public LinkedList<String> getPassByMethods() {
        return this.passedBy;
    }

    /* Returns "R" for an argument that has been passed by reference, or "V" otherwise */
    public String paramMode(int n) {
        if (n > this.getPassByMethods().size()) {
            return (new String("-"));
        }

        if ((this.getPassByMethods().get(n-1)).equals(new String("ref"))) {
            return (new String("R"));
        }
        else {
            return (new String("V"));
        }
    }

    /* Returns the return type of the function */
    public Type getType() {
        return this.returnType;
    }

    public TId getName() {
        return this.name;
    }

    /* Returns the nesting leve */
    public int getNestingLevel() {
        return this.nestingLevel;
    }

    @Override
    public boolean isFunctionInfo() {
        return true;
    }

}