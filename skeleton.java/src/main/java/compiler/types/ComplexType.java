package compiler.types;

import java.util.LinkedList;

/*
 * Class for the Grace complex types
 */
public class ComplexType extends Type {
    
    private String  typeName; /* Name of the complex type */
    private Integer size;     /* Size of the current dimension of the complex type */
    private Type    type;     /* Represents the built in type of the complex type at the last level */

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

        //System.out.print(this.size + " ");
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

    @Override
    public Integer getSize(){
        return this.size;
    }

    @Override
    public void setSize(int newSize){
        this.size = newSize;
    }

    public Type getType() {
        return this.type;
    }

    /*
     * Return a string to print an array type
     */
    @Override
    public String toString() {
        String str = new String();
        str = this.getArrayType();
        for (int i=0; i < this.getArrayDims(); i++)
            str += "[]"; 
        
        return str;
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }
    
    @Override
    public void getDimentions(LinkedList<Integer> list) {
        /* If our Type is Complex call recursively */
        list.add(this.size);
        if (this.getType() instanceof ComplexType) {
            this.getType().getDimentions(list);
            return;
        }
        else { 
            return;
        }
    }

    @Override
    public int getArrayDims() {
        /* If our Type is Complex call recursively */
        if (this.getType() instanceof ComplexType) 
            return this.getType().getArrayDims() + 1;
        else 
            return 1;  /* because we can't add the first dim return 1 here */
    }

    @Override
    public String getArrayType() {
        /* If our Type is Complex call recursively */
        if (this.getType() instanceof ComplexType) 
            return this.getType().getArrayType();
        else 
            return this.getType().getTypeName();
    }

    @Override
    public boolean isArray() {
        return true;
    }


    /* Returns : 1 in case of equality
     *           2 in case of size error
     *           0 in case of type error
     * Parameters : The @type is the type of an the variable in the definition
     *              of a function (can be unknown size) , and the object that
     *              we call it on is the type used in call when we call that func
     *              which has the real sizes of the array. (that's a convention)
     * */
    @Override
    public int isEquivWith(Type type) {

        /* Check the type names */
        if (this.getTypeName().equals(type.getTypeName())) {

            System.out.println("Calling with " + this.getSize() + " from func def " + type.getSize());

            /* If type (calling expr) size is greater then this obj's (defined size)
             * return error */
            if (type.getSize() != -1 && this.size > type.getSize()) {
                return 2;
            }

            /* Recursive call to see the resemblance of the inner types */
            return this.getType().isEquivWith(type.getType());
        }

        /* Return type error */
        return 0;
    }

}
