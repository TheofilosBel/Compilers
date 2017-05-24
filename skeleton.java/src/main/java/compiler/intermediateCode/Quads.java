package compiler.intermediateCode;

public class Quads {

    /* Static integer to give the right label to the quads */
    private static int labelCount = 0;
    
    private String opName;
    private String x;
    private String y;
    private String z;

    
    /* Returns the number of the next quad */
    public static int nextQuad() {
        return labelCount + 1;
    }
    
    /* Its like fuction GenQuad*/
    public Quads(String opName, String x, String y, String z) {
        this.opName = opName;
        this.x      = x;
        this.y      = y;
        this.z      = z;
    }
    
    
}
