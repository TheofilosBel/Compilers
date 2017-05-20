package compiler.semanticAnalysis;

import compiler.node.*;

public class SymbolTableEntry {

    /*
    Keep a Type object that saves info about the type of the entry
    */
    private Info type;

    public SymbolTableEntry() {}

    public SymbolTableEntry(Info type) {
        this.type = type;
    }

    /* Setters and Getters */
    public Info getType() {
        return this.type;
    }

}
