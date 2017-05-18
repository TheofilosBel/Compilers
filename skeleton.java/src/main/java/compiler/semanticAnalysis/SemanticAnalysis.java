package compiler.semanticAnalysis;

import compiler.analysis.*;
import compiler.node.*;
import compiler.semanticAnalysis.Type;
import compiler.semanticAnalysis.Variable;
import compiler.semanticAnalysis.SymbolTableEntry;
import java.util.Collections;
import java.util.LinkedList;


public class SemanticAnalysis extends DepthFirstAdapter {

    int indentation = 0;
    SymbolTable symbolTable;

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
            
            /* If a func def or var with the same name found raise exception */
            if( !(result.getType() instanceof FuncDecType)) {
                
                System.out.println("Error: Function with name " + node.getId().toString() + " cat be used, name already in use");
                System.exit(-1);
            }
            
            /* Declaration of function found, update its flag (matched definition with declaration) */
            ((FuncDecType) result.getType()).setDefFlag(true);
            
            /* Check equivalence */
            
        }
        
        
        
        /* In every new func_def we create a new scope */
        this.symbolTable.enter();
        
        /* Create a SymbolTableEntry object to pass to the insert function */
        SymbolTableEntry data = new SymbolTableEntry(new FuncDefType((AType) node.getRetType(), node.getFplist(), node.getId().toString()));
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
                System.out.println("Error Conflicting types : name \"" + var.getId() + "\" already existis");
                System.exit(-1);
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