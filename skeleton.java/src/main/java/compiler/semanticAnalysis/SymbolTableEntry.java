package compiler.semanticAnalysis;

import compiler.node.*;

/*
 * Class that represents every entry in the symbol table
 */
public class SymbolTableEntry {

    /*
    Keep an Info object that saves the required info about the entry
    */
    private Info type;

    public SymbolTableEntry() {}

    /*
     * Constructs a symbol table entry with the given type
     *
     * @type Type of the symbol table entry
     */
    public SymbolTableEntry(Info type) {
        this.type = type;
    }

    /* Returns info about the calling object */
    public Info getType() {
        return this.type;
    }

}
