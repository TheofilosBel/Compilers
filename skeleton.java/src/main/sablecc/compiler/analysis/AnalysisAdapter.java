/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.analysis;

import java.util.*;
import compiler.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
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

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
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

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAProgram(AProgram node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExistingLocalDefList(AExistingLocalDefList node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANonExistingLocalDefList(ANonExistingLocalDefList node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFuncDefLocalDef(AFuncDefLocalDef node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFuncDecLocalDef(AFuncDecLocalDef node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarDefLocalDef(AVarDefLocalDef node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFuncDef(AFuncDef node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFuncDec(AFuncDec node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFuncCall(AFuncCall node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExistingFparList(AExistingFparList node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANonExistingFparList(ANonExistingFparList node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarDef(AVarDef node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVariable(AVariable node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAType(AType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExistingArrayDec(AExistingArrayDec node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANotExistingArrayDec(ANotExistingArrayDec node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIntDataType(AIntDataType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseACharDataType(ACharDataType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANothDataType(ANothDataType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANoneDataType(ANoneDataType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIfStmt(AIfStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAWhileStmt(AWhileStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAssignStmt(AAssignStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABlockStmt(ABlockStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFuncCallStmt(AFuncCallStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAReturnStmt(AReturnStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANoopStmt(ANoopStmt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAddExpr(AAddExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASubExpr(ASubExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultExpr(AMultExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADivExpr(ADivExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAModExpr(AModExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPosExpr(APosExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANegExpr(ANegExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIntExpr(AIntExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseACharExpr(ACharExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALvalExpr(ALvalExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFuncExpr(AFuncExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAStrLvalue(AStrLvalue node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIdLvalue(AIdLvalue node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAArrayLvalue(AArrayLvalue node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOrCond(AOrCond node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAndCond(AAndCond node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANotCond(ANotCond node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExprCmpCond(AExprCmpCond node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATrueCond(ATrueCond node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFalseCond(AFalseCond node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEqCmpOp(AEqCmpOp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANeqCmpOp(ANeqCmpOp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALtCmpOp(ALtCmpOp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGtCmpOp(AGtCmpOp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGeqCmpOp(AGeqCmpOp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALeqCmpOp(ALeqCmpOp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTWhiteSpace(TWhiteSpace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComment(TComment node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLPar(TLPar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRPar(TRPar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLBracket(TLBracket node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRBracket(TRBracket node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLBrace(TLBrace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRBrace(TRBrace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTColon(TColon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSemicolon(TSemicolon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAssign(TAssign node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMult(TMult node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEq(TEq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNeq(TNeq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLt(TLt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTGt(TGt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLteq(TLteq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTGteq(TGteq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwAnd(TKwAnd node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwOr(TKwOr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwInt(TKwInt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwThen(TKwThen node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwChar(TKwChar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwMod(TKwMod node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwVar(TKwVar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwDiv(TKwDiv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwNot(TKwNot node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwWhile(TKwWhile node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwDo(TKwDo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwIf(TKwIf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwElse(TKwElse node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwFun(TKwFun node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwReturn(TKwReturn node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwRef(TKwRef node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTKwNothing(TKwNothing node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTIntConst(TIntConst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCharConst(TCharConst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTStringLiteral(TStringLiteral node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTrue(TTrue node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFalse(TFalse node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTId(TId node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}