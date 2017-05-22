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

public class SemanticAnalysis extends DepthFirstAdapter {

    int indentation = 0;
    SymbolTable symbolTable; /* The structure of the symbol table */
    private HashMap<Node, Type> exprTypes; /* A structure that maps every sablecc generated Node to a type */

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
        
        /* Add the first scope that will hold the built in library functions */
        this.symbolTable.enter();
        
        LinkedList<VariableInfo> argList; /* A list containing the arguments to each function */
        LinkedList<String> passBy;    /* A list containing the pass method (by reference / by value) of each argument */
        SymbolTableEntry data;        /* An object that is inserted in the symbol table for each function */

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

    /* For debugging reasons */
    private void addIndentationLevel() {
        indentation++;
    }

    private void removeIndentationLevel() {
        indentation--;
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
        SymbolTableEntry data = new SymbolTableEntry(new FuncDecInfo((PDataType) node.getRetType(),
                                node.getFplist(), node.getId()));
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
            FuncDecInfo type = new FuncDecInfo((PDataType) node.getRetType(),
                               node.getFplist(), node.getId());
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

            /* Create the HashMap for the type checking */
            exprTypes = new HashMap<>();
        }

        /* Create the info for the function definition*/
        FunctionInfo funcDefInfo = new FuncDefInfo((PDataType) node.getRetType(),
                node.getFplist(), node.getId());
         
        /* Check equivalence with the function declaration found and before.
         * First on arguments passed by reference */
        
            
        /* Check if the definition is equivalent with the declaration */
        if (funcDec != null) {
            ((FunctionInfo) funcDec.getInfo()).isEquivWith(funcDefInfo);
        }

        /* In every new func_def we create a new scope */
        this.symbolTable.enter();

        /* Create a SymbolTableEntry object to pass to the insert function */
        SymbolTableEntry data = new SymbolTableEntry(funcDefInfo);

        /* Insert the function definition */
        this.symbolTable.insert(node.getId().toString(), data);

        /* Insert the function's arguments to the symbol table - First those passed by reference) */
        for (int var = 0;  var < ((FunctionInfo) data.getInfo()).getArgsByRef().size(); var++) {
            this.symbolTable.insert(((FunctionInfo) data.getInfo()).getArgsByRef().get(var).getName().toString(),
                new SymbolTableEntry(new VariableInfo(((FunctionInfo) data.getInfo()).getArgsByRef().get(var))));
        }

        /* Insert the function's arguments to the symbol table - Now those passed by value) */
        for (int var = 0; var < ((FunctionInfo) data.getInfo()).getArgsByVal().size(); var++) {
            this.symbolTable.insert(((FunctionInfo) data.getInfo()).getArgsByVal().get(var).getName().toString(),
                new SymbolTableEntry(new VariableInfo(((FunctionInfo) data.getInfo()).getArgsByVal().get(var))));
            
        }

        addIndentationLevel();
    }

    @Override
    public void outAFuncDef(AFuncDef node) {
        /* When exiting from a function exit from the current scope too */
        this.symbolTable.exit();
        removeIndentationLevel();
    }

    @Override
    public void inAVarDef(AVarDef node) {
        indentNprint("In variable DEfinition");
        
        /* Get the type of the variables in the current definition */
        PType type = (PType) ((AType) node.getType()).clone();

        /* Extract every variable from a multi-variable definition and save their types */
        for (int varnum = 0; varnum < node.getIdList().size(); varnum++) {
            /* Add all the variables in the symbol table */
            VariableInfo v = new VariableInfo(node.getIdList().get(varnum), (AType) type);
            SymbolTableEntry data = new SymbolTableEntry(v);

            if (this.symbolTable.insert(node.getIdList().get(varnum).toString(), data) == false){
                throw new SemanticAnalysisException(v.getName().getLine(), v.getName().getPos(),
                        "Conflicting types: name \"" + v.getName().getText() + "\" already exists");
            }

            /* Print each variable */
            indentNprint("Name :" + v.getName());
            indentNprint("Type :" + v.getType());
            indentNprint("Int ?:" + v.getType().isInt());
            
        }
    }

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
    public void outAFuncCall(AFuncCall node) {
        /* Get the declaration from the symbol table */
        SymbolTableEntry funcDec = this.symbolTable.lookup(node.getId().toString());
        
        /* If the function is not declared throw an exception */
        if (funcDec == null) {
            throw new SemanticAnalysisException(node.getId().getLine(), node.getId().getPos(),
                    "Calling function \"" + node.getId().getText() + "\": function is not declared or defined");
        }

        /* Equivalence check with declaration */
        FuncDecInfo funcDecInfo = (FuncDecInfo) funcDec.getInfo();
        
        System.out.println(node.getExprList().size());
        
        /* First check for equal number of arguments */
        if ((funcDecInfo.getArgsByRef().size() + funcDecInfo.getArgsByVal().size()) == node.getExprList().size()) {
            if (node.getExprList().size() > 0) {
                /* Check every expression with its equivalent argument */
                for (int arg = 0; arg < node.getExprList().size(); arg++) {
                    /* If the expression and the argument are not equal throw exception */                    
                    Type funcDecExprType  = funcDecInfo.getArgsByVal().get(arg).getType(); 
                    Type funcCallExprType = exprTypes.get(node.getExprList().get(arg));

                    System.out.println("Type exp " + funcCallExprType + "\nType arg " + funcDecExprType);

                    if (!(funcDecExprType.isEquivWith(funcCallExprType))) {
                        TId name  = node.getId();
                        Node expr = node.getExprList().get(arg);
                        throw new TypeCheckingException(name.getLine(), name.getPos(),
                                "In function \"" + name.getText() + "\": calling with expression: \"" +
                                expr.toString() + "\" with type " + funcCallExprType.toString() +
                                " but declared type is " + funcDecExprType.toString());
                    }
                }
            }
        }
        else {
            TId name  = node.getId();
            throw new TypeCheckingException(name.getLine(), name.getPos(),
                "Calling function \"" + name.getText() + "\": wrong number of arguments provided");
        }

        /* Put the return type to the HashMap */
        exprTypes.put(node.parent(), funcDecInfo.getType());
    }

    @Override
    public void outAStrLvalue(AStrLvalue node) {
        LinkedList<Integer> strLength = new LinkedList<Integer>();
        /* We subtract 3 from the length to account for the "" and the space at the end */
        strLength.add(node.getStringLiteral().toString().length() - 3);

        exprTypes.put(node.parent(), new ComplexType("array", strLength, "char"));
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
        exprTypes.put(node.parent(), anId.getInfo().getType());
    }
    
    public TId recArrayIdFinder(AArrayLvalue node, LinkedList<Integer> dimList) {
        
        /* Add the size to the linked List and call again */
        dimList.add(0);

        /* Recursion */
        if (node.getLvalue() instanceof AIdLvalue) {
            return ((AIdLvalue) node.getLvalue()).getId();
        }
        
        /* else if (node.getLvalue() instanceof AArrayLvalue) 
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

            /* Put the AArrayLvalue parent (which is and expr) on the hashMap */
            exprTypes.put(node.parent(), new BuiltInType(arrayType.getArrayType()));
        }
    }

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
    public void outATrueCond(ATrueCond node) {
        exprTypes.put(node, BuiltInType.Boolean);
    }

    @Override
    public void inAFalseCond(AFalseCond node) {
        exprTypes.put(node, BuiltInType.Boolean);        
    }
}