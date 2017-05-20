package compiler.types;

public abstract class Type {

    public Type() {}
    
    @Override
    public abstract String toString();

    public String getTypeName()         { return null;  }
    
    public boolean isInt()              { return false; }
    
    public boolean isChar()             { return false; }
    
    public boolean isArray()            { return false; }
    
    public boolean isAddable(Type type) { return false; }
    
    public boolean isSubed(Type type)   { return false; }
    
    public boolean isDived(Type type)   { return false; }
    
    public boolean isModed(Type type)   { return false; }
    
    public boolean isMulted(Type type)  { return false; }
    
    
}
