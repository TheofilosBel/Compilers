package compiler.semanticAnalysis;

import compiler.node.*;

/*
This is a class to save the info needed for a variable
*/
public class VariableType extends Type {
    
    private Variable var;

    public VariableType(String name, AType type) {
        super();
        this.var = new Variable(name, type);
    }

    public String getType() {
        return var.getType();
    }

}