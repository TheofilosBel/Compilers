package compiler;

import compiler.analysis.*;
import compiler.node.*;
import java.util.Collections;

public class Translation extends DepthFirstAdapter {

    int indentation = 0;

    private void addIndentationLevel() {
        indentation++;
    }

    private void removeIndentationLevel() {
        indentation--;
    }

    private void printIndentation() {
        System.out.print(String.join("", Collections.nCopies(indentation, "    ")));
    }
    
    private void printNLineIndent(){
        System.out.println();
        printIndentation();
    }
    
    private void printNLineIndent(String msg){
        System.out.println(msg);
        printIndentation();
    }
    
    @Override
    public void inAProgram(AProgram node) {
        defaultIn(node);
    }

    @Override
    public void inAFuncDef(AFuncDef node) {
        printIndentation();
        printNLineIndent("type: function definition");
        System.out.print("name: ");
    }

    @Override
    public void outAFuncDef(AFuncDef node) {
        removeIndentationLevel();
    }
    
    @Override
    public void inAFuncDecl(AFuncDecl node) {
        printIndentation();
        printNLineIndent("type: function declaration");
        System.out.print("name: ");
    }

    @Override
    public void inAInnerDeclLocalDef(AInnerDeclLocalDef node) {
        defaultIn(node);
    }

    @Override
    public void inAInnerDefLocalDef(AInnerDefLocalDef node) {
        defaultIn(node);
    }

    @Override
    public void inAInnerVarLocalDef(AInnerVarLocalDef node) {
        defaultIn(node);
    }

    @Override
    public void inAHeader(AHeader node) {
        System.out.print(node.getId().toString());
        printNLineIndent();
        System.out.print("parameters:");
    }

    @Override
    public void outAHeader(AHeader node) {
        System.out.println();
    }

    @Override
    public void inAFparList(AFparList node) {
        System.out.println(" [");
        addIndentationLevel();
    }
    
    @Override
    public void outAFparList(AFparList node) {
        removeIndentationLevel();
        printIndentation();
        System.out.print("]");
    }

    @Override
    public void inAFparDef(AFparDef node) {
        printIndentation();
        if (node.getKwRef() != null) {
            System.out.print("pass by ref: ");
        }
        else {
            System.out.print("pass by val: ");
        }
            
        System.out.print("name: ");
    }
    
    @Override
    public void outAFparDef(AFparDef node) {
        System.out.println();
    }

    @Override
    public void inAVarDef(AVarDef node) {
        printIndentation();
        System.out.println("type: variable definition");
        printIndentation();
        System.out.print("name: ");
    }
    
    @Override
    public void outAVarDef(AVarDef node) {
        System.out.println();
        System.out.println();
    }

    @Override
    public void inAMultiIdList(AMultiIdList node) {
        System.out.print(node.getId().toString());
    }

    @Override
    public void inASingleIdList(ASingleIdList node) {
        System.out.print(node.getId().toString());
    }

    @Override
    public void inAFparType(AFparType node) {
        printNLineIndent();
        System.out.print("type: ");
    }

    @Override
    public void inAType(AType node) {
        printNLineIndent();
        System.out.print("type: ");
    }

    @Override
    public void inADataRetType(ADataRetType node) {
        printNLineIndent();
        System.out.print("return type: ");
    }
    
    @Override
    public void outADataRetType(ADataRetType node) {
        printNLineIndent();
    }

    @Override
    public void inANothingRetType(ANothingRetType node) {
        printNLineIndent();
        System.out.print("return type: nothing");
        addIndentationLevel();
    }
    
    @Override
    public void outANothingRetType(ANothingRetType node) {
        System.out.println();
    }

    @Override
    public void inAIntDataType(AIntDataType node) {
        System.out.print(node.toString());
    }

    @Override
    public void inACharDataType(ACharDataType node) {
        System.out.print(node.toString());
    }

    @Override
    public void inAMatchedStmt(AMatchedStmt node) {
        defaultIn(node);
    }

    @Override
    public void inAUnmatchedStmt(AUnmatchedStmt node) {
        defaultIn(node);
    }

    @Override
    public void inAIfMatched(AIfMatched node) {
        printIndentation();
        System.out.println("type: if-else statement");
        addIndentationLevel();
    }

    @Override
    public void outAIfMatched(AIfMatched node) {
        removeIndentationLevel();
    }

    @Override
    public void inAWhileMatched(AWhileMatched node) {
        printIndentation();
        System.out.println("type: while statement");
        addIndentationLevel();
    }

    @Override
    public void outAWhileMatched(AWhileMatched node) {
        removeIndentationLevel();
    }

    @Override
    public void inANoopMatched(ANoopMatched node) {
        printIndentation();
        System.out.println("type: no operation statement");
    }

    @Override
    public void inAAssignMatched(AAssignMatched node) {
        printIndentation();
        System.out.println("type: assignment");
    }

    @Override
    public void outAAssignMatched(AAssignMatched node) {
        System.out.println();
    }

    @Override
    public void inABlockMatched(ABlockMatched node) {
        defaultIn(node);
    }

    @Override
    public void inAFuncCallMatched(AFuncCallMatched node) {
        defaultIn(node);
    }

    @Override
    public void inAReturnMatched(AReturnMatched node) {
        printIndentation();
        System.out.println("type: return statement");
        addIndentationLevel();
    }

    @Override
    public void outAReturnMatched(AReturnMatched node) {
        removeIndentationLevel();
    }
    
    @Override
    public void inAIfUnmatched(AIfUnmatched node) {
        printIndentation();
        System.out.println("type: if-only statement");
        addIndentationLevel();
    }

    @Override
    public void outAIfUnmatched(AIfUnmatched node) {
        removeIndentationLevel();
    }

    @Override
    public void inAWhileUnmatched(AWhileUnmatched node) {
        printIndentation();
        System.out.println("type: while statement");
        addIndentationLevel();
    }

    @Override
    public void outAWhileUnmatched(AWhileUnmatched node) {
        removeIndentationLevel();
    }

    @Override
    public void inAMatchedIf(AMatchedIf node) {
        defaultIn(node);
    }

    @Override
    public void inANoElseUnmatchedIf(ANoElseUnmatchedIf node) {
        defaultIn(node);
    }

    @Override
    public void inAWithElseUnmatchedIf(AWithElseUnmatchedIf node) {
        defaultIn(node);
    }

    @Override
    public void inABlock(ABlock node) {
        printIndentation();
        System.out.println("{");
        addIndentationLevel();
    }

    @Override
    public void outABlock(ABlock node) {
        removeIndentationLevel();
        printIndentation();
        System.out.println("}");
    }

    @Override
    public void inAFuncCall(AFuncCall node) {
        printIndentation();
        printNLineIndent("type: function call");
        System.out.print("name: ");
        System.out.println(node.getId().toString());
        printIndentation();
        System.out.println("parameters: [");
    }
    
    @Override
    public void outAFuncCall(AFuncCall node) {
        System.out.println();
    }

    @Override
    public void inAMultiExprList(AMultiExprList node) {
        addIndentationLevel();
    }

    @Override
    public void outAMultiExprList(AMultiExprList node) {
        removeIndentationLevel();
        printIndentation();
        System.out.println("]");
    }

    @Override
    public void inAEmptyExprList(AEmptyExprList node) {
        defaultIn(node);
    }

    @Override
    public void inAExprListTail(AExprListTail node) {
        defaultIn(node);
    }

    @Override
    public void inAEmptyArrayDeclarator(AEmptyArrayDeclarator node) {
        System.out.print("array with empty dimensions ");
    }
    
    @Override
    public void inAArrayDeclArrayDeclarator(AArrayDeclArrayDeclarator node) {
        System.out.print("array with dimension(s): ");
        System.out.print(node.getIntConst().toString());
    }

    @Override
    public void inAArrayDeclaratorTail(AArrayDeclaratorTail node) {
        System.out.print(node.getIntConst().toString());
    }

    @Override
    public void inAIdLValue(AIdLValue node) {
        printIndentation();
        System.out.print("identifier name: ");
        System.out.println(node.getId().toString());
    }

    @Override
    public void inAStrLValue(AStrLValue node) {
        printIndentation();
        System.out.println("string literal: " + node.getStringLiteral().toString());
    }

    
    @Override
    public void inAExprLValue(AExprLValue node) {
        printIndentation();
        System.out.println("type: array access expression");
        addIndentationLevel();
        printIndentation();
        System.out.println("Array: [");
        addIndentationLevel();
    }
    
    @Override
    public void outAExprLValue(AExprLValue node) {
        removeIndentationLevel();
        printIndentation();
        System.out.println("]");
        removeIndentationLevel();
    }
    
    /* CASE OVERRIDE of AExprLValue HERE */
    @Override
    public void caseAExprLValue(AExprLValue node)
    {
        inAExprLValue(node);
        if(node.getLValue() != null)
        {
            node.getLValue().apply(this);
        }
        if(node.getLBracket() != null)
        {
            node.getLBracket().apply(this);
        }
        
        /* Print the array Access */
        removeIndentationLevel();
        printIndentation();
        System.out.println("]" );
        printIndentation();
        System.out.println("index: [" );
        addIndentationLevel();
                
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRBracket() != null)
        {
            node.getRBracket().apply(this);
        }
        outAExprLValue(node);
    }
    
    /*----------------------------*/

    @Override
    public void inAAddExpr(AAddExpr node) {
        printIndentation();
        System.out.println("type: add expression");
        addIndentationLevel();
    }
    
    @Override
    public void outAAddExpr(AAddExpr node) {
        removeIndentationLevel();
    }
    
    @Override
    public void inASubExpr(ASubExpr node) {
        printIndentation();
        System.out.println("type: sub expression");
        addIndentationLevel();
    }
    
    @Override
    public void outASubExpr(ASubExpr node) {
        removeIndentationLevel();
    }

    @Override
    public void inATermExpr(ATermExpr node) {
        defaultIn(node);
    }

    @Override
    public void inAMultTerm(AMultTerm node) {
        printIndentation();
        System.out.println("type: mult expression");
        addIndentationLevel();
    }

    @Override
    public void outAMultTerm(AMultTerm node) {
        removeIndentationLevel();
    }

    @Override
    public void inADivTerm(ADivTerm node) {
        printIndentation();
        System.out.println("type: div expression");
        addIndentationLevel();
    }

    @Override
    public void outADivTerm(ADivTerm node) {
        removeIndentationLevel();
    }

    @Override
    public void inAModTerm(AModTerm node) {
        printIndentation();
        System.out.println("type: mod expression");
        addIndentationLevel();
    }

    @Override
    public void outAModTerm(AModTerm node) {
        removeIndentationLevel();
    }

    @Override
    public void inASFactorTerm(ASFactorTerm node) {
        defaultIn(node);
    }

    @Override
    public void inAPosSignedFactor(APosSignedFactor node) {
        printIndentation();
        System.out.println("type: Possitive Factor");
        addIndentationLevel();
    }
    
    @Override
    public void outAPosSignedFactor(APosSignedFactor node) {
        removeIndentationLevel();
    }
    
    @Override
    public void inANegSignedFactor(ANegSignedFactor node) {
        printIndentation();
        System.out.println("type: Negative Factor");
        addIndentationLevel();
    }
    
    @Override
    public void outANegSignedFactor(ANegSignedFactor node) {
        removeIndentationLevel();
    }
    
    @Override
    public void inAFactorSignedFactor(AFactorSignedFactor node) {
        defaultIn(node);
    }

    @Override
    public void inAIntFactor(AIntFactor node) {
        printIndentation();
        System.out.println("Integer value: " + node.getIntConst().toString());
    }

    @Override
    public void inACharFactor(ACharFactor node) {
        printIndentation();
        System.out.print("Char const: " + node.getCharConst().toString());
    }

    @Override
    public void inALvalFactor(ALvalFactor node) {
        defaultIn(node);
    }

    @Override
    public void inAFuncFactor(AFuncFactor node) {
        defaultIn(node);
    }

    @Override
    public void inAParensFactor(AParensFactor node) {
        defaultIn(node);
    }

    @Override
    public void inAOrCond(AOrCond node) {
        printIndentation();
        printNLineIndent("type: condition");
        System.out.println("Operand: OR");
        addIndentationLevel();
    }
    
    @Override
    public void outAOrCond(AOrCond node) {
        removeIndentationLevel();
    }

    @Override
    public void inACond1Cond(ACond1Cond node) {
        defaultIn(node);
    }

    @Override
    public void inAAndCond1(AAndCond1 node) {
        printIndentation();
        printNLineIndent("type: condition");
        System.out.println("Operand: And");
        addIndentationLevel();
    }
    
    @Override
    public void outAAndCond1(AAndCond1 node) {
        removeIndentationLevel();
    }

    @Override
    public void inACond2Cond1(ACond2Cond1 node) {
        defaultIn(node);
    }

    @Override
    public void inANotCond2(ANotCond2 node) {
        printIndentation();
        printNLineIndent("type: condition");
        System.out.println("Operand: Not");
        addIndentationLevel();
    }
    
    @Override
    public void outANotCond2(ANotCond2 node) {
        removeIndentationLevel();
    }

    @Override
    public void inACond3Cond2(ACond3Cond2 node) {
        defaultIn(node);
    }

    @Override
    public void inAExprCmpCond3(AExprCmpCond3 node) {
        printIndentation();
        printNLineIndent("type: condition");
        System.out.println("Operand: " + node.getCmpOp());
        addIndentationLevel();
    }
    
    @Override
    public void outAExprCmpCond3(AExprCmpCond3 node) {
        removeIndentationLevel();
        //System.out.println();
    }
    
    @Override
    public void inABoolCond3(ABoolCond3 node) {
        defaultIn(node);
    }

    @Override
    public void inATrueBool(ATrueBool node) {
        printIndentation();
        System.out.println("boolean value: TRUE");
    }

    @Override
    public void inAFalseBool(AFalseBool node) {
        printIndentation();
        System.out.println("boolean value: FALSE");
    }

    @Override
    public void inACParensBool(ACParensBool node) {
        defaultIn(node);
    }
}
