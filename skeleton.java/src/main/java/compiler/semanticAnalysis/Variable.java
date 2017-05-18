package compiler.semanticAnalysis;

import java.util.LinkedList;

import compiler.node.AExistingArrayDec;
import compiler.node.AType;
import compiler.node.AVariable;

public class Variable{
    
    public String                 name;
    public String                 type;
    public LinkedList<Integer>    dimlist;
    
    
    public Variable(){
        
    }
    
    public Variable(String name, AType type){
        this.name = new String(name);
        this.type = new String(type.getDataType().toString());
        
        /* Create the dim array if it exists, else make an empty list */
        System.out.println("In constructor");
        if (type.getArrayDec() instanceof AExistingArrayDec) {
            
            this.dimlist = new LinkedList<Integer>();
            
            for (int dim = 0; dim < ((AExistingArrayDec) type.getArrayDec()).getIntConst().size(); dim++){
                String e = new String(((AExistingArrayDec) type.getArrayDec()).getIntConst().get(dim).toString());
                e = e.substring(0, e.length()-1);  /* Reduce the reduntant space at the end */
                dimlist.add( Integer.parseInt(e));
            }
            
            
            /* Print the list
            for (int dim = 0; dim < this.dimlist.size(); dim++){
                System.out.println(this.dimlist.get(dim));
            } 
            */
            
        } else {
            this.dimlist = null;
        }
    }
}
