package compiler.fianlCode;

import compiler.intermediateCode.*;
import compiler.semanticAnalysis.SymbolTable;
import compiler.semanticAnalysis.SymbolTableEntry;
import compiler.semanticAnalysis.FunctionInfo;
import compiler.semanticAnalysis.VariableInfo;
import compiler.semanticAnalysis.Info;
import java.util.LinkedList;

/**
 * Created by theo on 10/6/2017.
 */
public class FinalCode {

    private class asCommand {
        String label;
        String operation;
        String operand1;
        String operand2;

        private asCommand(String l, String opname, String op1, String op2){
            this.label = l;
            this.operation = opname;
            this.operand1 = op1;
            this.operand2 = op2;
            System.out.println(this);
        }

        @Override
        public String toString() {
            return this.label + "" + this.operation + " " + this.operand1 + " " +
                    this.operand2;
        }
    }

    /* Needed data for final code production */
    SymbolTable copyOfST;
    LinkedList<asCommand> finalCode;
    FunctionInfo currentFunctionInfo;
    int localVarStackIndex;



    public FinalCode (SymbolTable st, FunctionInfo currentFn){
        this.copyOfST = st;
        this.finalCode = new LinkedList<asCommand>();
        this.currentFunctionInfo = currentFn;
        this.localVarStackIndex = -4;  /* First local variable on activation record */
    }

    public void printCommands() {
        System.out.println("Printing blocks final code");
        for (asCommand item : this.finalCode){
            System.out.println(item);
        }
    }

    /* +----------------------------------------------+
     * | Helping functions for String of intermediate |
     * |            code to Final code                |
     * +----------------------------------------------+ */

    /* Returns : True or False if the str is a int const
     * Parameters : A @str to determine if it's int
     */
    public boolean isIntConst(String str) {

        try {
            int i = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /* Returns : True or False if the str is a string const
     * Parameters : A @str to determine if it's char
     */
    public boolean isCharConst(String str) {

        try {
            char c = str.charAt(0);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /* Return : null or VariableInfo type obj
     * Parameters : @str the string to recognize
     *              @boolptr a one elem array to fit a boolean
     */
    public VariableInfo isVariable(String str, boolean[] variableLocality){
        return (VariableInfo) copyOfST.lookup(str, variableLocality).getInfo();
    }

    /* Returns : TRUE or FALSE
     * Parameters : @str the string to recognize if its TempVariable
     */
    public Boolean isTempVariable(String str){
        /* If th first letter is $ then its a temp var */
        return  str.substring(0, 1).equals("$");
    }

    /* +------------------------------------+
     * |                                    |
     * | Functions that generate final code |
     * |                                    |
     * +------------------------------------+ */

    /* Returns : Nothing
     * Parameters : The @nonLocal string to find in the others Access links
     */
    public void getAR(String nonLocal) {

    }

    /* Returns : True or false in case of error
     * Parameters : @reg is the register to put @data
     */
    public Boolean load(String reg, String data){
        VariableInfo varInfo = null;  // If data is variable keep its info
        String  sizeOfVar = null;     // If data is variable the string to put on assembly move
        int variableStackIndex = 0;   // If data is variable the stackIndex that it resides
        boolean[] variableLocality = new boolean[1];  // For the locality of the variable

        /* In case of data's type produce code */
        if (isIntConst(data)) {
            this.finalCode.add(new asCommand("\t", "mov", reg, data));
            return true;
        }
        else if (isCharConst(data)) {

            char c = data.charAt(0);
            this.finalCode.add(new asCommand("\t", "mov", reg, Integer.toString((int) c)));
            return true;
        }
        else if ((varInfo = isVariable(data, variableLocality)) != null || isTempVariable(data)) {

            /* Produce code depending on locality and temp variables */
            if (variableLocality[0] || isTempVariable(data)) {

                /* Determine the stack index  AND If its not on the activation record put it */
                if (varInfo.getStackIndex() == 0) {
                    variableStackIndex = localVarStackIndex;
                    varInfo.setStackIndex(localVarStackIndex);

                    /* Update the Stack index pointer */
                    if (varInfo.getType().isInt())
                        localVarStackIndex -= 4;
                    else if (varInfo.getType().isInt())
                        localVarStackIndex -= 1;
                }
                else {
                    variableStackIndex = varInfo.getStackIndex();
                }

                /* Determine size */
                if (varInfo.getType().isInt())
                    sizeOfVar = "DWORD PTR";
                else if (varInfo.getType().isInt())
                    sizeOfVar = "BYTE";


                /* Make assembly code */
                this.finalCode.add(new asCommand("\t", "mov", reg, sizeOfVar + " PTR  [ebp" + variableStackIndex + "]" ));
                return true;
            }
            else {

                /* TODO non local vars */
                System.out.println("Variable " + data + " is not local");
            }
        }
        /* TODO add more here*/

        return false;
    }

    /* Function that turns intermediate code to final code
    * Parameters : The @quadList of intermediate code to transform to final code
    */
    public void intermediateToFinalCode(LinkedList<Quads> quadsList){

        System.out.println();
        System.out.println("------------Intermediate to assembly-------------");

        /* For each quad generate final code */
        for (Quads quad : quadsList) {

            System.out.println("Quad " + quad  + " to :");

            /* Get quads operation name*/
            String opName = quad.getOpName();

            if (opName.equals("+")) {
                generateAddFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals(":=")) {
                //generateAssignFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }

            System.out.println("");
        }

    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @temp the temp var to put the result
     */
    private void generateAssignFinalCode(String op1, String temp) {

        /* Load operator 1 */
        load("eax", op1);

        /* Store to temp */
        //store("eax", temp);
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @temp the temp var to put the result
     */
    private void generateAddFinalCode(String op1, String op2, String temp) {
        /* Load operators */
        if (load("eax", op1) && load("ebx", op2)) {

            /* Add it to the list */
            this.finalCode.add(new asCommand("\t", "add", "eax", "ebx"));
        }
    }

}