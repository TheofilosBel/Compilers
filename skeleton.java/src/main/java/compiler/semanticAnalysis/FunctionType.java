package compiler.semanticAnalysis;

import compiler.node.*;

/*
This is a class to save the info needed for a function
*/
public class FunctionType extends Type {
    
    public String type;

    public FunctionType(AType type) {
        this.type = "fun";
    }

    public String getType() {
        return this.type;
    }

}