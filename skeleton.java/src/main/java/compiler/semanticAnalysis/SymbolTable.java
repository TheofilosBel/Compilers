package compiler.semanticAnalysis;

import compiler.node.*;
import compiler.semanticAnalysis.DataTransformation;

import java.util.Stack;
import java.util.TreeMap;

public class SymbolTable {

    /* We need a stack to keep the scopes */
    public Stack<TreeMap<String, Node>> scope_st;

    public SymbolTable() {
        /* Create an empty stack with an empty tree */
        this.scope_st = new Stack<>();
    }

    public void enter(AFuncDef node) {
        System.out.println("Entered new Scope");

        /*Create the Tree map*/
        TreeMap<String, Node> emptyMap = new TreeMap<String, Node>();
        this.scope_st.push(emptyMap);
    }
    
    public boolean insert(DataTransformation data) {
        
        boolean bool = false;
        System.out.println("Inserting Id: " + data.getTableDataName() + " to symbol table");
        
        /* First search if the name exists in this scope only */
        bool = this.scope_st.peek().containsKey(data.getTableDataName());

        /* Insert name to symbol Table */
        if (bool == false){
            this.scope_st.peek().put(data.getTableDataName(), data.getTableDataNode());
            return true;
        }
        
        /* In case we found it inform calling func for the situation */
        return false;
    }

    public boolean lookup(DataTransformation data) {
        String key = null;
        boolean bool = false;

        key =  data.getTableDataName();

        /* Loop all the stack from the top to the start */
        for (int scope_n = 0; scope_n  < this.scope_st.size(); scope_n++) {
            /* Check if the tree map contains the var_name */
            try {
                bool = this.scope_st.get(scope_n).containsKey(key);
            }
            catch (Exception e) {
                System.out.println("Error in loukup: " + e.getMessage());
                return false;
            }

            System.out.println();

            if ( bool == true) {
                System.out.println("Found id:" + key);
                return bool;
            } 
            else {
                System.out.println("Id " + key + "not found :");
            }
        }

        /* Return's false in case the serached failed */
        return false;
    }

    public void exit() {
        System.out.println("Leaving Scope");

        /* Pop the last used scope if its stack is not empty */
        if (!this.scope_st.isEmpty()) {
            this.scope_st.pop();
        }
    }

}
