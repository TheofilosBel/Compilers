package compiler.intermediateCode;

public class Quads {

    private static int labelCount = 0; /* Label of every quad */

    private String opName;
    private String x;
    private String y;
    private String z;

    /* Returns the number of the next quad */
    public static int nextQuad() {
        return labelCount + 1;
    }

    /* Quad constructor - Works like GenQuad */
    public Quads(String opName, String x, String y, String z) {
        this.opName = opName;
        this.x      = x;
        this.y      = y;
        this.z      = z;
        this.labelCount++;
    }

    @Override
    public String toString() {
        return this.labelCount + ": " + this.opName + ", " + this.x + ", " +
                this.y + ", " + this.z;
    }
    
}
