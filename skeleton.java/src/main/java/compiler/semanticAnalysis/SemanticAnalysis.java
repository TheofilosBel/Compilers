package compiler.semanticAnalysis;

import compiler.analysis.*;
import compiler.node.*;
import compiler.exceptions.*;
import compiler.semanticAnalysis.Type;
import compiler.semanticAnalysis.Variable;
import compiler.semanticAnalysis.SymbolTableEntry;
import java.util.Collections;
import java.util.LinkedList;

public class SemanticAnalysis extends DepthFirstAdapter {

    int indentation = 0;
    SymbolTable symbolTable; /* The structure of the symbol table */

    /*
    The semantic analysis phase starts here
    We create the symbol table and in its first scope we insert
    the Grace built in library functions so that they can be
    called by any function in the program (global scope)
    */
    public SemanticAnalysis() {
        /* Create a new symbol table */
        System.out.println("Constructing Symbol_table");
        this.symbolTable = new SymbolTable();
        
        /* Add the first scope that will hold the built in library functions */
        this.symbolTable.enter();
        
        LinkedList<Variable> argList; /* A list containing the arguments to each function */
        LinkedList<String> passBy;    /* A list containing the pass method (by reference / by value) of each argument */
        SymbolTableEntry data;        /* An object that is inserted in the symbol table for each function */

        /* fun puti (n : int) : nothing */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new Variable("n", "int"));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionType(new String("nothing"), argList, passBy));
        this.symbolTable.insert("puti", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun putc (c : char) : nothing */
        argList = new LinkedList<Variable>();
        passBy = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new Variable("c", "char"));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionType(new String("nothing"), argList, passBy));
        this.symbolTable.insert("putc", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun puts (ref s : char[]) : nothing */
        argList = new LinkedList<Variable>();
        passBy = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new Variable("s", "char"));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionType(new String("nothing"), argList, passBy));
        this.symbolTable.insert("puts", data);
        data    = null;
        argList = null;
        passBy  = null;
    
        /* fun geti () : int */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* No arguments to be added to the list */
        data = new SymbolTableEntry(new FunctionType(new String("int"), argList, passBy));
        this.symbolTable.insert("geti", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun getc () : char */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* No arguments to be added to the list */
        data = new SymbolTableEntry(new FunctionType(new String("char"), argList, passBy));
        this.symbolTable.insert("getc", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun gets (n : int, ref s : char[]) : nothing */
        argList = new LinkedList<Variable>();
        passBy = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new Variable("n", "int"));
        passBy.add("val");
        argList.add(new Variable("s", "char"));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionType(new String("nothing"), argList, passBy));
        this.symbolTable.insert("gets", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun abs (n : int) : int */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new Variable("n", "int"));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionType(new String("int"), argList, passBy));
        this.symbolTable.insert("abs", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun ord (c : char) : int */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new Variable("c", "char"));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionType(new String("int"), argList, passBy));
        this.symbolTable.insert("ord", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun chr (n : int) : char */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new Variable("n", "int"));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionType(new String("char"), argList, passBy));
        this.symbolTable.insert("chr", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strlen (ref s : char[]) : int */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new Variable("s", "char"));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionType(new String("int"), argList, passBy));
        this.symbolTable.insert("strlen", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strcmp (ref s1, s2 : char[]) : int */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new Variable("s1", "char"));
        passBy.add("ref");
        argList.add(new Variable("s2", "char"));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionType(new String("int"), argList, passBy));
        this.symbolTable.insert("strcmp", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun strcpy (ref trg, src : char[]) : nothing */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new Variable("trg", "char"));
        passBy.add("ref");
        argList.add(new Variable("src", "char"));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionType(new String("nothing"), argList, passBy));
        this.symbolTable.insert("strcpy", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strcat (ref trg, src : char[]) : nothing */
        argList = new LinkedList<Variable>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new Variable("trg", "char"));
        passBy.add("ref");
        argList.add(new Variable("src", "char"));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionType(new String("nothing"), argList, passBy));
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
        SymbolTableEntry data = new SymbolTableEntry(new FuncDecType((AType) node.getRetType(), node.getFplist(), node.getId().toString()));
        this.symbolTable.insert(node.getId().toString(), data);

        addIndentationLevel();
    }
    

    @Override
    public void inAFuncDef(AFuncDef node) {
        /* Check for a function declaration in the same nesting */
        SymbolTableEntry result = this.symbolTable.lookup(node.getId().toString());
        if (result != null) {
            
            /* If a function definition or variable with the same name as the function in node found raise exception */
            if(!(result.getType() instanceof FuncDecType)) {

                throw new SemanticAnalysisException("Error: Function with name " + node.getId().toString() + " cat be used, name already in use");
            }
            else if(((FuncDecType) result.getType()).getFuncDefined() == true){
                /* Or the function declaration was already matched with another function definition */
                throw new SemanticAnalysisException("Error: Function with name " + node.getId().toString() + " cat be used, name already in use");
            }
            
            /* Declaration of function found (and not matched), update its flag (matched definition with declaration) */
            ((FuncDecType) result.getType()).setFuncDefined(true);
            

            /* Check equivalence */
            
        }
        else if ((this.symbolTable.getIsMainDefined()) == true) {
            
            /* In case we have a non matched function definition (except main) add a declaration 
             * to the scope we are, before making a new scope , so it will be visible
             * to function calls in this scope. */
            
            /* Create a SymbolTableEntry object to pass to the insert function */
            FuncDecType type = new FuncDecType((AType) node.getRetType(),
                    node.getFplist(), node.getId().toString());
            SymbolTableEntry data = new SymbolTableEntry(type);
            
            /* Function is already matched because it has only a definition */
            type.setFuncDefined(true);
            
            this.symbolTable.insert(node.getId().toString(), data);
        }
        
        
        /* Check if this is the definition of the main function */
        if ((this.symbolTable.getIsMainDefined()) == false) {
            
            /* The main function should have no arguments and returns nothing */
            if (node.getFplist() instanceof AExistingFparList) {
                throw new TypeCheckingException("Main should have no parameters");
            }

            if (!(((AType) node.getRetType()).toString()).equals(new String("nothing "))) {                
                throw new TypeCheckingException("Main should return nothing");
            }

            System.out.println("Main OK");
            this.symbolTable.setIsMainDefined();
        }
        

        /* In every new func_def we create a new scope */
        this.symbolTable.enter();
        
        /* Create a SymbolTableEntry object to pass to the insert function */
        SymbolTableEntry data = new SymbolTableEntry(new FuncDefType((AType) node.getRetType(),
                                node.getFplist(), node.getId().toString()));
        
        /* Insert the function definition */
        this.symbolTable.insert(node.getId().toString(), data);
        
        
        /* Insert the function's arguments to the symbol table (fist byRef) */
        for (int var = 0;  var < ((FunctionType) data.getType()).getArgsByRef().size(); var++) {
            this.symbolTable.insert(
                    ((FunctionType) data.getType()).getArgsByRef().get(var).getName(),
                    new SymbolTableEntry(new VariableType(((FunctionType) data.getType()).getArgsByRef().get(var))) 
                    );
            
        }
        
        /* Insert the function's arguments to the symbol table (now byVal) */
        for (int var = 0; var < ((FunctionType) data.getType()).getArgsByVal().size(); var++) {
            this.symbolTable.insert(
                    ((FunctionType) data.getType()).getArgsByVal().get(var).getName(),
                    new SymbolTableEntry(new VariableType(((FunctionType) data.getType()).getArgsByVal().get(var))) 
                    );
            
        }
        
        addIndentationLevel();
    }

    public void outAFuncDef(AFuncDef node) {
        /* When we leave from a function we exit its scope */
        this.symbolTable.exit();
        removeIndentationLevel();
    }

    @Override
    public void inAVarDef(AVarDef node) {
        AVariable var = null;
        /* Get the type of the variables in the current definition */
        PType type = (PType) ((AType) node.getType()).clone();

        /* Extract multiple variables from a single definition and save their types */
        for (int varnum = 0; varnum < node.getVarList().size(); varnum++) { 
            var = (AVariable) node.getVarList().get(varnum);
            var.setType(type);

            System.out.println(var.getId().toString() + "  " + type.toString());

            /* Add all the variables in the symbol table */
            SymbolTableEntry data = new SymbolTableEntry(new VariableType(var.getId().toString(), (AType) type));

            if (this.symbolTable.insert(var.getId().toString(), data) == false){
                throw new SemanticAnalysisException("Error Conflicting types : name \"" + var.getId() + "\" already existis");
            }
            
        }
    }

    
    
    
    
    @Override
    public void outAIntExpr(AIntExpr node){
        
        /* Make the type (it's integer) */
        AIntDataType newType = new AIntDataType(new TKwInt());
        node.setDataType((PDataType) newType);
    }
    
    public void outACharExpr(ACharExpr node){
        
        /* Make the type (it's integer) */
        ACharDataType newType = new ACharDataType(new TKwChar());
        node.setDataType((PDataType) newType);
    }
    
    
    
    @Override
    public void outAOpExpr(AOpExpr node){
        
        /* Type Check */
        if (node.getL() instanceof AIntExpr) {            
            if (node.getR() instanceof AIntExpr) {
                
                /* Set the time of the add expr to int */
                AType newType = new AType((PDataType) new AIntDataType(new TKwInt()),
                                          (PArrayDec) new ANotExistingArrayDec()     );
                node.setType((PType) newType);
            }
            else 
                throw new TypeCheckingException("Error: invalid type of variable " +
                                                node.getR().toString() +
                                                " in "
                                               );
        }
        else 
            throw new TypeCheckingException("Error: invalid type of variable " +
                                            node.getL().toString() +
                                            " in "
                                           );
        
        
        /* Print type */
        if (node.getType() != null)
            System.out.println("Type of "+ node.getOperator().toString() +"expr is " + node.getType().toString());
        
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
                Type varType  = varInfo.getType();
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