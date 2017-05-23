package compiler.semanticAnalysis;

import compiler.node.TId;
import compiler.node.PDataType;
import compiler.node.PFparList;

/*
 * Class to keep the info of a function definition
 */
public class FuncDefInfo extends FunctionInfo {
    
    public boolean isMatchedToReturnStmt;

    public FuncDefInfo(PDataType rettype, PFparList argList, TId name) {
        super(rettype, argList, name);
        isMatchedToReturnStmt = false;
    }
    
    public void setIsMatchedToReturnStmt(boolean bool) {
        isMatchedToReturnStmt = bool;
    }

}
