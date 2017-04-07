package compiler.schematicAnalysis;

import compiler.analysis.*;
import compiler.node.*;
import java.util.Collections;

public class SchematicAnalysis extends DepthFirstAdapter{
    
    int indentation = 0;
    SymbolTable symbolTable;
    
    public SchematicAnalysis()
    {
        System.out.println("Constructing Symbol_table");
        this.symbolTable = new SymbolTable();  
    }

    /* For debuging reasons */
    private void addIndentationLevel() {
        indentation++;
    }

    private void removeIndentationLevel() {
        indentation--;
    }

    private void printIndentation() {
        System.out.print(String.join("", Collections.nCopies(indentation, "   ")));
    }
    
    private void indentNprint(String msg) 
    {
        printIndentation();
        System.out.println(msg);
    }
    /*-----------------------*/
    
    @Override
    public void inAFuncDef(AFuncDef node)
    {
        /* SymbolTable Handling : In every new func_def we have a new scope*/
        this.symbolTable.enter(node);   
        addIndentationLevel();        
    }
    
    
    public void outAFuncDef(AFuncDef node)
    {
        /* SymbolTable Handling */
        this.symbolTable.exit();
        removeIndentationLevel();
    }
    
    @Override 
    public void inAName(AName node)
    {
        printIndentation();
        /* Lookup the symbol table */
        if (this.symbolTable.lookup(node) != true)
        {
            /*Insert it in case it's a new name */
            this.symbolTable.insert(node);
        }
    }
    
    
    
}
