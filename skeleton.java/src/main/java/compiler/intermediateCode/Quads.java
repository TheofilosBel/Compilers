package compiler.intermediateCode;

public class Quads {

    private static Integer labelCount = 0; /* Label of every quad */

    private Integer label;
    private String  opName;
    private String  x;
    private String  y;
    private String  z;

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
        this.label  = labelCount;
    }

    @Override
    public String toString() {
        return this.label + ": " + this.opName + ", " + this.x + ", " +
                this.y + ", " + this.z;
    }
    
    /* Returns the x String */
    public String getX(){
        return this.x;
    }
    
    /* Returns the y String */
    public String getY(){
        return this.y;
    }
    
    /* Returns the Z String */
    public String getZ(){
        return this.z;
    }
    
    
    /* Set the x String */ 
    public void setX(String newStr) {
        this.x = newStr;
    }
    
    /* Set the y String */ 
    public void setY(String newStr) {
        this.y = newStr;
    }
    
    /* Set the z String */ 
    public void setZ(String newStr) {
        this.z = newStr;
    }
    
}
