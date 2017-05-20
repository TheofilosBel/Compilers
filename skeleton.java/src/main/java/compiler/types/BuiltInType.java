package compiler.types;

/*
 * Class for the Grace built in types: int and char
 * Also includes nothing which is only used as a return type
 */
public class BuiltInType extends Type {

    private final static Type Int      = new BuiltInType("int ");
    private final static Type Char     = new BuiltInType("char ");
    private final static Type Nothing  = new BuiltInType("nothing ");

    private final String typeName; /* Name of the type */

    /*
     * Constructs a built in type with the name given as a parameter
     */
    public BuiltInType(String name) {
        this.typeName = name;
    }

    @Override
    public String toString() {
        return this.typeName; 
    }

    public String getTypeName() { 
        return this.typeName;  
    }

    @Override
    public boolean isInt() {
        return this.getTypeName().equals(Int.getTypeName());
    }

    @Override
    public boolean isChar() {
        return this.getTypeName().equals(Char.getTypeName());
    }

}
