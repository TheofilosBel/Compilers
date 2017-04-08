package compiler.semanticAnalysis;

import compiler.node.*;
import java.util.Stack;
import java.util.TreeMap;

public class SymbolTable {

    /* We need a stack to keep the scopes */
    public Stack<TreeMap<String, Node>> scope_st;
    
    public SymbolTable()
    {
        /* Create an empty stack with an empty tree */
        this.scope_st = new Stack<>();
    }
    
    public void enter(AFuncDef node)
    {
        System.out.println("Entered new Scope");
        
        /*Create the Tree map*/
        TreeMap<String, Node> emptyMap = new TreeMap<String, Node>();
        this.scope_st.push(emptyMap);
    }
    
    public void insert(Node node) {
        
        System.out.println("Inserting Id: " + node.toString() + " to symbol table");
        
        /* Insert name to symbol Table */
        this.scope_st.peek().put(node.toString(), node);
    }

    public boolean lookup(Node node)
    {
        String key = null;
        boolean bool = false;
        
        key =  node.toString();
        
        /* Loop all the stack from the top to the start */
        for (int scope_n = 0; scope_n  < this.scope_st.size(); scope_n++)
        {
            
            /* Check if the tree map contains the var_name */
            try 
            {
                bool = this.scope_st.get(scope_n).containsKey(key);
            } 
            catch (Exception e)
            {
                System.out.println("Error in loukup: " + e.getMessage());
                return false;
            }
            
            System.out.println();
            
            if ( bool == true)
            {
                System.out.println("Found id:" + key);
                return bool;
            } 
            else 
            {
                System.out.println("Id " + key + "not found :");
            }
        }
        
        /* Return's false in case the serached failed */
        return false;
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
