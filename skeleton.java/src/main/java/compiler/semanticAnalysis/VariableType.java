package compiler.semanticAnalysis;

import compiler.node.*;

/*
This is a class to save the info needed for a variable
*/
public class VariableType extends Type {
    
    public String type;

    public VariableType(AType type) {
        super();
        this.type = type.getDataType().toString();
    }

    public String getType() {
        return this.type;
    }

}