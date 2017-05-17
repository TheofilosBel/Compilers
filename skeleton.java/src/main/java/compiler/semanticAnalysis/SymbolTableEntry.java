package compiler.semanticAnalysis;

import compiler.node.*;
import compiler.semanticAnalysis.Type;

public class SymbolTableEntry {

    /*
    Keep a string that indicates the name of the entry,
    a Node object and a Type object that saves info about
    the type of the entry
    */
    private Type   type;

    public SymbolTableEntry() {}

    public SymbolTableEntry(Type type) {
        this.type = type;
    }

    /* Setters and Getters */
    public Type getType() {
        return this.type;
    }

}
