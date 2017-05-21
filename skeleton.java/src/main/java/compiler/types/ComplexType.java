package compiler.types;

import java.util.LinkedList;

/*
 * Class for the Grace complex types
 */
public class ComplexType extends Type {
    
    private final String  typeName; /* Name of the complex type */
    private final Integer size;     /* Size of the current dimension of the complex type */
    private final Type    type;     /* Represents the built in type of the complex type at the last level */

    /*
     * Constructs a complex type
     *
     * @name Name of the complex type
     * @remainingTypes Further complex types of the complex type that have not been evaluated
     * @builtInTypeName The built in type of the elements of the complex type
     */
    public ComplexType(String name, LinkedList<Integer> remainingTypes, String builtInTypeName) {
        this.typeName = name;
        this.size     = remainingTypes.removeFirst();

        /* If there are further complex types */
        if (remainingTypes.size() > 0) {
            /* Recursively evaluate the inner complex types */
            this.type = new ComplexType("array", remainingTypes, builtInTypeName);
        } 
        else {
            this.type = new BuiltInType(builtInTypeName);
        }
    }
    
    public ComplexType(String name, Integer size, Type type){
        this.typeName = name;
        this.size     = size;
        this.type     = type.makeCopy();
    }
    
    
    public Type makeCopy(){
        return new ComplexType(this.typeName, this.size,  this.getType());
    }

    
    public Type getType() {
        return this.type;
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
    
    @Override
    public boolean isEquivWith(Type type){
        
        /* Check the type names */
        if (this.getTypeName().equals(type.getTypeName())) {
            
            /* Recursive call to see the resemblance of the inner types */
            if (this.getType().isEquivWith(type.getType())){
                return true;
            }
            else {
                return false;
            }
        }
        
        /* Else part */
        return false; 
    }

}
