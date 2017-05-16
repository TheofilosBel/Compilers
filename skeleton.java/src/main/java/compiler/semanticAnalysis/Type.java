package compiler.semanticAnalysis;


import compiler.node.*;


public class Type {
    
    public String type;
    
    
    /* Simple Type Constructor (Primitive types) */
    public Type(AType type) {
       String str =  type.getDataType().toString();
       
       if (str == "int")
           this.type = "int";       
       else if (str == "char")
           this.type = "char";

    }
    
    /* Expression Type Constructor (Complex type) */
    public Type(PExpr expr) {
        

     }
    
    
    public String getType(){
        return this.type;
    }
    
}
