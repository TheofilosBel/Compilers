package compiler.types;

/*
 * Class for the Grace built in types: int and char
 * Also includes nothing which is only used as a return type
 */
public class BuiltInType extends Type {

    public final static Type Int      = new BuiltInType("int ");
    public final static Type Char     = new BuiltInType("char ");
    public final static Type Nothing  = new BuiltInType("nothing ");
    public final static Type Boolean  = new BuiltInType("boolean ");
    public final static Type Address  = new BuiltInType("address ");

    private final String typeName; /* Name of the type */

    /*
     * Constructs a built in type with the name given as a parameter
     */
    public BuiltInType(String name) {
        this.typeName = name;
    }

    public Type makeCopy(){
        return new BuiltInType(this.typeName);
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
    
    @Override
    public boolean isNothing() {
        return this.getTypeName().equals(Nothing.getTypeName());
    }
    
    @Override
    public boolean isBoolean() {
        return this.getTypeName().equals(Boolean.getTypeName());
    }
    
    @Override
    public boolean isEquivWith(Type type) {

        /* Array can't be equal with bultIn type */
        if (type.isArray())
            return false;
        
        if (this.isInt() == type.isInt() && this.isInt())
            return true;
        else if (this.isChar() == type.isChar() && this.isChar())
            return true;
        else if (this.isBoolean() == type.isBoolean() && this.isBoolean())
            return true;
        else if (this.isNothing() == type.isNothing() && this.isNothing())
            return true;
        
        return false;
    }
}
