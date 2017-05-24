package compiler.intermediateCode;

import compiler.types.*;

/*
 * A class for the production of the intermediate code
 */
public class IntermediateCode {

    static private Integer tempCount = 0; /* Counter of temporary registers we need */

    public IntermediateCode() {}

    /* Returns the current tempCount and increases the counter */
    static public String newTemp(Type type) {
        String str = new String();
        str = "$" + tempCount.toString();
        tempCount++;
        return str;
    }
}
