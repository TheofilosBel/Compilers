package compiler.semanticAnalysis;

import compiler.types.*;

/*
 * Attributs of every node added to the exprTypes
 * hashmap during the type checking phase
 */
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

    /* Returns the attribute type */
    public Type getType() {
        return this.type;
    }

    /* Returns the attribute place */
    public String getPlace(){
        return this.place;
    }

    /* Set the attribute place */
    public void setPlace(String place){
        this.place = place;
    }

}
