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
        System.out.print(String.join("", Collections.nCopies(indentation, " ")));
    }

    @Override
    public void inAType(AType node)
    {
        /* See if its an array or not */
        System.out.print(" type =");
        System.out.print(node.getDataType().toString());
        
        /*If it's an array print it */
    }

    @Override
    public void inAExistingArrayDec(AExistingArrayDec node)
    {
        System.out.print(" with dimensions =");
        System.out.print(node.getIntConst().size());
        System.out.print(" and vals =");
        System.out.println(node.getIntConst());
        
    }

    @Override
    public void inANotExistingArrayDec(ANotExistingArrayDec node)
    {
        System.out.println();
    }

    @Override
    public void inAVar(AVar node)
    {
        
        /* Print the right indentation */
        printIndentation();
        
        /*Print the ids */
        System.out.print("VARS list=");
        System.out.print(node.getIdList());
    }

    @Override
    public void inAVarList(AVarList node)
    {
        System.out.print("Var def's found : ");
        System.out.println(node.getVList().size());
        addIndentationLevel();
    }

    @Override
    public void outAVarList(AVarList node)
    {
        removeIndentationLevel();
    }

}
