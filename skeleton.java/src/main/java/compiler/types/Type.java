package compiler.types;


import java.util.LinkedList;
/*
 * Abstract class for Grace types
 */
public abstract class Type {

    public Type() {}

    public abstract Type makeCopy();

    @Override
    public abstract String toString();

    /* Returns the name of the type */
    public String getTypeName() {
        return null;
    }

    /* Returns the array type : int or char */
    public String getArrayType() {
        return null;
    }

    /* Returns the array dimensions */
    public int getArrayDims() {
        return 0;
    }

    /* Returns true if this is the int type */
    public boolean isInt() {
        return false;
    }

    /* Returns true if this is the char type */
    public boolean isChar() {
        return false;
    }

    /* Returns true if this is the nothing type */
    public boolean isNothing() {
        return false;
    }

    /* Returns true if this is the boolean type */
    public boolean isBoolean() {
        return false;
    }
    
    /* Returns true if this is the void type */
    public boolean isVoid() {
        return false;
    }

    /* Returns true if this is the address type */
    public boolean isAddress() {
        return false;
    }

    /* Returns true if this is the array type */
    public boolean isArray() {
        return false;
    }

    /* Check if the calling type and the argument type are equivalent */
    public int isEquivWith(Type type){
        return 0;
    }

    public Type getType() {
        return null;
    }

    public Integer getSize() {
        return -2;
    }

    public Type getSubType(int[] index){ return null;}

    public void setSize(int newSize) { }

    public void getDimentions(LinkedList<Integer> list) {
        
    }

}
