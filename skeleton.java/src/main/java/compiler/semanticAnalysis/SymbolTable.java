package compiler.semanticAnalysis;

import compiler.node.*;
import compiler.semanticAnalysis.SymbolTableEntry;

import java.util.Stack;
import java.util.TreeMap;

/*
 * This class represents the symbol table
 * It is implemented as a stack of trees
 */
public class SymbolTable {

    private Stack<TreeMap<String, SymbolTableEntry>> scope_st; /* We need a stack to keep the scopes */
    private boolean isMainDefined;

    public SymbolTable() {
        /* Create an empty stack with an empty tree */
        this.scope_st = new Stack<>();
        this.isMainDefined = false;
    }

    public void enter() {
        System.out.println("Entered new Scope");

        /* Create an empty TreeMap */
        TreeMap<String, SymbolTableEntry> emptyMap = new TreeMap<String, SymbolTableEntry>();
        this.scope_st.push(emptyMap);
    }
    
    public boolean insert(String symbolName, SymbolTableEntry data) {
        boolean found = false;
        System.out.println("Inserting Id: " + symbolName + " to symbol table");
        
        /* First search if the name exists in this scope only */
        found = this.scope_st.peek().containsKey(symbolName);

        /* Insert name to symbol Table */
        if (found == false) {
            this.scope_st.peek().put(symbolName, data);
            return true;
        }
        
        /* In case we found it inform calling function for the situation */
        return false;
    }

    /* Returns : SymbolTableEntry or null
     * Parameters : @key to search in the symbol table
     *              if @boolptr == null then nothing happens else it must be
     *              an one elem array to return the locality o the variable
     */
    public SymbolTableEntry lookup(String key, int[] locality) {
        boolean found = false;

        /* Loop all the stack from the top to the start */
        for (int scope_n = this.scope_st.size() - 1; scope_n >= 0; scope_n--) {
            
            /* Check if the TreeMap contains the key */
            try {
                found = this.scope_st.get(scope_n).containsKey(key);
            }
            catch (Exception e) {
                System.out.println("Error in loukup: " + e.getMessage());
                return null;
            }

            System.out.println();

            if (found == true) {
                System.out.println("Found id:" + key + " scope " + (this.scope_st.size() - 1 - scope_n));

                /* Determine locality */
                if (locality != null && scope_n == this.scope_st.size() - 1)
                    locality[0] =  this.scope_st.size() - 1 - scope_n;
                else if (locality != null && scope_n != this.scope_st.size() - 1)
                    locality[0] = this.scope_st.size() - 1 - scope_n;

                /* Return the object */
                return this.scope_st.get(scope_n).get(key);
            }
        }

        /* Return false in case the search failed */
        System.out.println("Id " + key + " not found :");
        return null;
    }

    public void exit() {
        System.out.println("Leaving Scope");

        /* Pop the last used scope if its stack is not empty */
        if (!this.scope_st.isEmpty()) {
            this.scope_st.pop();
        }
    }

    public boolean getIsMainDefined() {
        return this.isMainDefined;
    }

    public void setIsMainDefined() {
        this.isMainDefined = true;
    }    

}
