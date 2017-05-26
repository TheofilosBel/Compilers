package compiler.semanticAnalysis;

import compiler.node.TId;
import compiler.node.PDataType;
import compiler.node.PFparList;
import java.util.LinkedList;

/*
 * Class to keep the info of a function definition
 */
public class FuncDefInfo extends FunctionInfo {

    /*
     * Indicates whether a function definition has been matched to a return statement
     * The function should have a return type that is not equal to 'nothing'
     */
    public boolean isMatchedToReturnStmt;
    public LinkedList<VariableInfo> localVariables; /* A List to keep the local variables */

    public FuncDefInfo(PDataType rettype, PFparList argList, TId name) {
        super(rettype, argList, name);
        this.isMatchedToReturnStmt = false;
        this.localVariables = new LinkedList<VariableInfo>();
    }

    /* Adds a variable in the local variable list */
    public void addLocalVariable(VariableInfo var) {
        this.localVariables.add(var);
    }

    /* Update the value of isMatchedToReturnStmt */
    public void setIsMatchedToReturnStmt(boolean bool) {
        isMatchedToReturnStmt = bool;
    }

    /* Return the value of isMatchedToReturnStmt */
    public boolean getIsMatchedToReturnStmt() {
        return isMatchedToReturnStmt;
    }
    
    public LinkedList<VariableInfo> getLocalVariables() {
        return this.localVariables;
    }
}
