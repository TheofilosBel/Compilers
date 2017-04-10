package compiler.semanticAnalysis;

import compiler.analysis.*;
import compiler.semanticAnalysis.DataTransformation.*;
import compiler.node.*;
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
        DataTransformation data = new DataTransformation(node.getId().toString(), node);
        this.symbolTable.insert(data);
        
        addIndentationLevel();     
    }

    public void outAFuncDef(AFuncDef node) {
        /* SymbolTable Handling : When we leave from a function leave it's scope */
        this.symbolTable.exit();
        removeIndentationLevel();
    }

    @Override
    public void inAVarDef(AVarDef node) {
    	/* Put the right types to the variables on the def */
    	for (int varnum = 0; varnum < node.getVarList().size(); varnum++) {
    		AVariable var = (AVariable) node.getVarList().get(varnum);
    		var.setType(node.getType());
    	}
    }

    @Override 
    public void inAVariable(AVariable node) {
        indentNprint("Variable:" + node.getId());
        indentNprint("Type: " + node.getType());
        
        DataTransformation data = new DataTransformation(node.getId().toString(), node);
        
        /*Insert it in case it's a new name */
        if (this.symbolTable.insert(data) == false){
            System.out.println("Error Conflicting types : name \"" + node.getId() + "\" already existis");
            System.exit(-1);
        }
        
    }

}