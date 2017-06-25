package compiler.fianlCode;

import compiler.intermediateCode.*;
import compiler.semanticAnalysis.*;
import compiler.types.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
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

            if (operation.equals(""))  // For labels only
                return this.label;
            else if (operand2.equals(""))  // For sort commands
                return this.label + " " + this.operation + " " + this.operand1;
            else  // for sort full
                return this.label + " " + this.operation + " " + this.operand1 + ", " + this.operand2;
        }
    }

    /* Needed data for final code production */
    private static int counter = 0;
    private static String filename = "out.s";
    private static LinkedList<String> dataPartOfAssembly = new LinkedList<String>();
    private static LinkedList<String> labelsOfData = new LinkedList<String>();
    private String mainFuncName;
    private SymbolTable copyOfST;              // A copy of the symbol table
    private LinkedList<asCommand> finalCode;   // The assembly commands in a list
    private FunctionInfo currentFunctionInfo;  // The info for the current function
    private IntermediateCode interCodeObj;     // The intermediate code object to convert to final code

    public FinalCode (SymbolTable st, FunctionInfo currentFn, IntermediateCode copyOfIdObj, String mainFunc){
        this.copyOfST = st;
        this.finalCode = new LinkedList<asCommand>();
        this.currentFunctionInfo = currentFn;
        this.interCodeObj = copyOfIdObj;
        this.mainFuncName = mainFunc;
    }

    public static int addData(String str) {
        dataPartOfAssembly.add(str);
        labelsOfData.add("grc_str" + counter);
        counter++;
        return counter;
    }


    public  void writeToFile() {
        boolean firstWrite = true;

        /* If its the first time to write to file then up a flag */
        File f = new File(this.filename);
        if(f.exists()) {
            firstWrite = false;
        }

        try(FileWriter fw = new FileWriter(this.filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            /* If its the first time then we have to write some things */
            if (firstWrite) {
                out.println(".intel_syntax noprefix # Use Intel syntax");
                out.println(".text");
                out.println("\t.global main");
                out.println("main:");
                out.println("push ebp\nmov ebp, esp");
                out.println("sub esp, 4\nmov eax, 0\npush eax");
                out.println("call grc_" + this.mainFuncName);
                out.println("add esp, 8");
                out.println("mov esp, ebp\npop ebp\nret\n");

            }

            for (asCommand item : this.finalCode){
                out.println(item.toString());
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void writeDataPartToFile() {

        /* Try to write to file */
        try(FileWriter fw = new FileWriter(this.filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {

            /* write the data part */
            out.println(".data");
            for (int i= 0; i < dataPartOfAssembly.size(); i++) {
                out.println(labelsOfData.get(i)+": .asciz " + dataPartOfAssembly.get(i));
            }


        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /* +----------------------------------------------+
     * | Helping functions for String of intermediate |
     * |            code to Final code                |
     * +----------------------------------------------+ */

    /* Returns : True in case its a built in func , else false
     * Parameters : @funcName is the name of the func we want to check
     */
    private boolean isBuiltInFunc(String funcName) {

        return funcName.equals("puti ") || funcName.equals("putc ") || funcName.equals("puts ") ||
                funcName.equals("geti ") || funcName.equals("getc ") || funcName.equals("gets ") ||
                funcName.equals("abs ") || funcName.equals("ord ") || funcName.equals("chr ") ||
                funcName.equals("strlen ") || funcName.equals("strcmp ") || funcName.equals("strcpy ") ||
                funcName.equals("strcat ");
    }

    /* Returns : Nothing
     * Parameters : Nothing
     * Updates the stack indicies of the parameters
     */
    public void updateParamsStackIndex() {
        int parametersStackIndex = 12;  // after access link (4) and return address (4)

        /* The func we want to get it's local var size is the current func */
        FuncDefInfo curDefInfo = (FuncDefInfo) this.currentFunctionInfo;

        System.out.println("Function " + this.currentFunctionInfo.getName() + " params :");

        /* In case function has a return stmt the first available stack index goes to RET
         * par that it was passed to that function */
        if (!this.currentFunctionInfo.getType().isNothing()) {
            parametersStackIndex += 4;
        }

        /* Update function's parameters */
        for (VariableInfo varInfo : curDefInfo.getArguments()) {

            varInfo = (VariableInfo) this.copyOfST.lookup(varInfo.getName().toString(), null).getInfo();

            System.out.print("\tparam " + varInfo.getName());

            System.out.println(" idx " + parametersStackIndex);
            varInfo.setStackIndex(parametersStackIndex);
            parametersStackIndex += 4;
        }

        System.out.println("Function " + this.currentFunctionInfo.getName() + "local vars:");
    }

    /* Returns : Nothing
     * Parameters : @interCodeList is a list of intermediate code to find each
     *              temp variable used in that code
     */
    public void updateLTVarsStackIndex(LinkedList<Quads> interCodeList) {
        int localVarStackIndex   = -4;

        /* The func we want to get it's local var size is the current func */
        FuncDefInfo curDefInfo = (FuncDefInfo) this.currentFunctionInfo;

        System.out.println("Function " + this.currentFunctionInfo.getName() + "local vars:");

        /* For each var get the size depending on type of var */
        for (VariableInfo varInfo : curDefInfo.getLocalVariables()){

            varInfo = (VariableInfo) this.copyOfST.lookup(varInfo.getName().toString(), null).getInfo();

            System.out.print("\tLocal var " + varInfo.getName());

            /* Update the each var's stack index depending on var's type */
            if (varInfo.getType().isInt() || varInfo.getType().isChar()) {
                System.out.println(" idx " + localVarStackIndex);
                varInfo.setStackIndex(localVarStackIndex);
                localVarStackIndex -= 4;
            }
            else if (varInfo.getType().isArray()){

                /* Get the array's dimensions */
                int prod = 1;
                LinkedList<Integer> list = new LinkedList<Integer>();
                varInfo.getType().getDimentions(list);

                /* Get the production of all its dims */
                for(Integer dim : list) {
                    prod *= dim;
                }

                /* Update the index */
                System.out.println(" idx " + localVarStackIndex);
                varInfo.setStackIndex(localVarStackIndex);
                localVarStackIndex -= 4*prod;
            }
        }

        /* Now we have to get the size of all the temp variables */
        LinkedList<String> tempVarsRecognized = new LinkedList<String>();
        for (Quads quad : interCodeList) {


            if (quad.getOpName().equals("par") && quad.getY().equals("RET")) {

                System.out.print("\tTemp var " + quad.getX());
                System.out.println(" idx " + localVarStackIndex);

                /* Update stack index */
                this.interCodeObj.putTempIndex(Integer.parseInt(quad.getX().substring(1)), localVarStackIndex);
                localVarStackIndex -= 4;
            }

            /* Get the Z part and get the type of the temp var , if already recognized , continue */
            if (isDoubleDollar(quad.getZ()) || quad.getZ().equals("") ||
                    !isTempVariable(quad.getZ()) || tempVarsRecognized.indexOf(quad.getZ()) != -1
                    )
                continue;

            System.out.print("\tTemp var " + quad.getZ());
            System.out.println(" idx " + localVarStackIndex);

            /* Update stack index */
            this.interCodeObj.putTempIndex(Integer.parseInt(quad.getZ().substring(1)), localVarStackIndex);
            localVarStackIndex -= 4;

            /* Update temVarsRecognized */
            tempVarsRecognized.add(quad.getZ());
        }
    }

    private int getParametersSize(String calleefunction) {
        int parametersSize = 0;

        /* The func we want to get it's parameters size */
        SymbolTableEntry funcDefEntry =  this.copyOfST.lookup(calleefunction, null);

        if (funcDefEntry == null) {
            throw new RuntimeException("Function " + calleefunction + " cannot be found when creating finalCode");
        }

        /* If not null get it's info */
        FunctionInfo funcInfo = (FunctionInfo) funcDefEntry.getInfo();

        System.out.println("Function's " + funcInfo.getName() + " params: ");

        /* Get it's parameters type's */
        for (VariableInfo varInfo : funcInfo.getArguments()) {
            System.out.println("\tParameter :" + varInfo.getName());

            /* Update the parametersSize depending on var's type */
            if (varInfo.getType().isInt() || varInfo.getType().isArray())
                parametersSize += 4;
            else if (varInfo.getType().isChar())
                parametersSize += 1;
        }


        return parametersSize;
    }

    /* Returns : The total size of the temp variables in the func
     * Parameters : @funcName is the name of the func
     */
    private int getLocalVarSize(LinkedList<Quads> interCodeList) {
        int totalSize = 0;

        /* The func we want to get it's local var size is the current func */
        FuncDefInfo curDefInfo = (FuncDefInfo) this.currentFunctionInfo;
        System.out.println("Function " + this.currentFunctionInfo.getName() + "local vars:");

        /* For each var get the size depending on type of var */
        for (VariableInfo varInfo : curDefInfo.getLocalVariables()){

            System.out.println("\tLocal var " + varInfo.getName());

            /* Update the total size depending on var's type */
            if (varInfo.getType().isInt() || varInfo.getType().isChar())
                totalSize += 4;
            else if (varInfo.getType().isArray()){

                /* Get the array's dimensions */
                int prod = 1;
                LinkedList<Integer> list = new LinkedList<Integer>();
                varInfo.getType().getDimentions(list);

                /* Get the production of all its dims */
                for(Integer dim : list) {
                    prod *= dim;
                }

                /* Update the index depending on array type */
                if (varInfo.getType().getArrayType().equals("int "))
                    totalSize += 4*prod;
                else
                    totalSize += prod;
            }
        }

        /* Now we have to get the size of all the temp variables */
        LinkedList<String> tempVarsRecognized = new LinkedList<String>();
        for (Quads quad : interCodeList) {

            if (quad.getOpName().equals("par") && quad.getY().equals("RET")) {

                /* Update stack index */
                totalSize +=4;
            }

            /* Get the Z part and get the type of the temp var , if already recognized , continue */
            if (isDoubleDollar(quad.getZ()) || quad.getZ().equals("") ||
                    !isTempVariable(quad.getZ()) || tempVarsRecognized.indexOf(quad.getZ()) != -1
                    )
                continue;


            System.out.println("\tTemp var " + quad.getZ());

            /* update total size*/
            totalSize += 4;

            /* Update temVarsRecognized */
            tempVarsRecognized.add(quad.getZ());
        }

        return totalSize;
    }

    /* Returns : True or False if the str is a int const
     * Parameters : A @str to determine if it's $$
     */
    private boolean isDoubleDollar(String str) {

        return str.equals("$$");
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
        for (int i=0; i < nestingsBack[0] - 1; i++) {
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

            this.finalCode.add(new asCommand("\t", "push", "DWORD PTR [esi + 8]", ""));
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

            char c = data.charAt(1);
            this.finalCode.add(new asCommand("\t", "mov", reg, Integer.toString((int) c)));
            return true;
        }
        else if (isTempVariable(data)) {

            /* Get temp vars type */
            Type tempType = this.interCodeObj.getTempType(Integer.parseInt(data.substring(1)));

            /* Get the stack index of the temp variable */
            variableStackIndex = this.interCodeObj.getTempIndex(Integer.parseInt(data.substring(1)));

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

            /* Determine the stack index */
            variableStackIndex = varInfo.getStackIndex();

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

            /* Determine size */
            if (tempType.isInt() || tempType.isAddress())
                sizeOfVar = "DWORD";
            else if (tempType.isChar())
                sizeOfVar = "BYTE";

            load("edi", data.substring(1, data.length() - 1));
            this.finalCode.add(new asCommand("\t", "mov", reg, sizeOfVar + " PTR [edi]"));
            return true;
        }
        else {

            /* Its the { } case */
            loadAddress(reg, data.substring(2, data.length() - 1));
            return true;

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

            int counter = addData(data);
            this.finalCode.add(new asCommand("\t", "mov", reg, "OFFSET FLAT:grc_str" + Integer.toString(counter - 1)));
            return true;
        }
        else if (isPointerAccess(data)) {
            load(reg, data.substring(1, data.length() - 1));
        }
        else if (isTempVariable(data)) {

            /* Get temp vars type */
            Type tempType = this.interCodeObj.getTempType(Integer.parseInt(data.substring(1)));

            /* Get the stack index of the temp variable */
            variableStackIndex = this.interCodeObj.getTempIndex(Integer.parseInt(data.substring(1)));

            /* Determine size */
            if (tempType.isInt() || tempType.isAddress())
                sizeOfVar = "DWORD";
            else if (tempType.isChar())
                sizeOfVar = "BYTE";

            this.finalCode.add(new asCommand("\t", "lea", reg, sizeOfVar + " PTR [ebp + " + variableStackIndex + "]"));
            return true;
        }
        else if ((varInfo = isVariable(data, variableLocality)) != null) {

            /* Determine the stack index */
            variableStackIndex = varInfo.getStackIndex();

            /* Determine size */
            if (varInfo.getType().isInt() || varInfo.getType().isArray())
                sizeOfVar = "DWORD";
            else if (varInfo.getType().isChar())
                sizeOfVar = "BYTE";

            /* Check if it's parameter , and locality */
            if (variableLocality[0] == 0 && (varInfo.getMethod().equals("val") || varInfo.getMethod().equals("non"))) {
                /* Make assembly code */
                this.finalCode.add(new asCommand("\t", "lea", reg, sizeOfVar + " PTR [ebp + " + variableStackIndex + "]"));
                return true;
            }
            else if (variableLocality[0] == 0 && varInfo.getMethod().equals("ref")) {
                /* Make assembly code */
                this.finalCode.add(new asCommand("\t", "mov", reg,  "DWORD PTR [ebp + " + variableStackIndex + "]"));
                return true;
            }
            else if (variableLocality[0] != 0 && (varInfo.getMethod().equals("val") || varInfo.getMethod().equals("non"))) {
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
        if (isDoubleDollar(data)) {

            /* Replace $$ with the ret pass by variable in index ebp + 12 */
            this.finalCode.add(new asCommand("\t","mov","esi", "DWORD PTR [ebp + 12]"));
            this.finalCode.add(new asCommand("\t","mov","DWORD PTR [esi]", reg));
            return true;
        }
        else if (isTempVariable(data)) {

            /* Get temp vars type */
            Type tempType = this.interCodeObj.getTempType(Integer.parseInt(data.substring(1)));

            /* Get the stack index of the temp variable */
            variableStackIndex = this.interCodeObj.getTempIndex(Integer.parseInt(data.substring(1)));

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
            if (tempType.isAddress())
                sizeOfVar = "DWORD";
            else if (tempType.isArray() && tempType.getArrayType().equals("char "))
                sizeOfVar = "BYTE";

            load("edi", data.substring(1, data.length() - 1));
            this.finalCode.add(new asCommand("\t", "mov", sizeOfVar + " PTR [edi]", reg));
            return true;
        }
        else if ((varInfo = isVariable(data, variableLocality)) != null) {

            /* Determine the stack index */
            variableStackIndex = varInfo.getStackIndex();
            System.out.println("Stack index " + variableStackIndex);

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
                this.finalCode.add(new asCommand("\t", "mov", "esi",  sizeOfVar + " PTR [ebp + " + variableStackIndex + "]"));
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
                this.finalCode.add(new asCommand("\t", "mov", "esi",  sizeOfVar + " PTR [esi + " + variableStackIndex + "]"));
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

        /* Assign stack indices to local variables before producing the final code */
        updateParamsStackIndex();
        updateLTVarsStackIndex(quadsList);


        /* For each quad generate final code */
        for (Quads quad : quadsList) {

            System.out.println("Quad " + quad  + " to :");

            this.finalCode.add(new asCommand("_" + quad.getlabel() + ":", "", "", ""));

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
            else if (opName.equals("array")) {
                generateArrayFinalCode(quad.getX(), quad.getY(), quad.getZ());
            }
            else if (opName.equals("par")) {
                generateParFinalCode(quad.getX(), quad.getY());
            }
            else if (opName.equals("jump")) {
                generateJumpFinalCode(quad.getZ());
            }
            else if (opName.equals("call")) {
                generateCallFinalCode(quad.getZ());
            }
            else if (opName.equals("ret")) {
                generateRetFinalCode();
            }
            else if (opName.equals("unit")) {
                generateUnitFinalCode(quad.getX(), quadsList);
            }
            else if (opName.equals("endu")) {
                generateEnduFinalCode(quad.getX());
            }
        }

        System.out.println("--------------------------------");

        /* write to file */
        writeToFile();

        /* If we are in main ( the last func ) write tha data part */
        if (this.currentFunctionInfo.getName().toString().equals(this.mainFuncName)) {
            writeDataPartToFile();
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
    private void generateArrayFinalCode(String op1, String op2, String temp) {

        /* Load operator 2 which is the num of bytes for the array */
        load("eax", op2);

        /* Get the address of the array */
        loadAddress("ecx", op1);

        /* Add the address and the offset (sub because it goes downwards */
        this.finalCode.add(new asCommand("\t", "sub", "ecx", "eax"));

        /* Store the result to the temp var */
        store("ecx", temp);
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
        this.finalCode.add(new asCommand("\t", "jnz", "_"+label, ""));
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
        this.finalCode.add(new asCommand("\t", "jge", "_"+label, ""));
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
        this.finalCode.add(new asCommand("\t", "jg", "_"+label, ""));
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
        this.finalCode.add(new asCommand("\t", "jle", "_"+label, ""));
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
        this.finalCode.add(new asCommand("\t", "jl", "_"+label, ""));
    }

    /* Returns : nothing
     * Parameters : @label the label to jump to
     */
    private void generateJumpFinalCode(String label) {

        /* jump to label */
        this.finalCode.add(new asCommand("\t", "jmp", "_"+label, ""));
    }

    /* Returns : nothing
     * Parameters : nothing
     * Returns to the end of the current function
     */
    private void generateRetFinalCode() {

        /* jump to label */
        this.finalCode.add(new asCommand("\t", "jmp", "_grc" + this.currentFunctionInfo.getName(), ""));
    }

    /* Returns : nothing
     * Parameters : @label to call
     */
    private void generateCallFinalCode(String calleefunction) {

        /* Get function's parameters size */
        int parameterSize = getParametersSize(calleefunction) + 4;

        /* create access link , if func is not built in */
        if (!isBuiltInFunc(calleefunction))
            updateAL(calleefunction);

        /* call the function */
        this.finalCode.add(new asCommand("\t", "call", "grc_" + calleefunction, ""));
        /* Clear the used space (calling convention) */
        this.finalCode.add(new asCommand("\t", "add", "esp", Integer.toString(parameterSize)));
    }

    /* Returns : nothing
     * Parameters : @op1 is the place of the variable to be passed to
     *              a func by @method.
     */
    private void generateParFinalCode(String op1, String method) {

        if (method.equals("V")) {
            /* Load op1 to parameter */
            load("eax", op1);

            /* Push to stack */
            this.finalCode.add(new asCommand("\t", "push", "eax", ""));
        }
        else {

            System.out.println("Here with " + op1);
            /* Load op1 address cause it's by ref pass, or a return address */
            loadAddress("esi", op1);

            /* Push to stack */
            this.finalCode.add(new asCommand("\t", "push", "esi", ""));
        }
    }

    /* Returns : nothing
     * Parameters : @unitName the label of the unit to
     */
    private void generateUnitFinalCode(String unitName, LinkedList<Quads> interCodelist) {

        /* Look up for the function name and decide the local var size */
        int local_var_size = getLocalVarSize(interCodelist);

        System.out.println("Local size " + local_var_size);

        this.finalCode.add(new asCommand("grc_" + unitName + ":", "", "", ""));
        this.finalCode.add(new asCommand("\t", "push", "ebp", ""));
        this.finalCode.add(new asCommand("\t", "mov", "ebp", "esp"));
        if (local_var_size != 0)
            this.finalCode.add(new asCommand("\t", "sub", "esp", Integer.toString(local_var_size)));
    }

    /* Returns : nothing
     * Parameters : @unitName the label of the unit to
     */
    private void generateEnduFinalCode(String unitName) {

        this.finalCode.add(new asCommand("_grc" + unitName + ":", "mov", "esp", "ebp"));
        /* Pop ebp from stack */
        this.finalCode.add(new asCommand("\t", "pop", "ebp", ""));
        /* Return */
        this.finalCode.add(new asCommand("\t", "ret", "", ""));
        /* Define end of process */
        //this.finalCode.add(new asCommand("grc_" + unitName, "endp", "", ""));
    }

}