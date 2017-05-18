package compiler.semanticAnalysis;

import compiler.node.AType;
import compiler.node.PFparList;
import compiler.semanticAnalysis.FunctionType;

public class FuncDecType extends FunctionType {
    
    private boolean funcDefined;

    public FuncDecType(AType rettype, PFparList argList) {
        super(rettype, argList);
        // TODO Auto-generated constructor stub
    }
    
    public boolean getDefFlag(){
        return this.funcDefined;
    }
    
    public void setDefFlag(boolean flag){
        this.funcDefined = flag;
    }

}
