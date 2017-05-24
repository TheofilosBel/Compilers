package compiler.semanticAnalysis;

import compiler.types.*;

public class Attributes {

    private Type   type;
    private String place;
    
    public Attributes(Type newType) {
        this.type = newType;
    }
    
    public Attributes(Type newType, String place) {
        this.type  = newType;
        this.place = place; 
    }
    
    /*
     * Get the attribute type 
     */
    public Type getType() {
        return this.type;
    }

    /* Get the attribute place */
    public String getPlace(){
        return this.place;
    }
    
    /* Set the attribute place */
    public void setPlace(String place){
        this.place = place;
    }

}
