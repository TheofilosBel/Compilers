package compiler;

import compiler.analysis.*;
import compiler.node.*;
import java.util.Collections;


public class Translation extends DepthFirstAdapter{

    int indentation = 0;

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

    @Override
    public void inAFuncDef(AFuncDef node) {
        /* Printing */
        indentNprint("type: func_def");
        indentNprint("name: " + node.getId());
        indentNprint("return_type: " + node.getRetType().toString());
        
        
        addIndentationLevel();        
    }

    @Override
    public void inAFuncDec(AFuncDec node) {
        /* Printing */
        indentNprint("type: func_def");
        indentNprint("name: " + node.getId());
        indentNprint("return_type: " + node.getRetType().toString());   
    }

    public void outAFuncDef(AFuncDef node) {
        removeIndentationLevel();
    }

    @Override
    public void inAType(AType node) {
        /* See if its an array or not */
        //System.out.print("type: ");
        //System.out.print(node.getDataType().toString());
    }

    @Override
    public void inAExistingArrayDec(AExistingArrayDec node) {
        //System.out.print(" with dimensions =");
        //System.out.print(node.getIntConst().size());
        //System.out.print(" and vals =");
        //System.out.println(node.getIntConst());
    }

    @Override
    public void inANotExistingArrayDec(ANotExistingArrayDec node) {
        //System.out.println();
    }

    @Override
    public void inAVarDef(AVarDef node) {
        indentNprint("type: var_def");
        indentNprint("names: " + node.getVarList());
        indentNprint("type: " + node.getType());
        System.out.println();
    }
    
}
