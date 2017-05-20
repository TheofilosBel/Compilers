package compiler.types;

public class BuiltInType extends Type {

    /* Two bult in types :
     *     char
     *     int
     * */
    
    private final static Type Int  = new BuiltInType("int ");
    private final static Type Char = new BuiltInType("char ");
    private final static Type non = new BuiltInType("nothing ");
    
    private final String typeName;
    
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
    public boolean isInt(){
        return this.getTypeName().equals(Int.getTypeName());
    }

    @Override
    public boolean isChar(){
        return this.getTypeName().equals(Char.getTypeName());
    }
    
    @Override
    public boolean isAddable(Type type){
        return this.isInt() && type.isInt();  /* Only integers can be added */ 
    }
    
    @Override
    public boolean isSubed(Type type){
        return this == Int && type == Int;  /* Only integers can be subtracted */ 
    }
    
    @Override
    public boolean isDived(Type type){
        return this == Int && type == Int;  /* Only integers can be added divided */ 
    }
    
    @Override
    public boolean isModed(Type type){
        return this == Int && type == Int;  /* Only integers can be moded */ 
    }
    
    @Override
    public boolean isMulted(Type type){
        return this == Int && type == Int;  /* Only integers can be multiplied */ 
    }
}
