package compiler.semanticAnalysis;

import java.util.LinkedList;

import compiler.node.AExistingArrayDec;
import compiler.node.AType;
import compiler.types.*;

public class VariableInfo extends Info {

    private String name;
    private Type   type;

    public VariableInfo() {}
    
    /* Copy Constructor */
    public VariableInfo(VariableInfo var) {
        this.name = new String(var.getName());
    }

    public VariableInfo(String name, AType type){
        this.name = new String(name);
        
        LinkedList<Integer> dimensionsList = new LinkedList<Integer>();
        
        /* Create the dim array if it exists, else make an empty list */
        System.out.println("In constructor");
        if (type.getArrayDec() instanceof AExistingArrayDec) {
            
            for (int dim = 0; dim < ((AExistingArrayDec) type.getArrayDec()).getIntConst().size(); dim++){
                String e = new String(((AExistingArrayDec) type.getArrayDec()).getIntConst().get(dim).toString());
                e = e.substring(0, e.length()-1);  /* Reduce the reduntant space at the end */
                dimensionsList.add(Integer.parseInt(e));
            }
            
            /* Pass the list to make the array type */
            this.type = new ComplexType("array", dimensionsList, type.getDataType().toString());

            /* Print the list */
            for (int dim = 0; dim < dimensionsList.size(); dim++){
                System.out.println(dimensionsList.get(dim));
            }
        }
        else {
            
            System.out.println("\"" + type.getDataType().toString() +"\"");
            
            /* Make a built in type */
            this.type = new BuiltInType(type.getDataType().toString());
        }
        
    }
    
    /* This constructor is used for the parameters of the built in functions only */
    public VariableInfo(String name, String type){
        this.name = new String(name);

        /* If the argument has been passed by ref then it is a one dimensional array of unknown length */
        if (type.equals(new String("ref"))) {
            this.type = new ComplexType("array", new LinkedList<Integer>(), type);
        }
        else {
            this.type = new BuiltInType(type); 
        }
    }

    /* Getter Functions */
    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

}