package compiler.schematicAnalysis;

import compiler.node.*;
import java.util.Stack;
import java.util.TreeMap;

public class SymbolTable extends SchematicAnalysis {

    /* We need a stack to keep the scopes */
    public Stack<TreeMap<String, String>> scope_st;
    
    public SymbolTable()
    {
        /* Create an empty stack with an empty tree */
        this.scope_st = new Stack<>();
    }
    
    public void enter(AFuncDef node)
    {
        System.out.println("Entered new Scope");
        
        /*Create the Tree map*/
        TreeMap<String, String> emptyMap = new TreeMap<String, String>();
        this.scope_st.push(emptyMap);
    }
    
    public void insert(AName node) {
        
        System.out.println("Inserting Id: " + node.getId().toString() + " to symbol table");
        
        /* Insert name to symbol Table */
        this.scope_st.peek().put(node.getId().toString(), "");
    }

    public boolean lookup(AName node)
    {
        String key = null;
        boolean bool = false;
        
        /* Check if the tree map contains the var_name */
        key =  node.getId().toString();
        
        
        try {
            bool = this.scope_st.peek().containsKey(key);
        } catch (Exception e){
            System.out.println("Error in loukup: " + e.getMessage());
        }
        
        System.out.println();
        if ( bool == true)
        {
            System.out.println("Found id:" + key);
        } 
        else 
        {
            System.out.println("Id " + key + "not found :");
        }
        
        /* Return the boolean */
        return bool;
    }
    
    public void exit()
    {
        System.out.println("Leaving Scope");
        
        /* Pop the last used scope if its stack is not empty */
        if (!this.scope_st.isEmpty()) {
            this.scope_st.pop();
        }
        
    }
}
