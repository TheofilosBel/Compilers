package compiler.semanticAnalysis;

import compiler.analysis.*;
import compiler.node.*;
import compiler.types.*;
import compiler.exceptions.*;
import compiler.semanticAnalysis.VariableInfo;
import compiler.semanticAnalysis.SymbolTableEntry;
import java.util.Collections;
import java.util.LinkedList;
import java.util.HashMap;

public class SemanticAnalysis extends DepthFirstAdapter {

    int indentation = 0;
    SymbolTable symbolTable; /* The structure of the symbol table */
    private HashMap<Node, Type> exprTypes; /* A structure that maps every sablecc generated Node to a type */

    /*
     * The semantic analysis phase starts here
     * We create the symbol table and in its first scope we insert
     * the Grace built in library functions so that they can be
     * called by any function in the program (global scope)
     */
    public SemanticAnalysis() {
        /* Create a new symbol table */
        System.out.println("Constructing Symbol_table");
        this.symbolTable = new SymbolTable();
        
        /* Add the first scope that will hold the built in library functions */
        this.symbolTable.enter();
        
        LinkedList<VariableInfo> argList; /* A list containing the arguments to each function */
        LinkedList<String> passBy;    /* A list containing the pass method (by reference / by value) of each argument */
        SymbolTableEntry data;        /* An object that is inserted in the symbol table for each function */

        /* fun puti (n : int) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("puti", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun putc (c : char) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("c", "char", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("putc", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun puts (ref s : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("s", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("puts", data);
        data    = null;
        argList = null;
        passBy  = null;
    
        /* fun geti () : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* No arguments to be added to the list */
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("geti", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun getc () : char */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* No arguments to be added to the list */
        data = new SymbolTableEntry(new FunctionInfo(new String("char"), argList, passBy));
        this.symbolTable.insert("getc", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun gets (n : int, ref s : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("n", "int", false));
        passBy.add("val");
        argList.add(new VariableInfo("s", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("gets", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun abs (n : int) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("abs", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun ord (c : char) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("c", "char", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("ord", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun chr (n : int) : char */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("char"), argList, passBy));
        this.symbolTable.insert("chr", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strlen (ref s : char[]) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("s", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("strlen", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strcmp (ref s1, s2 : char[]) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("s1", "char", true));
        passBy.add("ref");
        argList.add(new VariableInfo("s2", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("strcmp", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun strcpy (ref trg, src : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("trg", "char", true));
        passBy.add("ref");
        argList.add(new VariableInfo("src", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("strcpy", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strcat (ref trg, src : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("trg", "char", true));
        passBy.add("ref");
        argList.add(new VariableInfo("src", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("strcat", data);
        data    = null;
        argList = null;
        passBy  = null;
    }

    /* For debugging reasons */
    private void addIndentationLevel() {
        indentation++;
    }

    private void removeIndentationLevel() {
        indentation--;
    }

    private void printIndentation() {
        System.out.print(String.join("", Collections.nCopies(indentation, "   ")));
    }

    private void indentNprint(String msg) {
        printIndentation();
        System.out.println(msg);
    }
    /*-----------------------*/
    
    
    @Override
    public void inAFuncDec(AFuncDec node) {
        
        /* Create a SymbolTableEntry object to pass to the insert function */
        SymbolTableEntry data = new SymbolTableEntry(new FuncDecInfo((PDataType) node.getRetType(),
                                node.getFplist(), node.getId().toString()));
        this.symbolTable.insert(node.getId().toString(), data);

        addIndentationLevel();
    }

    @Override
    public void inAFuncDef(AFuncDef node) {
        /* Check for a function declaration in the same nesting */
        SymbolTableEntry funcDecInfo = this.symbolTable.lookup(node.getId().toString());
        
        if (funcDecInfo != null) {
            /*
             * If a function definition or variable with the same name
             * as the function in node was found then raise an exception
             */
            if (!(funcDecInfo.getType() instanceof FuncDecInfo)) {
                throw new SemanticAnalysisException("Error: Function with name " + node.getId().toString() +
                                                    " can't be used, name already in use");
            }
            else if(((FuncDecInfo) funcDecInfo.getType()).getFuncDefined() == true){
                /* In case the function declaration was already matched with another function definition */
                throw new SemanticAnalysisException("Error: Function with name " + node.getId().toString() +
                                                    " can't be used, name already in use");
            }

            /*
             * A non matched declaration has been found
             * Update its flag to indicate that it has been matched
             */
            ((FuncDecInfo) funcDecInfo.getType()).setFuncDefined(true); 
        }
        else if ((this.symbolTable.getIsMainDefined()) == true) {
            /*
             * If we have a non matched function definition (except for main)
             * add a declaration to the current scope, before making a new scope,
             * so that it will be visible to function calls in the new scope
             */
            FuncDecInfo type = new FuncDecInfo((PDataType) node.getRetType(),
                               node.getFplist(), node.getId().toString());
            SymbolTableEntry data = new SymbolTableEntry(type);
            
            /* Function is already matched because it only has a definition */
            type.setFuncDefined(true);

            this.symbolTable.insert(node.getId().toString(), data);
        }

        /* Check if this is the definition of the main function */
        if ((this.symbolTable.getIsMainDefined()) == false) {
            /* The main function should have no arguments and returns nothing */
            if (node.getFplist() instanceof AExistingFparList) {
                throw new TypeCheckingException("Error: Main should have no parameters");
            }

            if (!(((PDataType) node.getRetType()).toString()).equals(new String("nothing "))) {                
                throw new TypeCheckingException("Error: Main should return nothing");
            }

            this.symbolTable.setIsMainDefined();

            /* Create the HashMap for the type checking */
            exprTypes = new HashMap<>();
        }

        /* Create the info for the function definition*/
        FuncDefInfo funcDefInfo = new FuncDefInfo((PDataType) node.getRetType(),
                node.getFplist(), node.getId().toString());
         
        /* Check equivalence with the function declaration found and before.
         * First on arguments passed by reference */
        if (funcDeclIn)
        
        
        
        /* In every new func_def we create a new scope */
        this.symbolTable.enter();

        /* Create a SymbolTableEntry object to pass to the insert function */
        SymbolTableEntry data = new SymbolTableEntry(funcDefInfo);

        /* Insert the function definition */
        this.symbolTable.insert(node.getId().toString(), data);

        /* Insert the function's arguments to the symbol table - First those passed by reference) */
        for (int var = 0;  var < ((FunctionInfo) data.getType()).getArgsByRef().size(); var++) {
            this.symbolTable.insert(((FunctionInfo) data.getType()).getArgsByRef().get(var).getName(),
                new SymbolTableEntry(new VariableInfo(((FunctionInfo) data.getType()).getArgsByRef().get(var))));
        }

        /* Insert the function's arguments to the symbol table - Now those passed by value) */
        for (int var = 0; var < ((FunctionInfo) data.getType()).getArgsByVal().size(); var++) {
            this.symbolTable.insert(((FunctionInfo) data.getType()).getArgsByVal().get(var).getName(),
                new SymbolTableEntry(new VariableInfo(((FunctionInfo) data.getType()).getArgsByVal().get(var))));
        }

        addIndentationLevel();
    }

    public void outAFuncDef(AFuncDef node) {
        /* When exiting from a function exit from the current scope too */
        this.symbolTable.exit();
        removeIndentationLevel();
    }

    @Override
    public void inAVarDef(AVarDef node) {
        indentNprint("In variable DEfinition");
        
        /* Get the type of the variables in the current definition */
        PType type = (PType) ((AType) node.getType()).clone();

        
        /* Extract every variable from a multi-variable definition and save their types */
        for (int varnum = 0; varnum < node.getIdList().size(); varnum++) {
            /* Add all the variables in the symbol table */
            VariableInfo v = new VariableInfo(node.getIdList().get(varnum).toString(), (AType) type);
            SymbolTableEntry data = new SymbolTableEntry();

            if (this.symbolTable.insert(node.getIdList().get(varnum).toString(), data) == false){
                throw new SemanticAnalysisException("Error Conflicting types : name \"" + node.getIdList().get(varnum).toString() + "\" already existis");
            }
    
            /* Print each variable */
            indentNprint("Name :" + v.getName());
            indentNprint("Type :" + v.getType());
            indentNprint("Int ?:" + v.getType().isInt());
        }
        
    }

    @Override
    public void outAIntExpr(AIntExpr node) {
        String integerString = node.getIntConst().toString();

        /* Convert the string to an integer */
        try {
            Integer.parseInt(integerString);
        }
        catch (NumberFormatException e) {
            int line = node.getIntConst().getLine();
            int column = node.getIntConst().getPos();
            String error = "Error in line " + line + " column " + column +
                            ":\ninvalid integer constant " + integerString;
            throw new TypeCheckingException(error);
        }

        exprTypes.put(node, BuiltInType.Int);
    }

    public void outACharExpr(ACharExpr node) {}

    @Override
    public void outAAddExpr(AAddExpr node) {
        /* Type Check for constants */
        if (node.getL() instanceof AIntExpr) {            
            if (node.getR() instanceof AIntExpr) {
                /* Set the time of the add expr to int */
                AType newType = new AType((PDataType) new AIntDataType(new TKwInt()),
                                          (PArrayDec) new ANotExistingArrayDec()     );
            }
            else 
                throw new TypeCheckingException("Error: invalid type of variable " + node.getR().toString() + " in ");
        }
        /* Type Check for expr */
        else
            throw new TypeCheckingException("Error: invalid type of variable " + node.getL().toString() + " in ");

        /* Print type */
    }

    @Override
    public void outAAssignStmt(AAssignStmt node){
        /* Type check if we assing something to a var */
        if (node.getLvalue() instanceof AIdLvalue) {
            AIdLvalue lvalue = (AIdLvalue) node.getLvalue();
            SymbolTableEntry varInfo = this.symbolTable.lookup(lvalue.getId().toString());
            
            /* If it's not declared */
            if (varInfo == null) {
                System.out.println("Error Undefined variable : \"" + lvalue.getId() + "\"");
                System.exit(-1);
            } 
            else {
                
                /* Type check */
                Info varType  = varInfo.getType();
                //Type exprType = new Type(node.getExpr()); 

                /* Equivalent check */
            }
        }

        System.out.println("Edw mpainw omws");
    }

    @Override
    public void inABlockStmt(ABlockStmt node){
       //System.out.println("Edw omws 8a mpw");        
    }

}