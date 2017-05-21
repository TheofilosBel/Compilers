package compiler.semanticAnalysis;

import compiler.node.PDataType;
import compiler.node.PFparList;

/*
 * Class to keep the info of a function definition
 */
public class FuncDefInfo extends FunctionInfo {

    public FuncDefInfo(PDataType rettype, PFparList argList, String name) {
        super(rettype, argList, name);
    }

}
