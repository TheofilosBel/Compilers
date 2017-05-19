package compiler.semanticAnalysis;

import java.util.LinkedList;

import compiler.node.AExistingArrayDec;
import compiler.node.AType;
import compiler.node.AVariable;

public class Variable {

    private String              name;
    private String              type;
    private LinkedList<Integer> dimensionsList;

    public Variable() {}
    
    /* Copy Constructor */
    public Variable(Variable var) {
        this.name = new String(var.getName());
        this.type = new String(var.getType());
        this.dimensionsList = new LinkedList<Integer>(var.getDimList());
    }

    public Variable(String name, AType type){
        this.name = new String(name);
        this.type = new String(type.getDataType().toString());
        
        /* Create the dim array if it exists, else make an empty list */
        System.out.println("In constructor");
        if (type.getArrayDec() instanceof AExistingArrayDec) {
            
            this.dimensionsList = new LinkedList<Integer>();
            
            for (int dim = 0; dim < ((AExistingArrayDec) type.getArrayDec()).getIntConst().size(); dim++){
                String e = new String(((AExistingArrayDec) type.getArrayDec()).getIntConst().get(dim).toString());
                e = e.substring(0, e.length()-1);  /* Reduce the reduntant space at the end */
                dimensionsList.add( Integer.parseInt(e));
            }
            
            
            /* Print the list */
            for (int dim = 0; dim < this.dimensionsList.size(); dim++){
                System.out.println(this.dimensionsList.get(dim));
            } 
            
        } else {
            this.dimensionsList = null;
        }
    }
    
    /* Simple constructor for int char vars */
    public Variable(String name, String type){
        this.name = new String(name);
        this.type = new String(type);
        this.dimensionsList = null;
    }

    /* Getter Functions */
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public LinkedList<Integer> getDimList() {
        return this.dimensionsList;
    }

}