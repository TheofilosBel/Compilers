package compiler.semanticAnalysis;

import compiler.node.AType;
import compiler.node.PFparList;

public class FuncDefType extends FunctionType {

    public FuncDefType(AType rettype, PFparList argList, String name) {
        super(rettype, argList, name);
    }

}
