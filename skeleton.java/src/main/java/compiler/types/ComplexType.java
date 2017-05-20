package compiler.types;

import java.util.LinkedList;

public class ComplexType extends Type {

    //private final static ComplexType array  = new ComplexType("array", null, );
    
    private final String  typeName;
    private final Integer size;
    private final Type    type;
    
    
    public ComplexType(String name, LinkedList<Integer> remainingTypes, String bultInTypeName) {
        this.typeName = name;
        this.size     = remainingTypes.removeFirst();
        
        if (remainingTypes.size() > 0) {
            
            /* Recursive add */
            this.type = new ComplexType("array", remainingTypes, bultInTypeName);
        } 
        else {
            this.type =  new BuiltInType(bultInTypeName);
        }
    }
    
    @Override
    public String toString() {
        return this.getTypeName() + " " + type.toString();
    }
    
    @Override
    public String getTypeName() {
        return this.typeName;
    }
    
    @Override
    public boolean isArray() {
        return true;
    }

}
