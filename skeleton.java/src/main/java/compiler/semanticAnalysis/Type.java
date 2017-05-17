package compiler.semanticAnalysis;



/*
This is an abstract class used in the SymbolTableEntry
to save the type of each entry
It is extended by classes FunctionType and VariableType
*/
public abstract class Type {
    
    
    /* Simple Type Constructor (Primitive types) */
    public Type() {}

    public abstract String getType();

}