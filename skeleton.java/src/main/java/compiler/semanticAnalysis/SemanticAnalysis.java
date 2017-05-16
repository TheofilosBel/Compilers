package compiler.semanticAnalysis;

import compiler.analysis.*;
import compiler.node.*;
import compiler.semanticAnalysis.Type;
import compiler.semanticAnalysis.SymbolTableEntry.*;
import java.util.Collections;

public class SemanticAnalysis extends DepthFirstAdapter{

    int indentation = 0;
    SymbolTable symbolTable;

    public SemanticAnalysis() {
        System.out.println("Constructing Symbol_table");
        this.symbolTable = new SymbolTable();
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
    public void inAFuncDef(AFuncDef node) {
        /* SymbolTable Handling : In every new func_def we have a new scope */
        this.symbolTable.enter(node);
        
        /* Create a DataTransformation object to pass in the insert func */
        SymbolTableEntry data = new SymbolTableEntry(new Type((AType) node.getRetType()));
        this.symbolTable.insert(node.getId().toString(), data);
        
        addIndentationLevel();
    }

    public void outAFuncDef(AFuncDef node) {
        /* SymbolTable Handling : When we leave from a function leave it's scope */
        this.symbolTable.exit();
        removeIndentationLevel();
    }

    @Override
    public void inAVarDef(AVarDef node) {
        AVariable var=null;
        AType type = (AType) node.getType();
    	/* Put the right types to the variables on the def */
    	for (int varnum = 0; varnum < node.getVarList().size(); varnum++) {
    	    
    	    
    		var = (AVariable) node.getVarList().get(varnum);
    		var.setType(type);
    		
    		System.out.println(var.getId().toString() + "  " + var.getType().toString());
    		
    		
    		// Add all th vars in the symbol table
    		SymbolTableEntry data = new SymbolTableEntry(new Type(type));
    		
    		if (this.symbolTable.insert(var.getId().toString(), data) == false){
                System.out.println("Error Conflicting types : name \"" + var.getId() + "\" already existis");
                System.exit(-1);
            }
    		
    		
    	}
    }

    @Override 
    public void inAVariable(AVariable node) {
        indentNprint("Variable:" + node.getId().toString());
        indentNprint("Type: " + node.getType());
        
        //SymbolTableEntry data = new SymbolTableEntry(new Type(node.getType()));
        
        /*Insert it in case it's a new name 
        if (this.symbolTable.insert(node.getId().toString(), data) == false){
            System.out.println("Error Conflicting types : name \"" + node.getId() + "\" already existis");
            System.exit(-1);
        }
        */
    }
    
    
    @Override
    public void outAAssignStmt(AAssignStmt node){
        
        
        
        // Type check if we assing somthing to a var
        if (node.getLvalue() instanceof AIdLvalue) {
            
            AIdLvalue lvalue = (AIdLvalue) node.getLvalue();
            SymbolTableEntry varInfo = this.symbolTable.lookup(lvalue.getId().toString());
            
            /* If it's not decalred */
            if (varInfo == null) {
                System.out.println("Error Undefined variable : \"" + lvalue.getId() + "\"");
                System.exit(-1);
            } 
            else {
                /* Type check */
                Type varType  = varInfo.getType();
                Type exprType = new Type(node.getExpr()); 
                
                
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