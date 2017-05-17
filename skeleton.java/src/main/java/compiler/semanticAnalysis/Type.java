package compiler.semanticAnalysis;

import compiler.node.*;

/*
This is an abstract class used in the SymbolTableEntry
to save the type of each entry
It is extended by classes FunctionType and VariableType
*/
public abstract class Type {
    /* Simple Type Constructor (Primitive types) */
    public Type(AType type) {}

    /* Expression Type Constructor (Complex type) */
    public Type(PExpr expr) {}

    public String getType() {}

}