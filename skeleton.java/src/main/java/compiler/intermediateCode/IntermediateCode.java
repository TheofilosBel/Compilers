package compiler.intermediateCode;

import compiler.types.*;

public class IntermediateCode {

    
    static private Integer tempCount = 0;
    
    public IntermediateCode() {

    }
        
    static public String newTemp(Type type){
        String str = new String();
        str = "$" + tempCount.toString();
        tempCount++;
        return str;
    }
        


}
