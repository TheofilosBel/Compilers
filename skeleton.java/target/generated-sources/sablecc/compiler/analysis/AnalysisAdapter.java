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

    public void caseAExistingLocalDefList(AExistingLocalDefList node)
    {
        defaultCase(node);
    }

    public void caseANonExistingLocalDefList(ANonExistingLocalDefList node)
    {
        defaultCase(node);
    }

    public void caseAFuncDefLocalDef(AFuncDefLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAFuncDecLocalDef(AFuncDecLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAVarDefLocalDef(AVarDefLocalDef node)
    {
        defaultCase(node);
    }

    public void caseAFuncDef(AFuncDef node)
    {
        defaultCase(node);
    }

    public void caseAFuncDec(AFuncDec node)
    {
        defaultCase(node);
    }

    public void caseAFuncCall(AFuncCall node)
    {
        defaultCase(node);
    }

    public void caseAExistingFparList(AExistingFparList node)
    {
        defaultCase(node);
    }

    public void caseANonExistingFparList(ANonExistingFparList node)
    {
        defaultCase(node);
    }

    public void caseAByRefFparDef(AByRefFparDef node)
    {
        defaultCase(node);
    }

    public void caseAByValFparDef(AByValFparDef node)
    {
        defaultCase(node);
    }

    public void caseAVarDef(AVarDef node)
    {
        defaultCase(node);
    }

    public void caseAType(AType node)
    {
        defaultCase(node);
    }

    public void caseAExistingArrayDec(AExistingArrayDec node)
    {
        defaultCase(node);
    }

    public void caseAFirstEmptyArrayDec(AFirstEmptyArrayDec node)
    {
        defaultCase(node);
    }

    public void caseAEmptyArrayDec(AEmptyArrayDec node)
    {
        defaultCase(node);
    }

    public void caseANotExistingArrayDec(ANotExistingArrayDec node)
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

    public void caseANothDataType(ANothDataType node)
    {
        defaultCase(node);
    }

    public void caseAIfStmt(AIfStmt node)
    {
        defaultCase(node);
    }

    public void caseAWhileStmt(AWhileStmt node)
    {
        defaultCase(node);
    }

    public void caseAAssignStmt(AAssignStmt node)
    {
        defaultCase(node);
    }

    public void caseABlockStmt(ABlockStmt node)
    {
        defaultCase(node);
    }

    public void caseAFuncCallStmt(AFuncCallStmt node)
    {
        defaultCase(node);
    }

    public void caseAReturnStmt(AReturnStmt node)
    {
        defaultCase(node);
    }

    public void caseANoopStmt(ANoopStmt node)
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

    public void caseAMultExpr(AMultExpr node)
    {
        defaultCase(node);
    }

    public void caseADivExpr(ADivExpr node)
    {
        defaultCase(node);
    }

    public void caseAModExpr(AModExpr node)
    {
        defaultCase(node);
    }

    public void caseAPosExpr(APosExpr node)
    {
        defaultCase(node);
    }

    public void caseANegExpr(ANegExpr node)
    {
        defaultCase(node);
    }

    public void caseAIntExpr(AIntExpr node)
    {
        defaultCase(node);
    }

    public void caseACharExpr(ACharExpr node)
    {
        defaultCase(node);
    }

    public void caseALvalExpr(ALvalExpr node)
    {
        defaultCase(node);
    }

    public void caseAFuncExpr(AFuncExpr node)
    {
        defaultCase(node);
    }

    public void caseAStrLvalue(AStrLvalue node)
    {
        defaultCase(node);
    }

    public void caseAIdLvalue(AIdLvalue node)
    {
        defaultCase(node);
    }

    public void caseAArrayLvalue(AArrayLvalue node)
    {
        defaultCase(node);
    }

    public void caseAOrCond(AOrCond node)
    {
        defaultCase(node);
    }

    public void caseAAndCond(AAndCond node)
    {
        defaultCase(node);
    }

    public void caseANotCond(ANotCond node)
    {
        defaultCase(node);
    }

    public void caseAEqCond(AEqCond node)
    {
        defaultCase(node);
    }

    public void caseANeqCond(ANeqCond node)
    {
        defaultCase(node);
    }

    public void caseALtCond(ALtCond node)
    {
        defaultCase(node);
    }

    public void caseAGtCond(AGtCond node)
    {
        defaultCase(node);
    }

    public void caseAGteqCond(AGteqCond node)
    {
        defaultCase(node);
    }

    public void caseALteqCond(ALteqCond node)
    {
        defaultCase(node);
    }

    public void caseATrueCond(ATrueCond node)
    {
        defaultCase(node);
    }

    public void caseAFalseCond(AFalseCond node)
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

    public void caseTErroneousId(TErroneousId node)
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
