package compiler.semanticAnalysis;

import compiler.node.PDataType;
import compiler.node.PFparList;
import compiler.semanticAnalysis.FunctionInfo;

/*
 * Class to keep the info of a function declaration
 */
public class FuncDecInfo extends FunctionInfo {
    
    private boolean funcDefined; /* A flag that indicates if the declaration has been matched with a function definition */

    public FuncDecInfo(PDataType rettype, PFparList argList, String name) {
        super(rettype, argList, name);
        this.funcDefined = false;
    }
    
    /* Returns the value of funcDefined */
    public boolean getFuncDefined() {
        return this.funcDefined;
    }
    
    /*
     * Sets the value of funcDefined to the given value
     *
     * @value The value funcDefined will be set to
     */
    public void setFuncDefined(boolean value) {
        this.funcDefined = value;
    }

}
