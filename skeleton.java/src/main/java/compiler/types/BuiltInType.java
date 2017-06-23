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
    public final static Type Void     = new BuiltInType("void ");

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
    public boolean isVoid() {
        return this.getTypeName().equals(Void.getTypeName());
    }

    @Override
    public boolean isAddress() {
        return this.getTypeName().equals(Address.getTypeName());
    }
    
    @Override
    public int isEquivWith(Type type) {

        /* Array can't be equal with bultIn type */
        if (type.isArray())
            return 0;
        
        if (this.isInt() == type.isInt() && this.isInt())
            return 1;
        else if (this.isChar() == type.isChar() && this.isChar())
            return 1;
        else if (this.isBoolean() == type.isBoolean() && this.isBoolean())
            return 1;
        else if (this.isNothing() == type.isNothing() && this.isNothing())
            return 1;
        
        return 0;
    }
}
