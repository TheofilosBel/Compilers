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
    
    public void inAProgram(AProgram node) {
        defaultIn(node);
    }

    public void inAFuncDef(AFuncDef node) {
        printIndentation();
        printNLineIndent("type: function definition");
        System.out.print("name: ");
    }

    public void inAFuncDecl(AFuncDecl node) {
        printIndentation();
        printNLineIndent("type: function declaration");
        System.out.print("name: ");
    }

    public void inAInnerDeclLocalDef(AInnerDeclLocalDef node) {
        defaultIn(node);
    }

    public void inAInnerDefLocalDef(AInnerDefLocalDef node) {
        defaultIn(node);
    }

    public void inAInnerVarLocalDef(AInnerVarLocalDef node) {
        defaultIn(node);
    }

    public void inAHeader(AHeader node) {
        System.out.print(node.getId().toString());
        printNLineIndent();
        System.out.print("parameters:");
    }

    public void outAHeader(AHeader node) {
        System.out.println();
    }

    public void inAFparList(AFparList node) {
        System.out.println(" [");
        addIndentationLevel();
        printIndentation();
    }
    
    public void outAFparList(AFparList node) {
        removeIndentationLevel();
        printNLineIndent();
        System.out.print("]");
    }

    public void inAFparDef(AFparDef node) {
        System.out.print("name: ");
    }
    
    public void outAFparDef(AFparDef node) {
        defaultOut(node);
    }

    public void inAVarDef(AVarDef node) {
        printIndentation();
        System.out.println("type: variable definition");
        printIndentation();
        System.out.print("name: ");
    }
    
    public void outAVarDef(AVarDef node) {
        System.out.println();
        System.out.println();
    }

    public void inAMultiIdList(AMultiIdList node) {
        System.out.print(node.getId().toString());
    }

    public void inASingleIdList(ASingleIdList node) {
        System.out.print(node.getId().toString());
    }

    public void inAFparType(AFparType node) {
        printNLineIndent();
        System.out.print("type: ");
    }

    public void inAType(AType node) {
        printNLineIndent();
        System.out.print("type: ");
    }

    public void inADataRetType(ADataRetType node) {
        printNLineIndent();
        System.out.print("return type: ");
    }
    
    public void outADataRetType(ADataRetType node) {
        printNLineIndent();
    }

    public void inANothingRetType(ANothingRetType node) {
        printNLineIndent();
        System.out.print("return type: nothing");
        addIndentationLevel();
    }
    
    public void outANothingRetType(ADataRetType node) {
        System.out.println();
        addIndentationLevel();
    }

    public void inAIntDataType(AIntDataType node) {
        System.out.print(node.toString());
    }

    public void inACharDataType(ACharDataType node) {
        System.out.print(node.toString());
    }

    public void inAMatchedStmt(AMatchedStmt node) {
        defaultIn(node);
    }

    public void inAUnmatchedStmt(AUnmatchedStmt node) {
        defaultIn(node);
    }

    public void inAIfMatched(AIfMatched node) {
        printIndentation();
        System.out.println("type: if-else statement");
        addIndentationLevel();
    }

    public void outAIfMatched(AIfMatched node) {
        removeIndentationLevel();
    }

    public void inAWhileMatched(AWhileMatched node) {
        printIndentation();
        System.out.println("type: while statement");
        addIndentationLevel();
    }

    public void outAWhileMatched(AIfMatched node) {
        removeIndentationLevel();
    }

    public void inANoopMatched(ANoopMatched node) {
        printIndentation();
        System.out.println("type: no operation statement");
    }

    public void inAAssignMatched(AAssignMatched node) {
        printIndentation();
        System.out.println("type: assignment");
    }

    public void outAAssignMatched(AAssignMatched node) {
        System.out.println();
    }

    public void inABlockMatched(ABlockMatched node) {
        defaultIn(node);
    }

    public void inAFuncCallMatched(AFuncCallMatched node) {
        defaultIn(node);
    }

    public void inAReturnMatched(AReturnMatched node) {
        defaultIn(node);
    }

    public void inAIfUnmatched(AIfUnmatched node) {
        printIndentation();
        System.out.println("type: if-only statement");
        addIndentationLevel();
    }

    public void outAIfUnmatched(AIfUnmatched node) {
        removeIndentationLevel();
    }

    public void inAWhileUnmatched(AWhileUnmatched node) {
        printIndentation();
        System.out.println("type: while statement");
        addIndentationLevel();
    }

    public void outAWhileUnmatched(AWhileUnmatched node) {
        removeIndentationLevel();
    }

    public void inAMatchedIf(AMatchedIf node) {
        defaultIn(node);
    }

    public void inANoElseUnmatchedIf(ANoElseUnmatchedIf node) {
        defaultIn(node);
    }

    public void inAWithElseUnmatchedIf(AWithElseUnmatchedIf node) {
        defaultIn(node);
    }

    public void inABlock(ABlock node) {
        printIndentation();
        System.out.println("{");
        addIndentationLevel();
    }

    public void outABlock(ABlock node) {
        removeIndentationLevel();
        printIndentation();
        System.out.println("}");
    }

    public void inAFuncCall(AFuncCall node) {
        printIndentation();
        printNLineIndent("type: function call");
        System.out.print("name: ");
        System.out.println(node.getId().toString());
        printIndentation();
        System.out.println("parameters: [");
    }
    
    public void outAFuncCall(AFuncCall node) {
        System.out.println();
    }

    public void inAMultiExprList(AMultiExprList node) {
        addIndentationLevel();
    }

    public void outAMultiExprList(AMultiExprList node) {
        removeIndentationLevel();
        printIndentation();
        System.out.println("]");
    }

    public void inAEmptyExprList(AEmptyExprList node) {
        defaultIn(node);
    }

    public void inAExprListTail(AExprListTail node) {
        defaultIn(node);
    }

    public void inAArrayDeclArrayDeclarator(AArrayDeclArrayDeclarator node) {
        System.out.print("array with dimensions: ");
        System.out.print(node.getIntConst().toString());
    }

    public void inAArrayDeclaratorTail(AArrayDeclaratorTail node) {
        System.out.print(node.getIntConst().toString());
    }

    public void inAIdLValue(AIdLValue node) {
        printIndentation();
        System.out.print("identifier name: ");
        System.out.println(node.getId().toString());
    }

    public void inAStrLValue(AStrLValue node) {
        printIndentation();
        System.out.println("String literal: " + node.getStringLiteral().toString());
    }

    public void inAExprLValue(AExprLValue node) {
        defaultIn(node);
    }

    public void inAAddExpr(AAddExpr node) {
        printIndentation();
        System.out.println("type: add expression");
        addIndentationLevel();
    }
    
    public void outAAddExpr(AAddExpr node) {
        removeIndentationLevel();
    }
    
    public void inASubExpr(ASubExpr node) {
        printIndentation();
        System.out.println("type: sub expression");
        addIndentationLevel();
    }
    
    public void outASubExpr(ASubExpr node) {
        removeIndentationLevel();
    }

    public void inATermExpr(ATermExpr node) {
        defaultIn(node);
    }

    public void inAMultTerm(AMultTerm node) {
        printIndentation();
        System.out.println("type: mult expression");
        addIndentationLevel();
    }

    public void outAMultTerm(AMultTerm node) {
        removeIndentationLevel();
    }

    public void inADivTerm(ADivTerm node) {
        printIndentation();
        System.out.println("type: div expression");
        addIndentationLevel();
    }

    public void outADivTerm(ADivTerm node) {
        removeIndentationLevel();
    }

    public void inAModTerm(AModTerm node) {
        printIndentation();
        System.out.println("type: mod expression");
        addIndentationLevel();
    }

    public void outAModTerm(AModTerm node) {
        removeIndentationLevel();
    }

    public void inASFactorTerm(ASFactorTerm node) {
        defaultIn(node);
    }

    public void inAPosSignedFactor(APosSignedFactor node) {
        printIndentation();
        System.out.println("type: Possitive Factor");
        addIndentationLevel();
    }
    
    public void outAPosSignedFactor(APosSignedFactor node) {
        removeIndentationLevel();
    }
    
    public void inANegSignedFactor(ANegSignedFactor node) {
        printIndentation();
        System.out.println("type: Negative Factor");
        addIndentationLevel();
    }
    
    public void outANegSignedFactor(ANegSignedFactor node) {
        removeIndentationLevel();
    }
    
    public void inAFactorSignedFactor(AFactorSignedFactor node) {
        defaultIn(node);
    }

    public void inAIntFactor(AIntFactor node) {
        printIndentation();
        System.out.println("Integer value: " + node.getIntConst().toString());
    }

    public void inACharFactor(ACharFactor node) {
        printIndentation();
        System.out.print("Char const: " + node.getCharConst().toString());
    }

    public void inALvalFactor(ALvalFactor node) {
        defaultIn(node);
    }

    public void inAFuncFactor(AFuncFactor node) {
        defaultIn(node);
    }

    public void inAParensFactor(AParensFactor node) {
        defaultIn(node);
    }

    public void inAOrCond(AOrCond node) {
        printIndentation();
        printNLineIndent("type: condition");
        System.out.println("Operand: OR");
        addIndentationLevel();
    }
    
    public void outAOrCond(AOrCond node) {
        removeIndentationLevel();
    }

    public void inACond1Cond(ACond1Cond node) {
        defaultIn(node);
    }

    public void inAAndCond1(AAndCond1 node) {
        printIndentation();
        printNLineIndent("type: condition");
        System.out.println("Operand: And");
        addIndentationLevel();
    }
    
    public void outAAndCond1(AAndCond1 node) {
        removeIndentationLevel();
    }

    public void inACond2Cond1(ACond2Cond1 node) {
        defaultIn(node);
    }

    public void inANotCond2(ANotCond2 node) {
        printIndentation();
        printNLineIndent("type: condition");
        System.out.println("Operand: Not");
        addIndentationLevel();
    }
    
    public void outANotCond2(ANotCond2 node) {
        removeIndentationLevel();
    }

    public void inACond3Cond2(ACond3Cond2 node) {
        defaultIn(node);
    }

    public void inAExprCmpCond3(AExprCmpCond3 node) {
        printIndentation();
        printNLineIndent("type: condition");
        System.out.println("Operand: " + node.getCmpOp());
        addIndentationLevel();
    }
    
    public void outAExprCmpCond3(AExprCmpCond3 node) {
        removeIndentationLevel();
    }
    
    public void inABoolCond3(ABoolCond3 node) {
        defaultIn(node);
    }

    public void inATrueBool(ATrueBool node) {
        printIndentation();
        System.out.println("boolean value: TRUE");
    }

    public void inAFalseBool(AFalseBool node) {
        printIndentation();
        System.out.println("boolean value: FALSE");
    }

    public void inACParensBool(ACParensBool node) {
        defaultIn(node);
    }
}
