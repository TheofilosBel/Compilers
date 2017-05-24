package compiler.intermediateCode;

import compiler.types.*;
import java.util.LinkedList;

/*
 * A class for the production of the intermediate code
 */
public class IntermediateCode {

    private Integer tempCount = 0;      /* Counter of temporary registers we need */
    private LinkedList<Type> tempTypes; /* Type of each temporary register */

    public IntermediateCode() {
        tempTypes = new LinkedList<Type>();
    }

    /* Returns the current tempCount and increases the counter */
    public String newTemp(Type type) {
        this.tempTypes.add(type);
        String str = new String();
        str = "$" + this.tempCount.toString();
        this.tempCount++;
        return str;
    }
}
