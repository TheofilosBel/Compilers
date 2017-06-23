package compiler.intermediateCode;

import compiler.types.*;
import compiler.semanticAnalysis.Attributes;
import java.util.LinkedList;

/*
 * A class for the production of the intermediate code
 */
public class IntermediateCode {

    private Integer tempCount = 1;       /* Counter of temporary registers we need */
    private LinkedList<Type>  tempTypes; /* Type of each temporary register */
    private LinkedList<Integer>  tempStackIdices; /* Indices of temp vars in Activation records */
    private LinkedList<Quads> quadsList; /* A list that keeps all the quads generated */

    public IntermediateCode() {
        this.tempTypes = new LinkedList<Type>();
        this.quadsList = new LinkedList<Quads>();
    }

    /* Returns the current tempCount and increases the counter */
    public String newTemp(Type type) {
        this.tempTypes.add(type);
        this.tempStackIdices.add(0);
        String str = new String();
        str = "$" + this.tempCount.toString();
        this.tempCount++;
        return str;
    }

    /* Get the tempCount */
    public Integer getTempCount() {
        return this.tempCount;
    }

    /* Get the quadList */
    public LinkedList<Quads> getQuadsList() {
        return this.quadsList;
    }

    /* Get the tempType */
    public Type getTempType(int tempVarNum) {
        return this.tempTypes.get(tempVarNum - 1);
    }

    /* Get the temp indices to stack */
    public Integer getTempIndex(int tempVarNum) {
        return this.tempStackIdices.get(tempVarNum -1);
    }

    public void putTempIndex(int tempVarNum, Integer st_index) {
        this.tempStackIdices.remove(tempVarNum -1);
        this.tempStackIdices.add(tempVarNum - 1, st_index);
    }

    public void backPatch(LinkedList<Integer> list, Integer patchingLabel) {
        Quads quad = null;

        System.out.println("Patched quads:");
        System.out.println("List " + list);

        /* Loop all the quads */
        for (int quad_n = 0; quad_n < list.size(); quad_n++) {
            /* Get the right quad */
            quad = this.quadsList.get(list.get(quad_n) - 1);  /* -1 because list starts from 0 but quad numbers from 1 */

            /* Patch it */
            quad.setZ(patchingLabel.toString());

            /* Print the quads patched */
            System.out.println(this.quadsList.get(list.get(quad_n) - 1));
        }
    }

    public LinkedList<Integer> mergeLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        /* Create new list - Add the elements of list1 and list2 to new List */
        LinkedList<Integer> newList = new LinkedList<Integer>();
        newList.addAll(list1);
        newList.addAll(list2);

        return newList;
    }

    /* Generates a quad and adds it to the list */
    public void genQuad(String op, String x, String y, String z) {
        Quads quad = new Quads(op, x, y, z);
        this.quadsList.add(quad);
        System.out.println("Quad : "+ quad);
    }

    /* Generates code for a relational operation */
    public void genCodeForRelOp(String relOp, Attributes node, Attributes lhs, Attributes rhs) {
        node.makeList("True", Quads.nextQuad()); /* Initialize true list with the next quad */
        this.genQuad(relOp, lhs.getPlace(), rhs.getPlace(), "*");
        node.makeList("False", Quads.nextQuad()); /* Initialize false list with the next quad */
        this.genQuad("jump", "-", "-", "*");
        
        System.out.println("True :" + node.getTrue() + "\nFalse :" + node.getFalse());
    }

    /* Generate code for a not operation */
    public void genCodeForNotOp(Attributes node, Attributes cond) {
        /* Swap the true and false lists of cond */
        node.setTrue(cond.getFalse());  
        node.setFalse(cond.getTrue());
    }

}
