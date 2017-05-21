package compiler.semanticAnalysis;

import java.util.LinkedList;

import compiler.node.AExistingArrayDec;
import compiler.node.AType;
import compiler.node.TId;
import compiler.types.*;

/*
 * Class to save the info needed for every function
 */
public class VariableInfo extends Info {

    private TId  name; /* Name of the variable */
    private Type type; /* Type of the variable */

    public VariableInfo() {}
    
    /* Copy Constructor */
    public VariableInfo(VariableInfo var) {
        this.name = (TId) var.getName().clone();
    }

    /*
     * Construct a variable with the given name and type
     *
     * @name Name of the variable
     * @type Type of the variable
     */
    public VariableInfo(TId name, AType type){
        this.name = (TId) name.clone();
        LinkedList<Integer> dimensionsList = new LinkedList<Integer>();
        
        /*
         * If the variable has a complex type then save the size of every dimension
         * in dimensionsList and create a complex type
         * Else create a built in type
         */
        if (type.getArrayDec() instanceof AExistingArrayDec) {
            for (int dim = 0; dim < ((AExistingArrayDec) type.getArrayDec()).getIntConst().size(); dim++){
                String e = new String(((AExistingArrayDec) type.getArrayDec()).getIntConst().get(dim).toString());
                e = e.substring(0, e.length()-1); /* Remove the reduntant space created by the parser at the end */
                dimensionsList.add(Integer.parseInt(e));
            }
            
            /* Pass the list to create the complex (array) type */
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

    /*
     * This constructor is used for the arguments of the built in functions only
     *
     * @name Name of the argument
     * @type Type of the argument
     * @isComplex Indicates whether the variable has a complex type or not
     */
    public VariableInfo(String name, String type, boolean isComplex) {
        this.name = new TId(name, 0, 0);

        /*
         * If the argument has a complex type then it is a one dimensional array
         * of unknown length as seen from the declarations of the built in functions
         */
        if (isComplex == true) {
            LinkedList<Integer> dimensionsList = new LinkedList<Integer>();
            dimensionsList.add(0); /* 0 indicates unknown length */
            this.type = new ComplexType("array", dimensionsList, type);
        }
        else {
            this.type = new BuiltInType(type); 
        }
    }

    /* Returns the name of the variable */
    public TId getName() {
        return this.name;
    }

    /* Returns the type of the variable */
    public Type getType() {
        return this.type;
    }

}