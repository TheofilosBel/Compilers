/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.analysis;

import java.util.*;
import compiler.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    public void caseAProgram(AProgram node)
    {
        defaultCase(node);
    }

    public void caseAFuncDef(AFuncDef node)
    {
        defaultCase(node);
    }

    public void caseAFuncDecl(AFuncDecl node)
    {
        defaultCase(node);
    }

    public void caseAInnerDeclLocalDef(AInnerDeclLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAInnerDefLocalDef(AInnerDefLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAInnerVarLocalDef(AInnerVarLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAHeader(AHeader node)
    {
        defaultCase(node);
    }

    public void caseAFparList(AFparList node)
    {
        defaultCase(node);
    }

    public void caseAFparListTail(AFparListTail node)
    {
        defaultCase(node);
    }

    public void caseAFparDef(AFparDef node)
    {
        defaultCase(node);
    }

    public void caseAVarDef(AVarDef node)
    {
        defaultCase(node);
    }

    public void caseAMultiIdList(AMultiIdList node)
    {
        defaultCase(node);
    }

    public void caseASingleIdList(ASingleIdList node)
    {
        defaultCase(node);
    }

    public void caseAFparType(AFparType node)
    {
        defaultCase(node);
    }

    public void caseAType(AType node)
    {
        defaultCase(node);
    }

    public void caseADataRetType(ADataRetType node)
    {
        defaultCase(node);
    }

    public void caseANothingRetType(ANothingRetType node)
    {
        defaultCase(node);
    }

    public void caseAIntDataType(AIntDataType node)
    {
        defaultCase(node);
    }

    public void caseACharDataType(ACharDataType node)
    {
        defaultCase(node);
    }

    public void caseAMatchedStmt(AMatchedStmt node)
    {
        defaultCase(node);
    }

    public void caseAUnmatchedStmt(AUnmatchedStmt node)
    {
        defaultCase(node);
    }

    public void caseAIfMatched(AIfMatched node)
    {
        defaultCase(node);
    }

    public void caseAWhileMatched(AWhileMatched node)
    {
        defaultCase(node);
    }

    public void caseANoopMatched(ANoopMatched node)
    {
        defaultCase(node);
    }

    public void caseAAssignMatched(AAssignMatched node)
    {
        defaultCase(node);
    }

    public void caseABlockMatched(ABlockMatched node)
    {
        defaultCase(node);
    }

    public void caseAFuncCallMatched(AFuncCallMatched node)
    {
        defaultCase(node);
    }

    public void caseAReturnMatched(AReturnMatched node)
    {
        defaultCase(node);
    }

    public void caseAIfUnmatched(AIfUnmatched node)
    {
        defaultCase(node);
    }

    public void caseAWhileUnmatched(AWhileUnmatched node)
    {
        defaultCase(node);
    }

    public void caseAMatchedIf(AMatchedIf node)
    {
        defaultCase(node);
    }

    public void caseANoElseUnmatchedIf(ANoElseUnmatchedIf node)
    {
        defaultCase(node);
    }

    public void caseAWithElseUnmatchedIf(AWithElseUnmatchedIf node)
    {
        defaultCase(node);
    }

    public void caseAMatchedWhile(AMatchedWhile node)
    {
        defaultCase(node);
    }

    public void caseAUnmatchedWhile(AUnmatchedWhile node)
    {
        defaultCase(node);
    }

    public void caseABlock(ABlock node)
    {
        defaultCase(node);
    }

    public void caseAFuncCall(AFuncCall node)
    {
        defaultCase(node);
    }

    public void caseAMultiExprList(AMultiExprList node)
    {
        defaultCase(node);
    }

    public void caseAEmptyExprList(AEmptyExprList node)
    {
        defaultCase(node);
    }

    public void caseAExprListTail(AExprListTail node)
    {
        defaultCase(node);
    }

    public void caseAArrayDeclArrayDeclarator(AArrayDeclArrayDeclarator node)
    {
        defaultCase(node);
    }

    public void caseANoArrayDeclArrayDeclarator(ANoArrayDeclArrayDeclarator node)
    {
        defaultCase(node);
    }

    public void caseAArrayDeclaratorTail(AArrayDeclaratorTail node)
    {
        defaultCase(node);
    }

    public void caseAEmptyArrayDeclarator(AEmptyArrayDeclarator node)
    {
        defaultCase(node);
    }

    public void caseAIdLValue(AIdLValue node)
    {
        defaultCase(node);
    }

    public void caseAStrLValue(AStrLValue node)
    {
        defaultCase(node);
    }

    public void caseAExprLValue(AExprLValue node)
    {
        defaultCase(node);
    }

    public void caseAAddExpr(AAddExpr node)
    {
        defaultCase(node);
    }

    public void caseASubExpr(ASubExpr node)
    {
        defaultCase(node);
    }

    public void caseATermExpr(ATermExpr node)
    {
        defaultCase(node);
    }

    public void caseAMultTerm(AMultTerm node)
    {
        defaultCase(node);
    }

    public void caseADivTerm(ADivTerm node)
    {
        defaultCase(node);
    }

    public void caseAModTerm(AModTerm node)
    {
        defaultCase(node);
    }

    public void caseASFactorTerm(ASFactorTerm node)
    {
        defaultCase(node);
    }

    public void caseAPosSignedFactor(APosSignedFactor node)
    {
        defaultCase(node);
    }

    public void caseANegSignedFactor(ANegSignedFactor node)
    {
        defaultCase(node);
    }

    public void caseAFactorSignedFactor(AFactorSignedFactor node)
    {
        defaultCase(node);
    }

    public void caseAIntFactor(AIntFactor node)
    {
        defaultCase(node);
    }

    public void caseACharFactor(ACharFactor node)
    {
        defaultCase(node);
    }

    public void caseALvalFactor(ALvalFactor node)
    {
        defaultCase(node);
    }

    public void caseAFuncFactor(AFuncFactor node)
    {
        defaultCase(node);
    }

    public void caseAParensFactor(AParensFactor node)
    {
        defaultCase(node);
    }

    public void caseAOrCond(AOrCond node)
    {
        defaultCase(node);
    }

    public void caseACond1Cond(ACond1Cond node)
    {
        defaultCase(node);
    }

    public void caseAAndCond1(AAndCond1 node)
    {
        defaultCase(node);
    }

    public void caseACond2Cond1(ACond2Cond1 node)
    {
        defaultCase(node);
    }

    public void caseANotCond2(ANotCond2 node)
    {
        defaultCase(node);
    }

    public void caseACond3Cond2(ACond3Cond2 node)
    {
        defaultCase(node);
    }

    public void caseAExprCmpCond3(AExprCmpCond3 node)
    {
        defaultCase(node);
    }

    public void caseABoolCond3(ABoolCond3 node)
    {
        defaultCase(node);
    }

    public void caseATrueBool(ATrueBool node)
    {
        defaultCase(node);
    }

    public void caseAFalseBool(AFalseBool node)
    {
        defaultCase(node);
    }

    public void caseACParensBool(ACParensBool node)
    {
        defaultCase(node);
    }

    public void caseAEqCmpOp(AEqCmpOp node)
    {
        defaultCase(node);
    }

    public void caseANeqCmpOp(ANeqCmpOp node)
    {
        defaultCase(node);
    }

    public void caseALtCmpOp(ALtCmpOp node)
    {
        defaultCase(node);
    }

    public void caseAGtCmpOp(AGtCmpOp node)
    {
        defaultCase(node);
    }

    public void caseALeqCmpOp(ALeqCmpOp node)
    {
        defaultCase(node);
    }

    public void caseAGeqCmpOp(AGeqCmpOp node)
    {
        defaultCase(node);
    }

    public void caseTWhiteSpace(TWhiteSpace node)
    {
        defaultCase(node);
    }

    public void caseTComment(TComment node)
    {
        defaultCase(node);
    }

    public void caseTLPar(TLPar node)
    {
        defaultCase(node);
    }

    public void caseTRPar(TRPar node)
    {
        defaultCase(node);
    }

    public void caseTLBracket(TLBracket node)
    {
        defaultCase(node);
    }

    public void caseTRBracket(TRBracket node)
    {
        defaultCase(node);
    }

    public void caseTLBrace(TLBrace node)
    {
        defaultCase(node);
    }

    public void caseTRBrace(TRBrace node)
    {
        defaultCase(node);
    }

    public void caseTColon(TColon node)
    {
        defaultCase(node);
    }

    public void caseTSemicolon(TSemicolon node)
    {
        defaultCase(node);
    }

    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    public void caseTAssign(TAssign node)
    {
        defaultCase(node);
    }

    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    public void caseTMult(TMult node)
    {
        defaultCase(node);
    }

    public void caseTEq(TEq node)
    {
        defaultCase(node);
    }

    public void caseTNeq(TNeq node)
    {
        defaultCase(node);
    }

    public void caseTLt(TLt node)
    {
        defaultCase(node);
    }

    public void caseTGt(TGt node)
    {
        defaultCase(node);
    }

    public void caseTLteq(TLteq node)
    {
        defaultCase(node);
    }

    public void caseTGteq(TGteq node)
    {
        defaultCase(node);
    }

    public void caseTKwAnd(TKwAnd node)
    {
        defaultCase(node);
    }

    public void caseTKwOr(TKwOr node)
    {
        defaultCase(node);
    }

    public void caseTKwInt(TKwInt node)
    {
        defaultCase(node);
    }

    public void caseTKwThen(TKwThen node)
    {
        defaultCase(node);
    }

    public void caseTKwChar(TKwChar node)
    {
        defaultCase(node);
    }

    public void caseTKwMod(TKwMod node)
    {
        defaultCase(node);
    }

    public void caseTKwVar(TKwVar node)
    {
        defaultCase(node);
    }

    public void caseTKwDiv(TKwDiv node)
    {
        defaultCase(node);
    }

    public void caseTKwNot(TKwNot node)
    {
        defaultCase(node);
    }

    public void caseTKwWhile(TKwWhile node)
    {
        defaultCase(node);
    }

    public void caseTKwDo(TKwDo node)
    {
        defaultCase(node);
    }

    public void caseTKwIf(TKwIf node)
    {
        defaultCase(node);
    }

    public void caseTKwElse(TKwElse node)
    {
        defaultCase(node);
    }

    public void caseTKwFun(TKwFun node)
    {
        defaultCase(node);
    }

    public void caseTKwReturn(TKwReturn node)
    {
        defaultCase(node);
    }

    public void caseTKwRef(TKwRef node)
    {
        defaultCase(node);
    }

    public void caseTKwNothing(TKwNothing node)
    {
        defaultCase(node);
    }

    public void caseTIntConst(TIntConst node)
    {
        defaultCase(node);
    }

    public void caseTCharConst(TCharConst node)
    {
        defaultCase(node);
    }

    public void caseTStringLiteral(TStringLiteral node)
    {
        defaultCase(node);
    }

    public void caseTTrue(TTrue node)
    {
        defaultCase(node);
    }

    public void caseTFalse(TFalse node)
    {
        defaultCase(node);
    }

    public void caseTId(TId node)
    {
        defaultCase(node);
    }

    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
