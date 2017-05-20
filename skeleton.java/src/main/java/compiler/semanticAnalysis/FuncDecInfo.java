package compiler.semanticAnalysis;

import compiler.node.PDataType;
import compiler.node.PFparList;
import compiler.semanticAnalysis.FunctionInfo;

public class FuncDecInfo extends FunctionInfo {
    
    private boolean funcDefined; /* A flag that indicates if the declaration has been matched to a defined function */

    public FuncDecInfo(PDataType rettype, PFparList argList, String name) {
        super(rettype, argList, name);
        this.funcDefined = false;
    }
    
    public boolean getFuncDefined() {
        return this.funcDefined;
    }
    
    public void setFuncDefined(boolean flag) {
        this.funcDefined = flag;
    }

}
