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
        printNLineIndent("type = function definition");
        System.out.print("name = ");
        /*WE add indent to ret_type for beautification reasons */
    }

    public void inAFuncDecl(AFuncDecl node) {
        printIndentation();
        printNLineIndent("type = function declaration");
        System.out.print("name = ");
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
        System.out.println("parameters = [");
    }

    public void outAHeader(AHeader node) {
        System.out.println();
    }

    public void inAFparList(AFparList node) {
        addIndentationLevel();
        printIndentation();
    }
    
    public void outAFparList(AFparList node) {
        removeIndentationLevel();
        printNLineIndent();
        System.out.print("]");
    }

    public void inAFparDef(AFparDef node) {
        System.out.print("name = ");
    }
    
    public void outAFparDef(AFparDef node) {
        printNLineIndent();
    }

    public void inAVarDef(AVarDef node) {
        printIndentation();
        System.out.println("type = variable definition");
        printIndentation();
        System.out.print("name = ");
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
        System.out.print("type = ");
    }

    public void inAType(AType node) {
        printNLineIndent();
        System.out.print("type = ");
    }

    public void inADataRetType(ADataRetType node) {
        printNLineIndent();
        System.out.print("return type = ");
    }
    
    public void outADataRetType(ADataRetType node) {
        printNLineIndent();
    }

    public void inANothingRetType(ANothingRetType node) {
        printNLineIndent();
        System.out.print("return type = nothing");
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
        defaultIn(node);
    }

    public void inAWhileMatched(AWhileMatched node) {
        defaultIn(node);
    }

    public void inANoopMatched(ANoopMatched node) {
        printIndentation();
        System.out.println("type = no operation statement");
    }

    public void inAAssignMatched(AAssignMatched node) {
        printIndentation();
        System.out.println("type = assignment");
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
        defaultIn(node);
    }

    public void inAWhileUnmatched(AWhileUnmatched node) {
        defaultIn(node);
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

    public void inAMatchedWhile(AMatchedWhile node) {
        defaultIn(node);
    }

    public void inAUnmatchedWhile(AUnmatchedWhile node) {
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
        printNLineIndent();
        printNLineIndent("type = function call");
        System.out.println("name = ");
    }

    public void inAMultiExprList(AMultiExprList node) {
        defaultIn(node);
    }

    public void inAEmptyExprList(AEmptyExprList node) {
        defaultIn(node);
    }

    public void inAExprListTail(AExprListTail node) {
        defaultIn(node);
    }

    public void inAArrayDeclArrayDeclarator(AArrayDeclArrayDeclarator node) {
        System.out.print("array with dimensions = ");
        System.out.print(node.getIntConst().toString());
    }

    public void inANoArrayDeclArrayDeclarator(ANoArrayDeclArrayDeclarator node) {
        defaultIn(node);
    }

    public void inAArrayDeclaratorTail(AArrayDeclaratorTail node) {
        System.out.print(node.getIntConst().toString());
    }

    public void inAEmptyArrayDeclarator(AEmptyArrayDeclarator node) {
        defaultIn(node);
    }

    public void inAIdLValue(AIdLValue node) {
        System.out.println(node.getId().toString());
    }

    public void inAStrLValue(AStrLValue node) {
        System.out.println(node.getStringLiteral().toString());
    }

    public void inAExprLValue(AExprLValue node) {
        defaultIn(node);
    }

    public void inAAddExpr(AAddExpr node) {
        defaultIn(node);
    }

    public void inASubExpr(ASubExpr node) {
        defaultIn(node);
    }

    public void inATermExpr(ATermExpr node) {
        defaultIn(node);
    }

    public void inAMultTerm(AMultTerm node) {
        defaultIn(node);
    }

    public void inADivTerm(ADivTerm node) {
        defaultIn(node);
    }

    public void inAModTerm(AModTerm node) {
        defaultIn(node);
    }

    public void inASFactorTerm(ASFactorTerm node) {
        defaultIn(node);
    }

    public void inAPosSignedFactor(APosSignedFactor node) {
        defaultIn(node);
    }

    public void inANegSignedFactor(ANegSignedFactor node) {
        defaultIn(node);
    }

    public void inAFactorSignedFactor(AFactorSignedFactor node) {
        defaultIn(node);
    }

    public void inAIntFactor(AIntFactor node) {
        System.out.print(node.getIntConst().toString());
    }

    public void inACharFactor(ACharFactor node) {
        System.out.print(node.getCharConst().toString());
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
        defaultIn(node);
    }

    public void inACond1Cond(ACond1Cond node) {
        defaultIn(node);
    }

    public void inAAndCond1(AAndCond1 node) {
        defaultIn(node);
    }

    public void inACond2Cond1(ACond2Cond1 node) {
        defaultIn(node);
    }

    public void inANotCond2(ANotCond2 node) {
        defaultIn(node);
    }

    public void inACond3Cond2(ACond3Cond2 node) {
        defaultIn(node);
    }

    public void inAExprCmpCond3(AExprCmpCond3 node) {
        defaultIn(node);
    }

    public void inABoolCond3(ABoolCond3 node) {
        defaultIn(node);
    }

    public void inATrueBool(ATrueBool node) {
        defaultIn(node);
    }

    public void inAFalseBool(AFalseBool node) {
        defaultIn(node);
    }

    public void inACParensBool(ACParensBool node) {
        defaultIn(node);
    }

    public void inAEqCmpOp(AEqCmpOp node) {
        defaultIn(node);
    }

    public void inANeqCmpOp(ANeqCmpOp node) {
        defaultIn(node);
    }

    public void inALtCmpOp(ALtCmpOp node) {
        defaultIn(node);
    }

    public void inAGtCmpOp(AGtCmpOp node) {
        defaultIn(node);
    }

    public void inALeqCmpOp(ALeqCmpOp node) {
        defaultIn(node);
    }

    public void inAGeqCmpOp(AGeqCmpOp node) {
        defaultIn(node);
    }
}
