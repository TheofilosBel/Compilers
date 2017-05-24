package compiler.semanticAnalysis;

import compiler.analysis.*;
import compiler.node.*;
import compiler.types.*;
import compiler.exceptions.*;
import compiler.semanticAnalysis.VariableInfo;
import compiler.semanticAnalysis.SymbolTableEntry;
import java.util.Collections;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Stack;

public class SemanticAnalysis extends DepthFirstAdapter {

    int indentation = 0;
    SymbolTable symbolTable; /* The structure of the symbol table */
    private HashMap<Node, Type> exprTypes; /* A structure that maps every sablecc generated Node to a type */
    private Stack<TId> currentFunctionId;
    private CompilerErrorList errorList;

    /*
     * The semantic analysis phase starts here
     * We create the symbol table and in its first scope we insert
     * the Grace built in library functions so that they can be
     * called by any function in the program (global scope)
     */
    public SemanticAnalysis() {
        /* Create a new symbol table */
        System.out.println("Constructing Symbol_table");
        this.symbolTable = new SymbolTable();
        this.errorList   = new CompilerErrorList();
        
        /* Add the first scope that will hold the built in library functions */
        this.symbolTable.enter();
        
        LinkedList<VariableInfo> argList;     /* A list containing the arguments to each function */
        LinkedList<String> passBy;            /* A list containing the pass method (by reference / by value) of each argument */
        SymbolTableEntry data;                /* An object that is inserted in the symbol table for each function */
        currentFunctionId = new Stack<TId>(); /* It holds the id of the current function */
        exprTypes = new HashMap<>();          /* Create the HashMap for the type checking */
        

        /* fun puti (n : int) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("puti", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun putc (c : char) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("c", "char", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("putc", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun puts (ref s : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("s", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("puts", data);
        data    = null;
        argList = null;
        passBy  = null;
    
        /* fun geti () : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* No arguments to be added to the list */
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("geti", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun getc () : char */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* No arguments to be added to the list */
        data = new SymbolTableEntry(new FunctionInfo(new String("char"), argList, passBy));
        this.symbolTable.insert("getc", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun gets (n : int, ref s : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("n", "int", false));
        passBy.add("val");
        argList.add(new VariableInfo("s", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("gets", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun abs (n : int) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("abs", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun ord (c : char) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("c", "char", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("ord", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun chr (n : int) : char */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("n", "int", false));
        passBy.add("val");
        data = new SymbolTableEntry(new FunctionInfo(new String("char"), argList, passBy));
        this.symbolTable.insert("chr", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strlen (ref s : char[]) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the only argument to the list */
        argList.add(new VariableInfo("s", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("strlen", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strcmp (ref s1, s2 : char[]) : int */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("s1", "char", true));
        passBy.add("ref");
        argList.add(new VariableInfo("s2", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("int"), argList, passBy));
        this.symbolTable.insert("strcmp", data);
        data    = null;
        argList = null;
        passBy  = null;
        
        /* fun strcpy (ref trg, src : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("trg", "char", true));
        passBy.add("ref");
        argList.add(new VariableInfo("src", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("strcpy", data);
        data    = null;
        argList = null;
        passBy  = null;

        /* fun strcat (ref trg, src : char[]) : nothing */
        argList = new LinkedList<VariableInfo>();
        passBy  = new LinkedList<String>();
        /* Add the two arguments to the list */
        argList.add(new VariableInfo("trg", "char", true));
        passBy.add("ref");
        argList.add(new VariableInfo("src", "char", true));
        passBy.add("ref");
        data = new SymbolTableEntry(new FunctionInfo(new String("nothing"), argList, passBy));
        this.symbolTable.insert("strcat", data);
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
        SymbolTableEntry funcDec = this.symbolTable.lookup(node.getId().toString());
        
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
            catch (TypeCheckingException e){
                
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
        catch (TypeCheckingException e){
            
            /* Add error to list an continue */
            this.errorList.addToList(e.getMessage());
            return;
        }
        
        /* Check if the definition is equivalent with the declaration */
        if (funcDec != null) {
            
            try {
                ((FunctionInfo) funcDec.getInfo()).isEquivWith(funcDefInfo);
            }
            catch (TypeCheckingException e){
                
                /* Add error to list an continue */
                this.errorList.addToList(e.getMessage());
                throw e;
            }
        }

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
        SymbolTableEntry funcDef = this.symbolTable.lookup(node.getId().toString());
        
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
        
        /* Get the current FunctionInfo  */
        SymbolTableEntry currentFunctionEntry = this.symbolTable.lookup(currentFunctionId.peek().toString());
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
            
            /* Add the variable on the function's definition List that holds local variable */
            currentFuncDef.addLocalVariable(v);

            /* Print each variable */
            indentNprint("Name :" + v.getName());
            indentNprint("Type :" + v.getType());
            indentNprint("Int ?:" + v.getType().isInt());            
        }
        
        
        for (int a = 0 ; a < currentFuncDef.getLocalVariables().size(); a++) 
            System.out.println("Lists :" + currentFuncDef.getLocalVariables().get(a).getName());
    }

    @Override
    public void outAIfStmt(AIfStmt node) {
        Type aCondType = exprTypes.get(node.getCond());

        if (!(aCondType.isBoolean())) {
            int line = node.getKwIf().getLine();
            int column = node.getKwIf().getPos();
            throw new TypeCheckingException(line, column, "If statement condition should have a boolean type\n" +
                                            node.getCond().toString() + "is not a boolean condition");
        }
    }

    @Override
    public void outAWhileStmt(AWhileStmt node) {
        Type aCondType = exprTypes.get(node.getCond());

        if (!(aCondType.isBoolean())) {
            int line = node.getKwWhile().getLine();
            int column = node.getKwWhile().getPos();
            throw new TypeCheckingException(line, column, "While statement condition should have a boolean type\n" +
                                            node.getCond().toString() + "is not a boolean condition");
        }
    }

    @Override
    public void outAAssignStmt(AAssignStmt node) {
        Type assignLhsType = exprTypes.get(node.getLvalue());

        /* String literals not allowed as lvalues in assignments */
        if (assignLhsType.isArray() && assignLhsType.isEquivWith(BuiltInType.Char)) {
            int line = node.getAssign().getLine();
            int column = node.getAssign().getPos();
            throw new TypeCheckingException(line, column, "Left hand side of an assignment can not be a string literal");
        }

        Type assignRhsType = exprTypes.get(node.getExpr());

        if (!(assignLhsType.isEquivWith(assignRhsType))) {
            int line = node.getAssign().getLine();
            int column = node.getAssign().getPos();
            throw new TypeCheckingException(line, column, "Both sides of an assignment should have the same type");
        }
    }

    @Override
    public void outAReturnStmt(AReturnStmt node) {
        Type aExprType = exprTypes.get(node.getExpr());

        /* Search the function this return statement corresponds to */
        SymbolTableEntry currentFunctionEntry = this.symbolTable.lookup(currentFunctionId.peek().toString());
        
        if (!(aExprType.isEquivWith(currentFunctionEntry.getInfo().getType()))) {
            int line = node.getKwReturn().getLine();
            int column = node.getKwReturn().getPos();
            throw new TypeCheckingException(line, column, "Type of returned expression does not match return type of function:" + currentFunctionId.peek().getText() + "\n"
                                            + "Return type is " + currentFunctionEntry.getInfo().getType());
        }
        
        /* Function definition matched to a return statement */
        ((FuncDefInfo) currentFunctionEntry.getInfo()).setIsMatchedToReturnStmt(true);
    }
    
    /*-------------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------------------- Expr Productions ---------------------------------------------------*/
    
    @Override
    public void outAAddExpr(AAddExpr node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Int);
    }

    @Override
    public void outASubExpr(ASubExpr node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Int);
    }

    @Override
    public void outAMultExpr(AMultExpr node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Int);
    }

    @Override
    public void outADivExpr(ADivExpr node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Int);
    }

    @Override
    public void outAModExpr(AModExpr node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Int);
    }

    @Override
    public void outAPosExpr(APosExpr node) {
        Type aExprType = exprTypes.get(node.getExpr());

        /* A positive sign can be applied to integers only */
        if (!(aExprType.isInt())) {
            int line = node.getPlus().getLine();
            int column = node.getPlus().getPos();
            throw new TypeCheckingException(line, column, "Positive sign applied to invalid expression: "
                                            + node.getExpr().toString());
        }

        exprTypes.put(node, BuiltInType.Int);
    }

    @Override
    public void outANegExpr(ANegExpr node) {
        Type aExprType = exprTypes.get(node.getExpr());

        /* A negative sign can be applied to integers only */
        if (!(aExprType.isInt())) {
            int line = node.getMinus().getLine();
            int column = node.getMinus().getPos();
            throw new TypeCheckingException(line, column, "Negative sign applied to invalid expression: "
                                            + node.getExpr().toString());
        }

        exprTypes.put(node, BuiltInType.Int);
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

        exprTypes.put(node, BuiltInType.Int);
    }

    @Override
    public void outACharExpr(ACharExpr node) {
        exprTypes.put(node, BuiltInType.Char);
    }
    
    
    @Override
    public void outALvalExpr(ALvalExpr node) {
        
        /*Get the type of your child (Lvalue) an make it a node on hashMap */
        Type type  = exprTypes.get(node.getLvalue());
        if ( type != null) 
            exprTypes.put(node, type);
        
    }
    /*-----------------------------------------------------------------------------------------------------------*/
    
    @Override
    public void outAFuncCall(AFuncCall node) {
        /* Get the declaration from the symbol table */
        SymbolTableEntry funcDec = this.symbolTable.lookup(node.getId().toString());
        
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
                
                /* Get the functions declaration argument's type - Function call expretion's type */
                funcDecExprType = ((FunctionInfo) funcDec.getInfo()).getArguments().get(arg).getType();
                funcCallExprType = exprTypes.get(node.getExprList().get(arg));

                if (! funcDecExprType.isEquivWith(funcCallExprType)) {
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

        /* Put the return type to the HashMap */
        exprTypes.put(node.parent(), funcInfo.getType());
    }
    
    
    
    /*-------------------------------------- Lvalue Productions -----------------------------------------------------------*/

    @Override
    public void outAStrLvalue(AStrLvalue node) {
        LinkedList<Integer> strLength = new LinkedList<Integer>();
        /* We subtract 3 from the length to account for the "" and the space at the end */
        strLength.add(node.getStringLiteral().toString().length() - 3);

        exprTypes.put(node, new ComplexType("array", strLength, "char"));
    }

    @Override
    public void outAIdLvalue(AIdLvalue node) {
        SymbolTableEntry anId = this.symbolTable.lookup(node.getId().toString());

        /* If the id was not found in the symbol table throw an exception */
        if (anId == null) {
            int line = node.getId().getLine();
            int column = node.getId().getPos();
            throw new TypeCheckingException(line, column, "Undefined indentifier: " + node.getId().toString());
        }

        System.out.println(anId.getInfo().getType().toString());
        
        /* Put the Id on the hashMap */
        exprTypes.put(node, anId.getInfo().getType());
    }
    
    public TId recArrayIdFinder(AArrayLvalue node, LinkedList<Integer> dimList) {
        /* Add the size to the linked List and call again */
        dimList.add(0);

        /* Recursion */
        if (node.getLvalue() instanceof AIdLvalue) {
            return ((AIdLvalue) node.getLvalue()).getId();
        }

        /*
         * Else if (node.getLvalue() instanceof AArrayLvalue) 
         * There is no case for a AStrLvalue because an exception would already be thrown 
         */
        return recArrayIdFinder((AArrayLvalue) node.getLvalue(), dimList); 
    }

    @Override 
    public void outAArrayLvalue(AArrayLvalue node) {
        /* If the nested lvalue is strin_literal then throw error */
        if (node.getLvalue() instanceof AStrLvalue) {
            AStrLvalue strLval = (AStrLvalue) node.getLvalue();
            int line = strLval.getStringLiteral().getLine();
            int column = strLval.getStringLiteral().getPos();
            throw new TypeCheckingException(line, column, "Invalid action, using string literal "+ strLval.toString()+" as array type");
        }

        /* If the expression is non int then throw an error */
        Type aExprType = exprTypes.get(node.getExpr());
        if (! aExprType.isInt()){
            int line = node.getLBracket().getLine();
            int column = node.getLBracket().getPos() + 1;  /* The error occurs after the [ token but we use it to help us get column */
            throw new TypeCheckingException(line, column, "Invalid action, using string literal \"" + node.getExpr().toString() + "\" with \'[\' \']\'");
        }

        /* Use recursive function to get the idName (at the highest lvalueArray node on the AST) */
        if (! (node.parent() instanceof AArrayLvalue)){
            TId arrayName = null;
            LinkedList<Integer> list = new LinkedList<Integer>();
            arrayName = recArrayIdFinder(node, list);
            System.out.println(list);

            /* Lookup in the symbol table for the array , we are sure that it exist , 
             * because it it wasn't defined an exception would be thrown in outAIdLvalue */
            SymbolTableEntry array = this.symbolTable.lookup(arrayName.toString());  

            /* Get the Type */
            Type arrayType = array.getInfo().getType();

            /* If the variable is not an array throw an exception */
            if (! arrayType.isArray()) {
                int line = arrayName.getLine();
                int column = arrayName.getPos();
                throw new SemanticAnalysisException(line, column, "Invalid action, identifier \""+ arrayName.getText()+"\" not defined as an array but as " + 
                                                    arrayType.getTypeName());
            }

            /* Make a new type representing the array access (we need this to check the dim number) */
            Type arrayAccessType = new ComplexType("array", list, arrayType.getArrayType()); 

            /* If the types are not the same (blame the dimensions)*/
            if (! arrayType.isEquivWith(arrayAccessType)) {
                int line = arrayName.getLine();
                int column = arrayName.getPos();
                throw new TypeCheckingException(line, column, "Invalid action, array with id \""+ arrayName.getText()+"\" was defined with: " +
                                                arrayType.getArrayDims() + " dimensions but is used with: " + arrayAccessType.getArrayDims());
            }

            /* Put the AArrayLvalue parent (which is and expression) on the hashMap */
            exprTypes.put(node, new BuiltInType(arrayType.getArrayType()));
        }
    }
    
    /*----------------------------------------------------------------------------------------------------------------------------*/
    
    
    /*---------------------------------------- Cond Productions -----------------------------------------------------------*/

    @Override
    public void outAOrCond(AOrCond node) {
        Type leftCondType = exprTypes.get(node.getL());
        Type rightCondType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Boolean);
    }

    @Override
    public void outAAndCond(AAndCond node) {
        Type leftCondType = exprTypes.get(node.getL());
        Type rightCondType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Boolean);
    }

    @Override
    public void outANotCond(ANotCond node) {
        Type aCondType = exprTypes.get(node.getCond());

        /* And operator can be applied to booleans only */
        if (!(aCondType.isBoolean())) {
            int line = node.getKwNot().getLine();
            int column = node.getKwNot().getPos();
            throw new TypeCheckingException(line, column, "Not operator can be applied to boolean expressions only\n" +
                                            node.getCond().toString() + "is not a boolean expression");
        }

        exprTypes.put(node, BuiltInType.Boolean);   
    }

    @Override
    public void outAEqCond(AEqCond node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Boolean);
    }

    @Override
    public void outANeqCond(ANeqCond node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Boolean);
    }

    @Override
    public void outALtCond(ALtCond node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Boolean);
    }
    
    @Override
    public void outAGtCond(AGtCond node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());
        
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

        exprTypes.put(node, BuiltInType.Boolean);
    }

    public void outAGteqCond(AGteqCond node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Boolean);
    }

    public void outALteqCond(ALteqCond node) {
        Type leftExprType = exprTypes.get(node.getL());
        Type rightExprType = exprTypes.get(node.getR());

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

        exprTypes.put(node, BuiltInType.Boolean);
    }

    @Override
    public void outATrueCond(ATrueCond node) {
        exprTypes.put(node, BuiltInType.Boolean);
    }

    @Override
    public void inAFalseCond(AFalseCond node) {
        exprTypes.put(node, BuiltInType.Boolean);        
    }
}