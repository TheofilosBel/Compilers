package compiler.types;

/*
 * Abstract class for Grace types
 */
public abstract class Type {

    public Type() {}

    @Override
    public abstract String toString();

    /* Returns the name of the type */
    public String getTypeName() {
        return null;
    }

    /* Returns true if this is the int type */
    public boolean isInt() {
        return false;
    }

    /* Returns true if this is the char type */
    public boolean isChar() {
        return false;
    }

    /* Returns true if this is the array type */
    public boolean isArray() {
        return false;
    }
    
    public boolean isEquivWith(Type type){
        return false;
    }
    
    public Type getType() {
        return null;
    }

}
