package compiler.types;

import compiler.node.AType;
import compiler.node.PFparList;
import compiler.types.FunctionType;

public class FuncDecType extends FunctionType {
    
    private boolean funcDefined; /* A flag that indicates if the declaration has been matched to a defined function */

    public FuncDecType(AType rettype, PFparList argList, String name) {
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
