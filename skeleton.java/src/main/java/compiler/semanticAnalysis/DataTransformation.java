package compiler.semanticAnalysis;

import compiler.node.*;

public class DataTransformation {
    
    
    /*Keep a string and a node type obj */
    private String name;
    private Node   node;
    
    
    public DataTransformation(String name, Node node){
        this.name = name;
        this.node = node;
    }
    
    /*---- Setters and Getters */
    public String getTableDataName(){
        return this.name;
    }
    
    public Node getTableDataNode(){
        return this.node;
    }
    
    public void setTableDataName(String newname){
        this.name = newname;
    }
    
    public void setTableDataNode(Node newnode){
        this.node = newnode;
    }
    /*--------------------------*/
    
    


    
    
}
