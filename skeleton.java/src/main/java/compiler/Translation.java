package compiler;

import compiler.analysis.*;
import compiler.node.*;
import java.util.Collections;

public class Translation extends DepthFirstAdapter {
    public void inAProgram(AProgram node) {
        defaultIn(node);
    }

    public void inAFuncDef(AFuncDef node) {
        defaultIn(node);
    }

    public void inAFuncDecl(AFuncDecl node) {
        defaultIn(node);
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
        defaultIn(node);
    }

    public void inAMultiFparList(AMultiFparList node) {
        defaultIn(node);
    }

    public void inASingleFparList(ASingleFparList node) {
        defaultIn(node);
    }
    public void inAFparDef(AFparDef node) {
        defaultIn(node);
    }

    public void inAVarDef(AVarDef node) {
        defaultIn(node);
    }

    public void inAMultiIdList(AMultiIdList node) {
        defaultIn(node);
    }

    public void inASingleIdList(ASingleIdList node) {
        defaultIn(node);
    }

    public void inAFparType(AFparType node) {
        defaultIn(node);
    }

    public void inAType(AType node) {
        defaultIn(node);
    }

    public void inADataRetType(ADataRetType node) {
        defaultIn(node);
    }

    public void inANothingRetType(ANothingRetType node) {
        defaultIn(node);
    }

    public void inAIntDataType(AIntDataType node) {
        defaultIn(node);
    }

    public void inACharDataType(ACharDataType node) {
        defaultIn(node);
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
        defaultIn(node);
    }

    public void inAAssignMatched(AAssignMatched node) {
        defaultIn(node);
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
        defaultIn(node);
    }

    public void inAFuncCall(AFuncCall node) {
        defaultIn(node);
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
        defaultIn(node);
    }

    public void inANoArrayDeclArrayDeclarator(ANoArrayDeclArrayDeclarator node) {
        defaultIn(node);
    }

    public void inAArrayDeclaratorTail(AArrayDeclaratorTail node) {
        defaultIn(node);
    }

    public void inAEmptyArrayDeclarator(AEmptyArrayDeclarator node) {
        defaultIn(node);
    }

    public void inAIdLValue(AIdLValue node) {
        defaultIn(node);
    }

    public void inAStrLValue(AStrLValue node) {
        defaultIn(node);
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
        defaultIn(node);
    }

    public void inACharFactor(ACharFactor node) {
        defaultIn(node);
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
