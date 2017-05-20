package compiler.semanticAnalysis;

import compiler.types.Type;
/*
This is an abstract class used in the SymbolTableEntry
to save the type of each entry
It is extended by classes FunctionType and VariableType
*/
public abstract class Info {
    
    /* Simple Type Constructor (Primitive types) */
    public Info() {}

    public abstract Type getType();

}