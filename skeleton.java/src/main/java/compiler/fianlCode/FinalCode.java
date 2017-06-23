package compiler.fianlCode;

import compiler.intermediateCode.*;
import compiler.semanticAnalysis.SymbolTable;
import compiler.semanticAnalysis.SymbolTableEntry;
import compiler.semanticAnalysis.FunctionInfo;
import compiler.semanticAnalysis.VariableInfo;
import compiler.types.*;
import compiler.semanticAnalysis.Info;
import java.util.LinkedList;

/**
 * Created by theo on 10/6/2017.
 * Final code will be produced in
 * every function definition.
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
            return this.label + "" + this.operation + "," + this.operand1 + "," +
                    this.operand2;
        }
    }

    /* Needed data for final code production */
    SymbolTable copyOfST;                    // Needed
    LinkedList<asCommand> finalCode;
    FunctionInfo currentFunctionInfo;
    IntermediateCode interCodeObj;
    int localVarStackIndex;


    public FinalCode (SymbolTable st, FunctionInfo currentFn, IntermediateCode copyOfIdObj){
        this.copyOfST = st;
        this.finalCode = new LinkedList<asCommand>();
        this.currentFunctionInfo = currentFn;
        this.localVarStackIndex = -4;  /* First local variable on activation record */
        this.interCodeObj = copyOfIdObj;
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

    /* Returns : The an int with the stack Index of the temp var
     * Parameters : The temp var @tempVar to put on the Activation record (stack)
     */
    private int assignTempStackInd(String tempVar) {
        int variableStackIndex;

        /* Get temp vars type */
        Type tempType = this.interCodeObj.getTempType(Integer.parseInt(tempVar));

        /* Get the stack index of the temp variable */
        variableStackIndex = this.interCodeObj.getTempIndex(Integer.parseInt(tempVar));
        if (variableStackIndex == 0) {

            //System.out.println("$" + tempVar + " Updated to " + this.localVarStackIndex);
            this.interCodeObj.putTempIndex(Integer.parseInt(tempVar), this.localVarStackIndex);
            variableStackIndex = this.localVarStackIndex;

            /* Update the Stack index pointer */
            if (tempType.isInt())
                this.localVarStackIndex -= 4;
            else if (tempType.isChar())
                this.localVarStackIndex -= 1;
        }

        return variableStackIndex;
    }

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

    /* Returns : True or False if the str is a char const
     * Parameters : A @str to determine if it's char
     */
    public boolean isCharConst(String str) {

        return str.substring(0,1).equals("\'");
    }

    /* Returns : True or False if the str is a string const
     * Parameters : A @str to determine if it's a string const
     */
    public boolean isStringConst(String str) {

        return str.substring(0,1).equals("\"");
    }

    /* Return : null or VariableInfo type obj
     * Parameters : @str the string to recognize
     *              @variableLocality a one elem array to fit an int
     */
    public VariableInfo isVariable(String str, int[] variableLocality) {
        SymbolTableEntry varEntry = this.copyOfST.lookup(str, variableLocality);

        /* If the var entry exists return it's info */
        if (varEntry != null) {
            return (VariableInfo) varEntry.getInfo();
        }

        /* In case of null return null */
        return null;
    }

    /* Returns : TRUE or FALSE
     * Parameters : @str the string to recognize if its TempVariable
     */
    public Boolean isTempVariable(String str){
        /* If th first letter is $ then its a temp var */
        return  str.substring(0, 1).equals("$");
    }

    /* Returns : TRUE or FALSE
     * Parameters : @str the string to recognize if its pointer access
     */
    public Boolean isPointerAccess(String str){
        /* If th first letter is $ then its a temp var */
        return  str.substring(0, 1).equals("[");
    }

    /* +------------------------------------+
     * |                                    |
     * | Functions that generate final code |
     * |                                    |
     * +------------------------------------+ */

    /* Returns : Nothing
     * Parameters : The @nonLocal string to find in the others Access links
     * Produces final code for the transfer to the Access link of the wanted
     * variable (there it's local)
     */
    public void getAR(String nonLocal) {
        int[] nestingsBack = new int[1];

        /* Get the times we have to go back */
        copyOfST.lookup(nonLocal, nestingsBack);

        /* We are going to get the access link of the current AR to esi register */
        this.finalCode.add(new asCommand("\t", "mov", "esi", "DWORD PTR [ebp + 8]" ));

        /* Then go back to the asked AR */
        for (int i=0; i < nestingsBack[0]; i++) {
            this.finalCode.add(new asCommand("\t", "mov", "esi", "DWORD PTR [esi + 8]" ));
        }
    }

    /* Returns: Nothing
     * Parameters: @calleeFunction named of the function that was called by the current function
     */
    public void updateAL(String calleeFunction) {
        int[] locality = new int[1];
        
        /* Get the nesting level of the two functions */
        int callerLevel = ((FunctionInfo) copyOfST.lookup(currentFunctionInfo.getName().toString(), locality)
                            .getInfo()).getNestingLevel();
        int calleeLevel = ((FunctionInfo) copyOfST.lookup(calleeFunction, locality).getInfo()).getNestingLevel();

        /* Produce final code */
        if (callerLevel < calleeLevel) {
            this.finalCode.add(new asCommand("\t", "push", "ebp", ""));
        }
        else if (callerLevel == calleeLevel) {
            this.finalCode.add(new asCommand("\t", "push", "DWORD PTR [ebp + 8]", ""));
        }
        else {
            this.finalCode.add(new asCommand("\t", "mov", "esi", "DWORD PTR [ebp + 8]"));

            for (int i = 0; i < callerLevel - calleeLevel + 1; i++) {
                this.finalCode.add(new asCommand("\t", "mov", "esi", "DWORD PTR [esi + 8]"));
            }

            this.finalCode.add(new asCommand("\t", "push", "", "DWORD PTR [esi + 8]"));
        }
    }

    /* Returns : True or false in case of error
     * Parameters : @reg is the register to put @data
     */
    public Boolean load(String reg, String data){
        VariableInfo varInfo = null;  // If data is variable keep its info
        String  sizeOfVar = null;     // If data is variable the string to put on assembly move
        int variableStackIndex = 0;   // If data is variable the stackIndex that it resides
        int[] variableLocality = new int[1];  // For the locality of the variable

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
        else if (isTempVariable(data)) {

            /* Get temp vars type */
            Type tempType = this.interCodeObj.getTempType(Integer.parseInt(data.substring(1)));

            /* Get the stack index of the temp variable */
            variableStackIndex = assignTempStackInd(data.substring(1));

            /* Determine size */
            if (tempType.isInt() || tempType.isAddress())
                sizeOfVar = "DWORD";
            else if (tempType.isChar())
                sizeOfVar = "BYTE";

            /* Make assembly code */
            this.finalCode.add(new asCommand("\t", "mov", reg, sizeOfVar + " PTR [ebp + " + variableStackIndex + "]"));
            return true;

        }
        else if ((varInfo = isVariable(data, variableLocality)) != null) {

            /* Determine the stack index AND If its not in the activation record put it */
            if (varInfo.getStackIndex() == 0 && variableLocality[0] == 0) {
                variableStackIndex = localVarStackIndex;
                varInfo.setStackIndex(localVarStackIndex);

                /* Update the Stack index pointer */
                if (varInfo.getType().isInt())
                    localVarStackIndex -= 4;
                else if (varInfo.getType().isChar())
                    localVarStackIndex -= 1;
            } else {
                variableStackIndex = varInfo.getStackIndex();
            }

            /* Determine size */
            if (varInfo.getType().isInt())
                sizeOfVar = "DWORD";
            else if (varInfo.getType().isChar())
                sizeOfVar = "BYTE";

            if (varInfo.getMethod().equals("non") || varInfo.getMethod().equals("val")) {

                /* Produce code depending on locality and temp variables */
                if (variableLocality[0] == 0) {

                    /* Make assembly code */
                    this.finalCode.add(new asCommand("\t", "mov", reg, sizeOfVar + " PTR [ebp + " + variableStackIndex + "]"));
                    return true;
                } else {

                    /* In this case it's non local var find its activation record */
                    getAR(data);

                    /* Make assembly code */
                    this.finalCode.add(new asCommand("\t", "mov", reg, sizeOfVar + " PTR [esi + " + variableStackIndex + "]"));
                    return true;

                }
            } else if (varInfo.getMethod().equals("ref")) {

                /* Produce code depending on locality and temp variables */
                if (variableLocality[0] == 0) {

                    /* Make assembly code */
                    this.finalCode.add(new asCommand("\t", "mov", "esi", sizeOfVar + " PTR [ebp + " + variableStackIndex + "]"));
                    this.finalCode.add(new asCommand("\t", "mov", reg, sizeOfVar + " PTR [esi]"));
                    return true;
                } else {

                    /* In this case it's non local var find its activation record */
                    getAR(data);

                    /* Make assembly code */
                    this.finalCode.add(new asCommand("\t", "mov", "esi", sizeOfVar + " PTR [esi + " + variableStackIndex + "]"));
                    this.finalCode.add(new asCommand("\t", "mov", reg, sizeOfVar + " PTR [esi]"));
                    return true;

                }
            }
        } else if (isPointerAccess(data)) {

            /* Inside [ ] there is always a temp var */
            int tempNum = Integer.parseInt(data.substring(2, data.length() - 1));
            Type tempType = this.interCodeObj.getTempType(tempNum);

            /* Determine the stack index */
            variableStackIndex = assignTempStackInd(data.substring(2, data.length() - 1));

            /* Determine size */
            if (tempType.isInt() || tempType.isAddress())
                sizeOfVar = "DWORD";
            else if (tempType.isChar())
                sizeOfVar = "BYTE";

            load("edi", data.substring(1, data.length() - 1));
            this.finalCode.add(new asCommand("\t", "mov", reg, sizeOfVar + " PTR [ebp + " + variableStackIndex + "]"));
            return true;
        }
        else {

            /* Its the { } case */
            loadAddress(reg, data.substring(2, data.length() - 1));

        }

        return false;
    }

    public boolean loadAddress(String reg, String data) {
        VariableInfo varInfo = null;  // If data is variable keep its info
        String  sizeOfVar = null;     // If data is variable the string to put on assembly move
        int variableStackIndex = 0;   // If data is variable the stackIndex that it resides
        int[] variableLocality = new int[1];  // For the locality of the variable


        /* Based on data type produce final code */
        if (isStringConst(data)) {


        }
        else if ((varInfo = isVariable(data, variableLocality)) != null) {

            /* Determine the stack index AND If its not in the activation record put it */
            if (varInfo.getStackIndex() == 0 && variableLocality[0] == 0) {
                variableStackIndex = localVarStackIndex;
                varInfo.setStackIndex(localVarStackIndex);

                /* Update the Stack index pointer */
                if (varInfo.getType().isInt())
                    localVarStackIndex -= 4;
                else if (varInfo.getType().isChar())
                    localVarStackIndex -= 1;
            } else {
                variableStackIndex = varInfo.getStackIndex();
            }

            /* Determine size */
            if (varInfo.getType().isInt())
                sizeOfVar = "DWORD";
            else if (varInfo.getType().isChar())
                sizeOfVar = "BYTE";

            /* Check if it's parameter , and locality */
            if (variableLocality[0] == 0 && varInfo.getMethod().equals("val")) {
                /* Make assembly code */
                this.finalCode.add(new asCommand("\t", "lea", reg, sizeOfVar + " PTR [ebp + " + variableStackIndex + "]"));
                return true;
            }
            else if (variableLocality[0] == 0 && varInfo.getMethod().equals("ref")) {
                /* Make assembly code */
                this.finalCode.add(new asCommand("\t", "mov", reg,  "DWORD PTR [ebp + " + variableStackIndex + "]"));
                return true;
            }
            else if (variableLocality[0] != 0 && varInfo.getMethod().equals("val")) {
                /* Make assembly code */
                getAR(data);
                this.finalCode.add(new asCommand("\t", "lea", reg,  sizeOfVar + " PTR [esi + " + variableStackIndex + "]"));
                return true;
            }
            else if (variableLocality[0] != 0 && varInfo.getMethod().equals("ref")) {
                /* Make assembly code */
                getAR(data);
                this.finalCode.add(new asCommand("\t", "mov", reg,  sizeOfVar + " PTR [esi + " + variableStackIndex + "]"));
                return true;
            }
        }

        return false;
    }

    public boolean store(String reg, String data) {

        VariableInfo varInfo = null;  // If data is variable keep its info
        String  sizeOfVar = null;     // If data is variable the string to put on assembly move
        int variableStackIndex = 0;   // If data is variable the stackIndex that it resides
        int[] variableLocality = new int[1];  // For the locality of the variable


        /* Based on data type produce final code */
        if (isTempVariable(data)) {

            /* Get temp vars type */
            Type tempType = this.interCodeObj.getTempType(Integer.parseInt(data.substring(1)));

            /* Get the stack index of the temp variable */
            variableStackIndex = assignTempStackInd(data.substring(1));

            /* Determine size */
            if (tempType.isInt() || tempType.isAddress())
                sizeOfVar = "DWORD";
            else if (tempType.isChar())
                sizeOfVar = "BYTE";

            /* Make assembly code */
            this.finalCode.add(new asCommand("\t", "mov",sizeOfVar + " PTR [ebp + " + variableStackIndex + "]", reg));
            return true;
        }
        else if (isPointerAccess(data)) {

            /* Inside [ ] there is always a temp var */
            int tempNum = Integer.parseInt(data.substring(2, data.length() - 1));
            Type tempType = this.interCodeObj.getTempType(tempNum);

            /* Determine size */
            if (tempType.isArray() && tempType.getArrayType().equals("int "))
                sizeOfVar = "DWORD";
            else if (tempType.isArray() && tempType.getArrayType().equals("char "))
                sizeOfVar = "BYTE";

            load("edi", data.substring(1, data.length() - 1));
            this.finalCode.add(new asCommand("\t", "mov", sizeOfVar + " PTR [ebp + " + variableStackIndex + "]", reg));
            return true;
        }
        else if ((varInfo = isVariable(data, variableLocality)) != null) {

            /* Determine the stack index AND If its not in the activation record put it */
            if (varInfo.getStackIndex() == 0 && variableLocality[0] == 0) {
                variableStackIndex = localVarStackIndex;
                varInfo.setStackIndex(localVarStackIndex);

                /* Update the Stack index pointer */
                if (varInfo.getType().isInt())
                    localVarStackIndex -= 4;
                else if (varInfo.getType().isChar())
                    localVarStackIndex -= 1;
            } else {
                variableStackIndex = varInfo.getStackIndex();
            }

            /* Determine size */
            if (varInfo.getType().isInt())
                sizeOfVar = "DWORD";
            else if (varInfo.getType().isChar())
                sizeOfVar = "BYTE";

            /* Check if it's parameter , and locality */
            if (variableLocality[0] == 0 && (varInfo.getMethod().equals("val") || varInfo.getMethod().equals("non"))) {
                /* Make assembly code */
                this.finalCode.add(new asCommand("\t", "mov",sizeOfVar + " PTR [ebp + " + variableStackIndex + "]", reg));
                return true;
            }
            else if (variableLocality[0] == 0 && varInfo.getMethod().equals("ref")) {
                /* Make assembly code */
                this.finalCode.add(new asCommand("\t", "mov", "esi",  sizeOfVar + "PTR [ebp + " + variableStackIndex + "]"));
                this.finalCode.add(new asCommand("\t", "mov", sizeOfVar + " PTR [esi]", reg));
                return true;
            }
            else if (variableLocality[0] != 0 && (varInfo.getMethod().equals("val") || varInfo.getMethod().equals("non"))) {
                /* Make assembly code */
                getAR(data);
                this.finalCode.add(new asCommand("\t", "mov",sizeOfVar + " PTR [esi + " + variableStackIndex + "]",reg));
                return true;
            }
            else if (variableLocality[0] != 0 && varInfo.getMethod().equals("ref")) {
                /* Make assembly code */
                getAR(data);
                this.finalCode.add(new asCommand("\t", "mov", "esi",  sizeOfVar + "PTR [esi + " + variableStackIndex + "]"));
                this.finalCode.add(new asCommand("\t", "mov", sizeOfVar + " PTR [esi]", reg));
                return true;
            }
        }

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
            else if (opName.equals("-")) {
                generateSubFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals("*")) {
                generateMulFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals(":=")) {
                generateAssignFinalCode(quad.getX(), quad.getZ());
            }
            else if (opName.equals("div")) {
                generateDivFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals("mod")) {
                generateModFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals("=")) {
                generateEqFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals(">=")) {
                generateGeqFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals(">")) {
                generateGreaterFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals("<=")) {
                generateLeqFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals("<")) {
                generateLessFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals("jump")) {
                generateJumpFinalCode(quad.getZ());
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
        store("eax", temp);
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @temp the temp var to put the result
     */
    private void generateAddFinalCode(String op1, String op2, String temp) {
        /* Load operators */
        load("eax", op1);
        load("ebx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "add", "eax", "ebx"));

        /* store */
        store("eax", temp);
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @temp the temp var to put the result
     */
    private void generateSubFinalCode(String op1, String op2, String temp) {
        /* Load operators */
        load("eax", op1);
        load("ebx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "sub", "eax", "ebx"));

        /* store */
        store("eax", temp);

    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @temp the temp var to put the result
     */
    private void generateMulFinalCode(String op1, String op2, String temp) {
        /* Load operators */
        load("eax", op1);
        load("ecx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "imul", "eax", "ecx"));

        /* store */
        store("eax", temp);
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @temp the temp var to put the result
     */
    private void generateDivFinalCode(String op1, String op2, String temp) {
        /* Load operators */
        load("eax", op1);
        this.finalCode.add(new asCommand("\t", "cdq", "", ""));
        load("ebx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "idiv", "eax", "ebx"));

        /* store the quotation from eax to temp */
        store("eax", temp);
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @temp the temp var to put the result
     */
    private void generateModFinalCode(String op1, String op2, String temp) {
        /* Load operators */
        load("eax", op1);
        this.finalCode.add(new asCommand("\t", "cdq", "", ""));
        load("ebx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "idiv", "eax", "ebx"));

        /* store the remaining from edx to temp */
        store("edx", temp);
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @label the label to jump to
     */
    private void generateEqFinalCode(String op1, String op2, String label) {
        /* Load operators */
        load("eax", op1);
        load("edx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "cmp", "eax", "edx"));

        /* jump to label */
        this.finalCode.add(new asCommand("\t", "jnz", label, ""));
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @label the label to jump to
     */
    private void generateGeqFinalCode(String op1, String op2, String label) {
        /* Load operators */
        load("eax", op1);
        load("edx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "cmp", "eax", "edx"));

        /* jump to label */
        this.finalCode.add(new asCommand("\t", "jge", label, ""));
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @label the label to jump to
     */
    private void generateGreaterFinalCode(String op1, String op2, String label) {
        /* Load operators */
        load("eax", op1);
        load("edx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "cmp", "eax", "edx"));

        /* jump to label */
        this.finalCode.add(new asCommand("\t", "jg", label, ""));
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @label the label to jump to
     */
    private void generateLeqFinalCode(String op1, String op2, String label) {
        /* Load operators */
        load("eax", op1);
        load("edx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "cmp", "eax", "edx"));

        /* jump to label */
        this.finalCode.add(new asCommand("\t", "jle", label, ""));
    }

    /* Returns : nothing
     * Parameters : @op1 The x of quad, @op2 the y of quad,
     *              @label the label to jump to
     */
    private void generateLessFinalCode(String op1, String op2, String label) {
        /* Load operators */
        load("eax", op1);
        load("edx", op2);

        /* Add it to the list */
        this.finalCode.add(new asCommand("\t", "cmp", "eax", "edx"));

        /* jump to label */
        this.finalCode.add(new asCommand("\t", "jl", label, ""));
    }

    /* Returns : nothing
     * Parameters : @label the label to jump to
     */
    private void generateJumpFinalCode(String label) {

        /* jump to label */
        this.finalCode.add(new asCommand("\t", "jmp", label, ""));
    }

}