package compiler.semanticAnalysis;

import compiler.types.Type;

/*
 * Abstract class to save info about each symbol table entry
 */
public abstract class Info {
    
    public Info() {}

    public abstract Type getType();

}