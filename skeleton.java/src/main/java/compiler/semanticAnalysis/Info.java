package compiler.semanticAnalysis;

import compiler.types.Type;

/*
 * Abstract class to save info about each symbol table entry
 */
public abstract class Info {
    
    public Info() {}

    public abstract Type getType();

    public boolean isVariableInfo() { return false;}
    public boolean isFunctionInfo() { return false;}

}