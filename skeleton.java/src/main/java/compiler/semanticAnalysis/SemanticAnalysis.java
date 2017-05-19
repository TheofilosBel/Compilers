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

    public SemanticAnalysis() {
        System.out.println("Constructing Symbol_table");
        this.symbolTable = new SymbolTable();
        
        /* Add the first scope that will have the built in func */
        this.symbolTable.enter();
        
        /* Add func : fun puti (n : int) : nothing; */
        LinkedList<Variable> arglist = new LinkedList<Variable>();
        LinkedList<String> passby = new LinkedList<String>();
        arglist.add(new Variable("n", "int"));
        passby.add("val");
        SymbolTableEntry data = new SymbolTableEntry(new FunctionType("nothing", arglist, passby));
        this.symbolTable.insert("puti", data);
        data = null;
        arglist =null;
        passby = null;
        
        /* Add func : fun putc (c : char) : nothing; */
        arglist = new LinkedList<Variable>();
        passby = new LinkedList<String>();
        arglist.add(new Variable("c", "char"));
        passby.add("val");
        data = new SymbolTableEntry(new FunctionType("nothing", arglist, passby));
        this.symbolTable.insert("putc", data);
        data = null;
        arglist =null;
        passby = null;
        
        /* Add func : fun puts (ref s : char[]) : nothing; */
        arglist = new LinkedList<Variable>();
        passby = new LinkedList<String>();
        arglist.add(new Variable("s", "char"));
        passby.add("ref");
        data = new SymbolTableEntry(new FunctionType("nothing", arglist, passby));
        this.symbolTable.insert("putc", data);
        data = null;
        arglist =null;
        passby = null;
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

        /* In every new func_def we create a new scope */
        this.symbolTable.enter();
        
        /* Create a SymbolTableEntry object to pass to the insert function */
        SymbolTableEntry data = new SymbolTableEntry(new FuncDefType((AType) node.getRetType(),
                                node.getFplist(), node.getId().toString()));
        
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

        this.symbolTable.insert(node.getId().toString(), data);
        
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