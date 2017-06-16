package compiler.semanticAnalysis;

import compiler.analysis.*;
import compiler.node.*;
import compiler.types.*;
import compiler.exceptions.*;
import compiler.intermediateCode.*;
import compiler.fianlCode.*;
import compiler.semanticAnalysis.VariableInfo;
import compiler.semanticAnalysis.SymbolTableEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Stack;

public class SemanticAnalysis extends DepthFirstAdapter {

    int indentation = 0;
    SymbolTable symbolTable; /* The structure of the symbol table */
    private HashMap<Node, Attributes> exprTypes; /* A structure that maps every sablecc generated Node to a type */
    private Stack<TId> currentFunctionId;
    private CompilerErrorList errorList;
    private IntermediateCode intermediateCode;
    private FinalCode finalCode;
    int blockDepth; /* This helps us extinguish function definition blocks for if/while blocks */

    /*
     * The semantic analysis phase starts here
     * We create the symbol table and in its first scope we insert
     * the Grace built in library functions so that they can be
     * called by any function in the program (global scope)
     */
    public SemanticAnalysis() {
        /* Create instances of the member classes */
        this.symbolTable = new SymbolTable();
        this.errorList = new CompilerErrorList();
        
        /* Add the first scope that will hold the built in library functions */
        this.symbolTable.enter();
        this.intermediateCode = new IntermediateCode();

        
        LinkedList<VariableInfo> argList; /* A list containing the arguments to each function */
        LinkedList<String> passBy;        /* A list containing the pass method (by reference / by value) of each argument */
        SymbolTableEntry data;            /* An object that is inserted in the symbol table for each function */

        currentFunctionId = new Stack<TId>();        /* Stack to save the id of the current function */
        exprTypes = new HashMap<Node, Attributes>(); /* Create the HashMap for the type checking */

        /* fun puti (n : int) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int ", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing "), argList, passBy));
        this.symbolTable.insert("puti ", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun putc (c : char) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("c", "char ", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing "), argList, passBy));
        this.symbolTable.insert("putc ", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun puts (ref s : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("s", "char ", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing "), argList, passBy));
        this.symbolTable.insert("puts ", data);
        data    = null;
        argList = null;
        passBy  = null;
    
        /* fun geti () : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* No arguments to be added to the list */
        data = new SymbolTableEntry(new FunctionInfo("int ", argList, passBy));
        this.symbolTable.insert("geti ", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun getc () : char */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* No arguments to be added to the list */
        data = new SymbolTableEntry(new FunctionInfo(new String("char "), argList, passBy));
        this.symbolTable.insert("getc ", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun gets (n : int, ref s : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("n", "int ", false));
        passBy.add("val");
        argList.add(new VariableInfo("s", "char ", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing "), argList, passBy));
        this.symbolTable.insert("gets ", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun abs (n : int) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int ", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("int "), argList, passBy));
        this.symbolTable.insert("abs ", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun ord (c : char) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("c", "char ", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("int "), argList, passBy));
        this.symbolTable.insert("ord ", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun chr (n : int) : char */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int ", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("char "), argList, passBy));
        this.symbolTable.insert("chr ", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strlen (ref s : char[]) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("s", "char ", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("int "), argList, passBy));
        this.symbolTable.insert("strlen ", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strcmp (ref s1, s2 : char[]) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("s1", "char ", true));
        passBy.add("ref");
        argList.add(new VariableInfo("s2", "char ", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("int "), argList, passBy));
        this.symbolTable.insert("strcmp ", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun strcpy (ref trg, src : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("trg", "char ", true));
        passBy.add("ref");
        argList.add(new VariableInfo("src", "char ", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing "), argList, passBy));
        this.symbolTable.insert("strcpy ", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strcat (ref trg, src : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("trg", "char ", true));
        passBy.add("ref");
        argList.add(new VariableInfo("src", "char ", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing "), argList, passBy));
        this.symbolTable.insert("strcat ", data);
        data    = null;
        argList = null;
        passBy  = null;
    }

    public CompilerErrorList getErrors() {
        return this.errorList;
    }

    /* For debugging reasons */
    private void addIndentationLevel() {
        indentation++;
    }

    private void printIndentation() {
        System.out.print(String.join("", Collections.nCopies(indentation, "   ")));
    }

    private void indentNprint(String msg) {
        printIndentation();
        System.out.println(msg);
    }
    /*-----------------------*/

    @Override
    public void inAFuncDec(AFuncDec node) {
        
        /* Create a SymbolTableEntry object to pass to the insert function */
        FunctionInfo info = null;
        try {
            info = new FuncDecInfo((PDataType) node.getRetType(), node.getFplist(), node.getId());
        }
        catch (TypeCheckingException e){
            
            /* Add error to list an continue */
            this.errorList.addToList(e.getMessage());
            throw e;
        }
        
        SymbolTableEntry data = new SymbolTableEntry(info);
        this.symbolTable.insert(node.getId().toString(), data);

        addIndentationLevel();
    }

    @Override
    public void inAFuncDef(AFuncDef node) {
        /* Check for a function declaration in the same nesting */
        SymbolTableEntry funcDec = this.symbolTable.lookup(node.getId().toString(), null);
        
        if (funcDec != null) {
            /*
             * If a function definition or variable with the same name
             * as the function in node was found then raise an exception
             */
            if (!(funcDec.getInfo() instanceof FuncDecInfo)) {
                throw new SemanticAnalysisException(node.getId().getLine(), node.getId().getPos(),
                        "Function with name " + node.getId().toString() + " can't be used, is already in use");
            }
            else if(((FuncDecInfo) funcDec.getInfo()).getFuncDefined() == true){
                /* In case the function declaration was already matched with another function definition */
                throw new SemanticAnalysisException(node.getId().getLine(), node.getId().getPos(),
                        "Function with name " + node.getId().toString() + " can't be used, is already defined");
            }

            /*
             * A non matched declaration has been found
             * Update its flag to indicate that it has been matched
             */
            ((FuncDecInfo) funcDec.getInfo()).setFuncDefined(true); 
        }
        else if ((this.symbolTable.getIsMainDefined()) == true) {
            /*
             * If we have a non matched function definition (except for main)
             * add a declaration to the current scope, before making a new scope,
             * so that it will be visible to function calls in the new scope
             */
            FuncDecInfo type = null;
            try {
                type = new FuncDecInfo((PDataType) node.getRetType(), node.getFplist(), node.getId());
            }
            catch (TypeCheckingException e) {
                /* Add error to list an continue in the ast */
                this.errorList.addToList(e.getMessage());
                throw e;
            }

            SymbolTableEntry data = new SymbolTableEntry(type);

            /* Function is already matched because it only has a definition */
            type.setFuncDefined(true);

            this.symbolTable.insert(node.getId().toString(), data);
        }

        /* Check if this is the definition of the main function */
        if ((this.symbolTable.getIsMainDefined()) == false) {
            /* The main function should have no arguments and returns nothing */
            if (node.getFplist() instanceof AExistingFparList) {
                throw new TypeCheckingException(0, 0, "Main function should have no parameters");
            }

            if (!(((PDataType) node.getRetType()).toString()).equals(new String("nothing "))) {                
                throw new TypeCheckingException(0, 0, "Main function should return nothing");
            }

            this.symbolTable.setIsMainDefined();
        }

        /* Create the info for the function definition*/
        FunctionInfo funcDefInfo = null;
        try {
            funcDefInfo = new FuncDefInfo((PDataType) node.getRetType(), node.getFplist(), node.getId());
        }
        catch (TypeCheckingException e) {
            /* Add error to list an continue */
            this.errorList.addToList(e.getMessage());
            return;
        }

        /* Check if the definition is equivalent with the declaration */
        if (funcDec != null) {
            try {
                ((FunctionInfo) funcDec.getInfo()).isEquivWith(funcDefInfo);
            }
            catch (TypeCheckingException e) {
                /* Add error to list an continue */
                this.errorList.addToList(e.getMessage());
                throw e;
            }
        }

        /* A new function definition block begins */
        blockDepth = 0;

        /* In every new func_def we create a new scope */
        this.symbolTable.enter();

        /* Create a SymbolTableEntry object to pass to the insert function */
        SymbolTableEntry data = new SymbolTableEntry(funcDefInfo);

        /* Insert the function definition */
       this.symbolTable.insert(node.getId().toString(), data);

        /* Insert the function's arguments to the symbol table */
        for (int var = 0;  var < ((FunctionInfo) data.getInfo()).getArguments().size(); var++) {
            this.symbolTable.insert(((FunctionInfo) data.getInfo()).getArguments().get(var).getName().toString(),
                new SymbolTableEntry(new VariableInfo(((FunctionInfo) data.getInfo()).getArguments().get(var))));
        }
        
        /* Initialize current Function so we can use in on the lower levels of the AST */
        currentFunctionId.push(node.getId());
    }

    @Override
    public void outAFuncDef(AFuncDef node) {
        /* Get function definition form symbol table */
        SymbolTableEntry funcDef = this.symbolTable.lookup(node.getId().toString(), null);

        /* Check if the function has been matched to a return statement */        
        if (!(funcDef.getInfo().getType().isNothing())) {
            if (!(((FuncDefInfo) funcDef.getInfo()).getIsMatchedToReturnStmt())) {
                    throw new SemanticAnalysisException(node.getId().getLine(), node.getId().getPos(),
                            "Function: \"" + node.getId().getText() + "\" has no return statement");
            }
        }

        /* When exiting from a function exit from the current scope too */
        this.symbolTable.exit();

        /* We don't need the function's id anymore */
        currentFunctionId.pop();
    }

    @Override
    public void inAVarDef(AVarDef node) {
        /* Get the current FunctionInfo */
        SymbolTableEntry currentFunctionEntry = this.symbolTable.lookup(currentFunctionId.peek().toString(), null);
        FuncDefInfo currentFuncDef = (FuncDefInfo) currentFunctionEntry.getInfo();

        /* Get the type of the variables in the current definition */
        PType type = (PType) ((AType) node.getType()).clone();

        /* Extract every variable from a multi-variable definition and save their types */
        for (int varnum = 0; varnum < node.getIdList().size(); varnum++) {
            /* Add all the variables in the symbol table */
            VariableInfo v = new VariableInfo(node.getIdList().get(varnum), (AType) type);
            SymbolTableEntry data = new SymbolTableEntry(v);

            if (this.symbolTable.insert(node.getIdList().get(varnum).toString(), data) == false) {
                throw new SemanticAnalysisException(v.getName().getLine(), v.getName().getPos(),
                        "Conflicting types: name \"" + v.getName().getText() + "\" already exists");
            }

            /* Add the variable to the function's definition list that holds local variables */
            currentFuncDef.addLocalVariable(v);

            /* Print each variable */
            //indentNprint("Name :" + v.getName());
            //indentNprint("Type :" + v.getType());
            //indentNprint("Int ?:" + v.getType().isInt());
        }

        //for (int a = 0 ; a < currentFuncDef.getLocalVariables().size(); a++)
            //System.out.println("Lists :" + currentFuncDef.getLocalVariables().get(a).getName());
    }

    @Override
    public void inABlockStmt(ABlockStmt node) {
        /*
         * This is the start of the main block of a function definition
         * so we create a unit quad
         */
        if (blockDepth == 0) {
            /* In every block create a new final Code block - Intermediate code block at the end we are going to produce it */
            System.out.println("Current Function is " + this.currentFunctionId.peek().toString());
            this.finalCode = new FinalCode(this.symbolTable,
                    (FunctionInfo) this.symbolTable.lookup(this.currentFunctionId.peek().toString(), null).getInfo()
                    );

            this.intermediateCode.genQuad("unit", currentFunctionId.peek().toString(), "-", "-");
        }


        blockDepth++;
    }

    @Override
    public void outABlockStmt(ABlockStmt node) {
        blockDepth--;

        /*
         * This is the end of the main block of a function definition
         * so we create a endu quad
         */
        if (blockDepth == 0) {
            /* Get the current FunctionInfo */
            SymbolTableEntry currentFunctionEntry = this.symbolTable.lookup(currentFunctionId.peek().toString(), null);

            /*
             * If the function definition has not been matched to a return statement
             * we have to generate a ret Quad manually
             */
            if (((FuncDefInfo) currentFunctionEntry.getInfo()).getIsMatchedToReturnStmt() == false) {
                this.intermediateCode.genQuad("ret", "-", "-", "-");
            }
            this.intermediateCode.genQuad("endu", currentFunctionId.peek().toString(), "-", "-");

            /* Produce the Functions's final code */
            //this.finalCode.intermediateToFinalCode(this.intermediateCode.getQuadsList());
        }
    }

    @Override
    public void caseABlockStmt(ABlockStmt node) {
        /* For intermediate code */
        LinkedList<Integer> nextList = null;
        int loopTimes = 0;

        inABlockStmt(node);
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getBody());
            for (PStmt e : copy) {
                /* BackPatch */
                if (loopTimes != 0) /* Don't backpatch in the first loop, there is no first stmt code */
                    this.intermediateCode.backPatch(nextList, Quads.nextQuad());
                
                e.apply(this);
                
                /* Get e's next list to back patch it */
                nextList = exprTypes.get(e).getNext();
                
                loopTimes++;
            }
        }
        outABlockStmt(node);

        Attributes nodeAttributes = new Attributes(BuiltInType.Void);

        /* Make the blocks next */
        nodeAttributes.setNext(nextList);

        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void caseAIfStmt(AIfStmt node) {
        inAIfStmt(node);
        if (node.getKwIf() != null) {
            node.getKwIf().apply(this);
        }

        if (node.getCond() != null) {
            node.getCond().apply(this);
        }

        Attributes condAttributes = exprTypes.get(node.getCond());

        /* Back patch cond's true with next quad */
        this.intermediateCode.backPatch(condAttributes.getTrue(), Quads.nextQuad());
        LinkedList<Integer> l1 = condAttributes.getFalse(); /* If there is no else cond.FALSE must point to ifstmt.NEXT */
        LinkedList<Integer> l2 = new LinkedList<Integer>();

        if (node.getThen() != null) {
            node.getThen().apply(this);
        }

        Attributes thenStmtAttributes = exprTypes.get(node.getThen());

        if (node.getElse() != null) {
            /* Generate intermediate code before the code of stmtElse */
            l1 = new LinkedList<Integer>();
            l1.add(Quads.nextQuad());
            this.intermediateCode.genQuad("jump", "-", "-", "*"); /* make the jump for the thenStmt.Next to pass the elseStmt */ 

            /* In case there is an else, cond.FALSE must point to the elseStmt */
            this.intermediateCode.backPatch(condAttributes.getFalse(), Quads.nextQuad());

            /* Generate code for stmtElse */
            node.getElse().apply(this);

            /* The next of elseStmt should point to the next of thenStmt and jump */
            l2 = exprTypes.get(node.getElse()).getNext();
        }
        outAIfStmt(node);

        Attributes nodeAttributes = new Attributes(BuiltInType.Void);

        /* ifStmt NEXT will be a merged list of thenStmt.NEXT l1 and l2 */
        nodeAttributes.setNext(this.intermediateCode.mergeLists(
                                this.intermediateCode.mergeLists(l1, l2), thenStmtAttributes.getNext()));

        /* put node to hash Map */
        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outAIfStmt(AIfStmt node) {
        Type aCondType = exprTypes.get(node.getCond()).getType();

        if (!(aCondType.isBoolean())) {
            int line = node.getKwIf().getLine();
            int column = node.getKwIf().getPos();
            throw new TypeCheckingException(line, column, "If statement condition should have a boolean type\n" +
                                            node.getCond().toString() + "is not a boolean condition");
        }
    }

    @Override
    public void caseAWhileStmt(AWhileStmt node) {
        inAWhileStmt(node);
        if (node.getKwWhile() != null) {
            node.getKwWhile().apply(this);
        }

        /* Get the condition's quad number */
        Integer condQuad = Quads.nextQuad();
        
        if (node.getCond() != null) {
            node.getCond().apply(this);
        }

        Attributes condAttributes = exprTypes.get(node.getCond());

        /* Backpatch condition's true with next quad */
        this.intermediateCode.backPatch(condAttributes.getTrue(), Quads.nextQuad());

        if (node.getBody() != null) {
            node.getBody().apply(this);
        }
        outAWhileStmt(node);

        /* Get the body's stmt attributes */
        Attributes childStmtAttributes = exprTypes.get(node.getBody());

        /* Make the node's Attributes */
        Attributes stmtAttributes = new Attributes(BuiltInType.Void);

        /* Back patch condition's true with the next quad */
        this.intermediateCode.backPatch(childStmtAttributes.getNext(), condQuad);
        this.intermediateCode.genQuad("jump", "-", "-", condQuad.toString());

        /* Set the next of this stmt to be the cond false */
        stmtAttributes.setNext(condAttributes.getFalse());

        /* Put to hash Map */
        exprTypes.put(node, stmtAttributes);
    }

    @Override
    public void outAWhileStmt(AWhileStmt node) {
        Type aCondType = exprTypes.get(node.getCond()).getType();

        if (!(aCondType.isBoolean())) {
            int line = node.getKwWhile().getLine();
            int column = node.getKwWhile().getPos();
            throw new TypeCheckingException(line, column, "While statement condition should have a boolean type\n" +
                                            node.getCond().toString() + "is not a boolean condition");
        }
    }

    @Override
    public void outAAssignStmt(AAssignStmt node) {
        Attributes assignLhsLval = exprTypes.get(node.getLvalue()); 
        Type assignLhsType = assignLhsLval.getType();

        /* String literals not allowed as lvalues in assignments */
        if (assignLhsType.isArray() && assignLhsType.isEquivWith(BuiltInType.Char)) {
            int line = node.getAssign().getLine();
            int column = node.getAssign().getPos();
            throw new TypeCheckingException(line, column, "Left hand side of an assignment can not be a string literal");
        }

        Attributes assignRhsExpr = exprTypes.get(node.getExpr());
        Type assignRhsType = assignRhsExpr.getType();

        if (!(assignLhsType.isEquivWith(assignRhsType))) {
            int line = node.getAssign().getLine();
            int column = node.getAssign().getPos();
            throw new TypeCheckingException(line, column, "Both sides of an assignment should have the same type");
        }
        
        /* Intermediate Code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Void);
        this.intermediateCode.genQuad(":=", assignRhsExpr.getPlace(), "-", assignLhsLval.getPlace());
        nodeAttributes.makeEmptyList("Next");
        
        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outAReturnStmt(AReturnStmt node) {
        Type aExprType = exprTypes.get(node.getExpr()).getType();

        /* Search the function this return statement corresponds to */
        SymbolTableEntry currentFunctionEntry = this.symbolTable.lookup(currentFunctionId.peek().toString(), null);

        if (!(aExprType.isEquivWith(currentFunctionEntry.getInfo().getType()))) {
            int line = node.getKwReturn().getLine();
            int column = node.getKwReturn().getPos();
            throw new TypeCheckingException(line, column, "Type of returned expression does not match return type of function:"
                                            + currentFunctionId.peek().getText() + "\n"
                                            + "Return type is " + currentFunctionEntry.getInfo().getType());
        }
        
        /* Function definition matched to a return statement */
        ((FuncDefInfo) currentFunctionEntry.getInfo()).setIsMatchedToReturnStmt(true);

        /* Intermediate Code */
        this.intermediateCode.genQuad(":=", exprTypes.get(node.getExpr()).getPlace(), "-", "$$");
        this.intermediateCode.genQuad("ret", "-", "-", "-");

        /* Create nodes attributes */
        Attributes nodeAttributes = new Attributes(BuiltInType.Void);
        nodeAttributes.makeEmptyList("Next");

        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outANoopStmt(ANoopStmt node) {
        /* Intermediate Code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Void);
        nodeAttributes.makeEmptyList("Next");
        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outAAddExpr(AAddExpr node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* Addition can be applied to integers only */
        if (!(leftExprType.isInt())) {
            int line = node.getPlus().getLine();
            int column = node.getPlus().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be added\n" +
                                            node.getL().toString() + "is not an integer");
        }

        if (!(rightExprType.isInt())) {
            int line = node.getPlus().getLine();
            int column = node.getPlus().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be added:\n" +
                                            node.getR().toString() + "is not an integer");
        }

        /* Intermediate Code */
        String temp = this.intermediateCode.newTemp(BuiltInType.Int);
        String op1  = exprTypes.get(node.getL()).getPlace();
        String op2  = exprTypes.get(node.getR()).getPlace();

        this.intermediateCode.genQuad("+", op1, op2, temp);

        exprTypes.put(node, new Attributes(BuiltInType.Int, temp));
    }

    @Override
    public void outASubExpr(ASubExpr node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* Subtraction can be applied to integers only */
        if (!(leftExprType.isInt())) {
            int line = node.getMinus().getLine();
            int column = node.getMinus().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be subtracted\n" +
                                            node.getL().toString() + "is not an integer");
        }

        if (!(rightExprType.isInt())) {
            int line = node.getMinus().getLine();
            int column = node.getMinus().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be subtracted:\n" +
                                            node.getR().toString() + "is not an integer");
        }
        
        /* Intermediate Code */
        String temp = this.intermediateCode.newTemp(BuiltInType.Int);
        String op1  = exprTypes.get(node.getL()).getPlace();
        String op2  = exprTypes.get(node.getR()).getPlace();

        this.intermediateCode.genQuad("-", op1, op2, temp);

        exprTypes.put(node, new Attributes(BuiltInType.Int, temp));
    }

    @Override
    public void outAMultExpr(AMultExpr node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* Multiplication can be applied to integers only */
        if (!(leftExprType.isInt())) {
            int line = node.getMult().getLine();
            int column = node.getMult().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be multiplicated\n" +
                                            node.getL().toString() + "is not an integer");
        }

        if (!(rightExprType.isInt())) {
            int line = node.getMult().getLine();
            int column = node.getMult().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be multiplicated:\n" +
                                            node.getR().toString() + "is not an integer");
        }

        /* Intermediate Code */
        String temp = this.intermediateCode.newTemp(BuiltInType.Int);
        String op1  = exprTypes.get(node.getL()).getPlace();
        String op2  = exprTypes.get(node.getR()).getPlace();

        this.intermediateCode.genQuad("*", op1, op2, temp);

        exprTypes.put(node, new Attributes(BuiltInType.Int, temp));
    }

    @Override
    public void outADivExpr(ADivExpr node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* Division can be applied to integers only */
        if (!(leftExprType.isInt())) {
            int line = node.getKwDiv().getLine();
            int column = node.getKwDiv().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be divided\n" +
                                            node.getL().toString() + "is not an integer");
        }

        if (!(rightExprType.isInt())) {
            int line = node.getKwDiv().getLine();
            int column = node.getKwDiv().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be divided:\n" +
                                            node.getR().toString() + "is not an integer");
        }

        /* Intermediate Code */
        String temp = this.intermediateCode.newTemp(BuiltInType.Int);
        String op1  = exprTypes.get(node.getL()).getPlace();
        String op2  = exprTypes.get(node.getR()).getPlace();

        this.intermediateCode.genQuad("/", op1, op2, temp);

        exprTypes.put(node, new Attributes(BuiltInType.Int, temp));
    }

    @Override
    public void outAModExpr(AModExpr node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* Modulo can be applied to integers only */
        if (!(leftExprType.isInt())) {
            int line = node.getKwMod().getLine();
            int column = node.getKwMod().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be modulo-ed\n" +
                                            node.getL().toString() + "is not an integer");
        }

        if (!(rightExprType.isInt())) {
            int line = node.getKwMod().getLine();
            int column = node.getKwMod().getPos();
            throw new TypeCheckingException(line, column, "Only integers can be modulo-ed:\n" +
                                            node.getR().toString() + "is not an integer");
        }

        /* Intermediate Code */
        String temp = this.intermediateCode.newTemp(BuiltInType.Int);
        String op1  = exprTypes.get(node.getL()).getPlace();
        String op2  = exprTypes.get(node.getR()).getPlace();

        this.intermediateCode.genQuad("%", op1, op2, temp);

        exprTypes.put(node, new Attributes(BuiltInType.Int, temp));

    }

    @Override
    public void outAPosExpr(APosExpr node) {
        Type aExprType = exprTypes.get(node.getExpr()).getType();

        /* A positive sign can be applied to integers only */
        if (!(aExprType.isInt())) {
            int line = node.getPlus().getLine();
            int column = node.getPlus().getPos();
            throw new TypeCheckingException(line, column, "Positive sign applied to invalid expression: "
                                            + node.getExpr().toString() + "\nExpression should be integer");
        }

        exprTypes.put(node, new Attributes(BuiltInType.Int, exprTypes.get(node.getExpr()).getPlace()));
    }

    @Override
    public void outANegExpr(ANegExpr node) {
        Type aExprType = exprTypes.get(node.getExpr()).getType();

        /* A negative sign can be applied to integers only */
        if (!(aExprType.isInt())) {
            int line = node.getMinus().getLine();
            int column = node.getMinus().getPos();
            throw new TypeCheckingException(line, column, "Negative sign applied to invalid expression: "
                                            + node.getExpr().toString() + "\nExpression should be integer");
        }

        /* Intermediate Code */
        String temp = this.intermediateCode.newTemp(BuiltInType.Int);
        String op1  = "-1";
        String op2  = exprTypes.get(node.getExpr()).getPlace();
        
        this.intermediateCode.genQuad("*", op1, op2, temp);

        exprTypes.put(node, new Attributes(BuiltInType.Int, temp));
    }

    @Override
    public void outAIntExpr(AIntExpr node) {
        String intStr = node.getIntConst().toString();
        intStr = intStr.substring(0, intStr.length()-1); /* Remove the reduntant space created by the parser at the end */

        /* Convert the string to an integer */
        try {
            Integer.parseInt(intStr);
        }
        catch (NumberFormatException e) {
            int line = node.getIntConst().getLine();
            int column = node.getIntConst().getPos();
            throw new TypeCheckingException(line, column, "Invalid integer constant: " + intStr);
        }

        exprTypes.put(node, new Attributes(BuiltInType.Int, node.getIntConst().getText()));
    }

    @Override
    public void outACharExpr(ACharExpr node) {
        exprTypes.put(node, new Attributes(BuiltInType.Char, node.getCharConst().getText()));
    }

    @Override
    public void outALvalExpr(ALvalExpr node) {
        Type type = exprTypes.get(node.getLvalue()).getType();
        if (type != null) {
            exprTypes.put(node, new Attributes(type, exprTypes.get(node.getLvalue()).getPlace()));
        }
    }

    @Override
    public void caseAFuncCall(AFuncCall node) {
        inAFuncCall(node);

        if (node.getId() != null) {
            node.getId().apply(this);
        }

        /* Search for the function declaration in the symbol table */
        SymbolTableEntry funcDec = this.symbolTable.lookup(node.getId().toString(), null);
        int n = 1;

        {
            List<PExpr> copy = new ArrayList<PExpr>(node.getExprList());
            for(PExpr e : copy) {
                e.apply(this);

                /* Create a "par" Quad for every argument */
                if (funcDec != null) {
                    this.intermediateCode.genQuad("par", exprTypes.get(e).getPlace(),
                                                ((FunctionInfo) funcDec.getInfo()).paramMode(n), "-");
                }
                n++;
            }
        }
        outAFuncCall(node);

        /* Create attributes */
        Attributes nodeAttributes = new Attributes(((FunctionInfo) funcDec.getInfo()).getType());

        /* Create an empty list */
        nodeAttributes.makeEmptyList("Next");

        /* Create a Quad for the function's return value */
        if ((((FunctionInfo) funcDec.getInfo()).getType()).isEquivWith(BuiltInType.Nothing)) {
            String w = this.intermediateCode.newTemp(((FunctionInfo) funcDec.getInfo()).getType());
            System.out.println(w);
            this.intermediateCode.genQuad("par", w, "RET", "-");

            nodeAttributes.setPlace(w);
        }

        this.intermediateCode.genQuad("call", "-", "-", node.getId().toString());
        exprTypes.put(node.parent(), nodeAttributes);
    }

    @Override
    public void outAFuncCall(AFuncCall node) {
        /* Get the declaration from the symbol table */
        SymbolTableEntry funcDec = this.symbolTable.lookup(node.getId().toString(), null);

        /* If the function is not declared throw an exception */
        if (funcDec == null) {
            throw new SemanticAnalysisException(node.getId().getLine(), node.getId().getPos(),
                    "Calling function \"" + node.getId().getText() + "\": function is not declared or defined");
        }

        /* Equivalence check with declaration */
        FunctionInfo funcInfo = (FunctionInfo) funcDec.getInfo();

        System.out.println(node.getExprList().size());

        /* First check for equal number of arguments */
        Type funcDecExprType  = null;
        Type funcCallExprType = null;
        if (funcInfo.getArguments().size() == node.getExprList().size() && node.getExprList().size() > 0) {
            /* Check every expression with its equivalent argument */
            for (int arg = 0; arg < node.getExprList().size(); arg++) {
                /* Get the function declaration's arguments type and function call's expressions' type */
                funcDecExprType = ((FunctionInfo) funcDec.getInfo()).getArguments().get(arg).getType();
                funcCallExprType = exprTypes.get(node.getExprList().get(arg)).getType();

                if (!funcDecExprType.isEquivWith(funcCallExprType)) {
                    TId name  = node.getId();
                    Node expr = node.getExprList().get(arg);
                    throw new TypeCheckingException(name.getLine(), name.getPos(),
                            "In function \"" + name.getText() + "\": calling with expression: \"" +
                            expr.toString() + "\" with type incompatible type " + funcCallExprType.toString() + 
                            "\nwhen declaration type  is " + funcDecExprType.toString());
                }
            }
        }
        else if (funcInfo.getArguments().size() != node.getExprList().size()){
            TId name  = node.getId();
            throw new TypeCheckingException(name.getLine(), name.getPos(),
                "Calling function \"" + name.getText() + "\": wrong number of arguments provided");
        }

        //exprTypes.put(node.parent(), new Attributes(funcInfo.getType()));
    }

    @Override
    public void outAStrLvalue(AStrLvalue node) {
        LinkedList<Integer> strLength = new LinkedList<Integer>();
        /* We subtract 3 from the length to account for the "" and the space at the end */
        strLength.add(node.getStringLiteral().toString().length() - 3);

        exprTypes.put(node, new Attributes(new ComplexType("array", strLength, "char "), node.getStringLiteral().getText()));
    }

    @Override
    public void outAIdLvalue(AIdLvalue node) {
        SymbolTableEntry anId = this.symbolTable.lookup(node.getId().toString(), null);

        /* If the id was not found in the symbol table throw an exception */
        if (anId == null) {
            int line = node.getId().getLine();
            int column = node.getId().getPos();
            throw new TypeCheckingException(line, column, "Undefined indentifier: " + node.getId().toString());
        }

        System.out.println(anId.getInfo().getType().toString());

        exprTypes.put(node, new Attributes(anId.getInfo().getType(), node.getId().toString()));
    }
    
    public TId recArrayIdFinder(AArrayLvalue node, LinkedList<Integer> dimList, LinkedList<String> dimPlaces) {
        if (exprTypes.get(node.getExpr()) == null) {
            System.out.println("Error");
        }
        String dimPlace = exprTypes.get(node.getExpr()).getPlace();

        /* Keep the place of each expression */
        dimPlaces.add(dimPlace);

        /* Add the size to the linked list and call again */
        dimList.add(0);

        /* There is no case for a AStrLvalue because an exception would have already been thrown */
        if (node.getLvalue() instanceof AIdLvalue) {
            return ((AIdLvalue) node.getLvalue()).getId();
        }

        return recArrayIdFinder((AArrayLvalue) node.getLvalue(), dimList, dimPlaces); 
    }

    @Override 
    public void outAArrayLvalue(AArrayLvalue node) {
        /* If the nested lvalue is string_literal then throw error */
        if (node.getLvalue() instanceof AStrLvalue) {
            AStrLvalue strLval = (AStrLvalue) node.getLvalue();
            int line = strLval.getStringLiteral().getLine();
            int column = strLval.getStringLiteral().getPos();
            throw new TypeCheckingException(line, column, "Invalid action: using string literal \""
                                            + strLval.toString() + "\" as array type");
        }

        /* If the expression is non integer then throw an error */
        Type aExprType = exprTypes.get(node.getExpr()).getType();
        if (!aExprType.isInt()) {
            int line = node.getLBracket().getLine();
            int column = node.getLBracket().getPos() + 1; /* The error occurs after the [ token but we use it to help us get column */
            throw new TypeCheckingException(line, column, "Invalid action: using string literal \""
                                            + node.getExpr().toString() + "\" to dereference array. Expression should be integer");
        }

        /* Use recursive function to get the idName (at the highest lvalueArray node on the AST) */
        if (!(node.parent() instanceof AArrayLvalue)) {
            TId arrayName = null;
            LinkedList<Integer> list = new LinkedList<Integer>();
            LinkedList<String> placesList = new LinkedList<String>();
            arrayName = recArrayIdFinder(node, list, placesList);

            /* Lookup in the symbol table for the array */
            SymbolTableEntry array = this.symbolTable.lookup(arrayName.toString(), null);

            /* Get the Type */
            Type arrayType = array.getInfo().getType();

            /* If the variable is not an array throw an exception */
            if (! arrayType.isArray()) {
                int line = arrayName.getLine();
                int column = arrayName.getPos();
                throw new SemanticAnalysisException(line, column, "Invalid action: identifier \""
                                + arrayName.getText()+ "\" not defined as an array but as " + arrayType.getTypeName());
            }

            /* Make a new type representing the array access (we need this to check the dimension number) */
            Type arrayAccessType = new ComplexType("array", list, arrayType.getArrayType()); 

            if (!arrayType.isEquivWith(arrayAccessType)) {
                int line = arrayName.getLine();
                int column = arrayName.getPos();
                throw new TypeCheckingException(line, column, "Invalid action: array with id \""
                                    + arrayName.getText()+"\" was defined with " + arrayType.getArrayDims() +
                                    " dimension(s) but is used with " + arrayAccessType.getArrayDims() + " dimension(s)");
            }

            /* Intermediate code */
            LinkedList<Integer> dimList = new LinkedList<Integer>();
            String temp1 = null, temp2 = null;
            arrayType.getDimentions(dimList);
            
            /*
             * In the above code we assume that an integer has a size of 4 bytes
             * and a character 1 byte in a 32-bit system
             */

            /* Code for arrays with 1 dimension */
            if (dimList.size() == 1) {
                /* Create the quad for '*' */
                temp2 = this.intermediateCode.newTemp(BuiltInType.Int);
                if (arrayType.getArrayType().equals("int "))
                    this.intermediateCode.genQuad("*", placesList.get(0), "4", temp2);
                else 
                    this.intermediateCode.genQuad("*", placesList.get(0), "1", temp2);
            }
            else {
                for (int dim = 0; dim < dimList.size() - 1; dim++) {
                    /* Create the quad for '*' */
                    if (dim == 0) {
                        /* Only for the first time we need a new temp */
                        temp1 = this.intermediateCode.newTemp(BuiltInType.Int);
                        this.intermediateCode.genQuad("*", placesList.get(placesList.size() - dim - 1),
                                                    dimList.get(dim+1).toString(), temp1);
                    }
                    else {
                        this.intermediateCode.genQuad("*", temp2, dimList.get(dim+1).toString(), temp1);
                    }

                    /* Make the quad for '+' */
                    temp2 = this.intermediateCode.newTemp(BuiltInType.Int);
                    this.intermediateCode.genQuad("+", temp1, placesList.get(placesList.size() - (dim+1) -1), temp2);
                }

                temp1 = temp2;
                temp2 = this.intermediateCode.newTemp(BuiltInType.Int);
                if (arrayType.getArrayType().equals("int "))
                    this.intermediateCode.genQuad("*", temp1, "4", temp2);
                else 
                    this.intermediateCode.genQuad("*", temp1, "1", temp2);
            }
            
            /* Make the array quad */
            temp1 = this.intermediateCode.newTemp(BuiltInType.Address);
            this.intermediateCode.genQuad("array", arrayName.toString(), temp2 , temp1);

            /* Put the AArrayLvalue parent (which is an expression) in the hashMap */
            exprTypes.put(node, new Attributes(new BuiltInType(arrayType.getArrayType()), "[" + temp1 + "]"));
        }
    }

    @Override
    public void caseAOrCond(AOrCond node) {
        inAOrCond(node);
        if (node.getL() != null) {
            node.getL().apply(this);
        }

        if (node.getKwOr() != null) {
            node.getKwOr().apply(this);
        }

        /* Intermediate code for Left cond */
        this.intermediateCode.backPatch(exprTypes.get(node.getL()).getFalse(), Quads.nextQuad());

        if (node.getR() != null) {
            node.getR().apply(this);
        }
        outAOrCond(node);

        /* Get the attributes of the lhs and rhs of and */
        Attributes lhsAttributes = exprTypes.get(node.getL());
        Attributes rhsAttributes = exprTypes.get(node.getR());

        /* Intermediate code actions for right node */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        nodeAttributes.setTrue(this.intermediateCode.mergeLists(lhsAttributes.getTrue(), rhsAttributes.getTrue()));
        nodeAttributes.setFalse(rhsAttributes.getFalse());

        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outAOrCond(AOrCond node) {
        Type leftCondType = exprTypes.get(node.getL()).getType();
        Type rightCondType = exprTypes.get(node.getR()).getType();

        /* Or operator can be applied to booleans only */
        if (!(leftCondType.isBoolean())) {
            int line = node.getKwOr().getLine();
            int column = node.getKwOr().getPos();
            throw new TypeCheckingException(line, column, "Or operator can be applied to boolean expressions only\n" +
                                            node.getL().toString() + "is not a boolean expression");
        }

        if (!(rightCondType.isBoolean())) {
            int line = node.getKwOr().getLine();
            int column = node.getKwOr().getPos();
            throw new TypeCheckingException(line, column, "Or operator can be applied to boolean expressions only\n" +
                                            node.getL().toString() + "is not a boolean expression");
        }
    }
    
    @Override
    public void caseAAndCond(AAndCond node) {
        inAAndCond(node);
        
        if (node.getL() != null) {
            node.getL().apply(this);
        }

        if (node.getKwAnd() != null) {
            node.getKwAnd().apply(this);
        }

        /* Intermediate code for Left cond */
        this.intermediateCode.backPatch(exprTypes.get(node.getL()).getTrue(), Quads.nextQuad());

        if (node.getR() != null) {
            node.getR().apply(this);
        }
        outAAndCond(node);

        /* Get the attributes of the lhs and rhs of and */
        Attributes lhsAttributes = exprTypes.get(node.getL());
        Attributes rhsAttributes = exprTypes.get(node.getR());

        /* Intermediate code actions for node */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        nodeAttributes.setFalse(this.intermediateCode.mergeLists(lhsAttributes.getFalse(), rhsAttributes.getFalse()));
        nodeAttributes.setTrue(rhsAttributes.getTrue());

        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outAAndCond(AAndCond node) {
        Type leftCondType = exprTypes.get(node.getL()).getType();
        Type rightCondType = exprTypes.get(node.getR()).getType();

        /* And operator can be applied to booleans only */
        if (!(leftCondType.isBoolean())) {
            int line = node.getKwAnd().getLine();
            int column = node.getKwAnd().getPos();
            throw new TypeCheckingException(line, column, "And operator can be applied to boolean expressions only\n" +
                                            node.getL().toString() + "is not a boolean expression");
        }

        if (!(rightCondType.isBoolean())) {
            int line = node.getKwAnd().getLine();
            int column = node.getKwAnd().getPos();
            throw new TypeCheckingException(line, column, "And operator can be applied to boolean expressions only\n" +
                                            node.getL().toString() + "is not a boolean expression");
        }
    }

    @Override
    public void outANotCond(ANotCond node) {
        Type aCondType = exprTypes.get(node.getCond()).getType();

        /* And operator can be applied to booleans only */
        if (!(aCondType.isBoolean())) {
            int line = node.getKwNot().getLine();
            int column = node.getKwNot().getPos();
            throw new TypeCheckingException(line, column, "Not operator can be applied to boolean expressions only\n" +
                                            node.getCond().toString() + "is not a boolean expression");
        }

        /* Intermediate code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        this.intermediateCode.genCodeForNotOp(nodeAttributes, exprTypes.get(node.getCond()));

        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outAEqCond(AEqCond node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* An equality comparison operator can be applied to equivalent types only */
        if (!(leftExprType.isEquivWith(rightExprType))) {
            int line = node.getEq().getLine();
            int column = node.getEq().getPos();
            throw new TypeCheckingException(line, column, "An \"=\" operator can be applied to equivalent types only");
        }

        /* An equality comparison operator can be applied to int or char only */
        if (!leftExprType.isInt() && !leftExprType.isChar()) {
            int line = node.getEq().getLine();
            int column = node.getEq().getPos();
            throw new TypeCheckingException(line, column, "An \"=\" operator can be applied to int or char types only");
        }

        /* Intermediate code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        this.intermediateCode.genCodeForRelOp("=", nodeAttributes, exprTypes.get(node.getL()), exprTypes.get(node.getR()));

        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outANeqCond(ANeqCond node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* A non-equality comparison operator can be applied to equivalent types only */
        if (!(leftExprType.isEquivWith(rightExprType))) {
            int line = node.getNeq().getLine();
            int column = node.getNeq().getPos();
            throw new TypeCheckingException(line, column, "An \"#\" operator can be applied to equivalent types only");
        }

        /* A non-equality comparison operator can be applied to int or char only */
        if (!leftExprType.isInt() && !leftExprType.isChar()) {
            int line = node.getNeq().getLine();
            int column = node.getNeq().getPos();
            throw new TypeCheckingException(line, column, "An \"#\" operator can be applied to int or char types only");
        }

        /* Intermediate code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        this.intermediateCode.genCodeForRelOp("#", nodeAttributes, exprTypes.get(node.getL()), exprTypes.get(node.getR()));

        exprTypes.put(node, nodeAttributes);

    }

    @Override
    public void outALtCond(ALtCond node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* A less-than comparison operator can be applied to equivalent types only */
        if (!(leftExprType.isEquivWith(rightExprType))) {
            int line = node.getLt().getLine();
            int column = node.getLt().getPos();
            throw new TypeCheckingException(line, column, "A \"<\" operator can be applied to equivalent types only");
        }

        /* A less-than comparison operator can be applied to int or char only */
        if (!leftExprType.isInt() && !leftExprType.isChar()) {
            int line = node.getLt().getLine();
            int column = node.getLt().getPos();
            throw new TypeCheckingException(line, column, "A \"<\" operator can be applied to int or char types only");
        }

        /* Intermediate code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        this.intermediateCode.genCodeForRelOp("<", nodeAttributes, exprTypes.get(node.getL()), exprTypes.get(node.getR()));

        exprTypes.put(node, nodeAttributes);

    }
    
    @Override
    public void outAGtCond(AGtCond node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();
        
        /* A greater-than comparison operator can be applied to equivalent types only */
        if (!(leftExprType.isEquivWith(rightExprType))) {
            int line = node.getGt().getLine();
            int column = node.getGt().getPos();
            throw new TypeCheckingException(line, column, "A \">\" operator can be applied to equivalent types only");
        }

        /* A greater-than comparison operator can be applied to int or char only */
        if (!leftExprType.isInt() && !leftExprType.isChar()) {
            int line = node.getGt().getLine();
            int column = node.getGt().getPos();
            throw new TypeCheckingException(line, column, "A \">\" operator can be applied to int or char types only");
        }
        
        /* Intermediate code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        this.intermediateCode.genCodeForRelOp(">", nodeAttributes, exprTypes.get(node.getL()), exprTypes.get(node.getR()));

        exprTypes.put(node, nodeAttributes);
    }

    public void outAGteqCond(AGteqCond node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* A greater-than-or-equal comparison operator can be applied to equivalent types only */
        if (!(leftExprType.isEquivWith(rightExprType))) {
            int line = node.getGteq().getLine();
            int column = node.getGteq().getPos();
            throw new TypeCheckingException(line, column, "A \">=\" operator can be applied to equivalent types only");
        }

        /* A greater than comparison operator can be applied to int or char only */
        if (!leftExprType.isInt() && !leftExprType.isChar()) {
            int line = node.getGteq().getLine();
            int column = node.getGteq().getPos();
            throw new TypeCheckingException(line, column, "A \">=\" operator can be applied to int or char types only");
        }

        /* Intermediate code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        this.intermediateCode.genCodeForRelOp(">=", nodeAttributes, exprTypes.get(node.getL()), exprTypes.get(node.getR()));

        exprTypes.put(node, nodeAttributes);
    }

    public void outALteqCond(ALteqCond node) {
        Type leftExprType = exprTypes.get(node.getL()).getType();
        Type rightExprType = exprTypes.get(node.getR()).getType();

        /* A less-than-or-equal comparison operator can be applied to equivalent types only */
        if (!(leftExprType.isEquivWith(rightExprType))) {
            int line = node.getLteq().getLine();
            int column = node.getLteq().getPos();
            throw new TypeCheckingException(line, column, "A \"<=\" operator can be applied to equivalent types only");
        }

        /* A less than comparison operator can be applied to int or char only */
        if (!leftExprType.isInt() && !leftExprType.isChar()) {
            int line = node.getLteq().getLine();
            int column = node.getLteq().getPos();
            throw new TypeCheckingException(line, column, "A \"<=\" operator can be applied to int or char types only");
        }

        /* Intermediate code */
        Attributes nodeAttributes = new Attributes(BuiltInType.Boolean);
        this.intermediateCode.genCodeForRelOp("<=", nodeAttributes, exprTypes.get(node.getL()), exprTypes.get(node.getR()));

        exprTypes.put(node, nodeAttributes);
    }

    @Override
    public void outATrueCond(ATrueCond node) {
        exprTypes.put(node, new Attributes(BuiltInType.Boolean));
    }

    @Override
    public void inAFalseCond(AFalseCond node) {
        exprTypes.put(node, new Attributes(BuiltInType.Boolean));        
    }
}