/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.analysis;

import compiler.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAOneProgram(AOneProgram node);
    void caseASimpleFuncDef(ASimpleFuncDef node);
    void caseAFuncDecl(AFuncDecl node);
    void caseAInnerDeclLocalDef(AInnerDeclLocalDef node);
    void caseAInnerDefLocalDef(AInnerDefLocalDef node);
    void caseAInnerVarsLocalDef(AInnerVarsLocalDef node);
    void caseAFuncHeaderHeader(AFuncHeaderHeader node);
    void caseAMultiFparList(AMultiFparList node);
    void caseASingleFparList(ASingleFparList node);
    void caseAByValFparDef(AByValFparDef node);
    void caseAByRefFparDef(AByRefFparDef node);
    void caseAVarDefVarDef(AVarDefVarDef node);
    void caseAMultiSetIdList(AMultiSetIdList node);
    void caseASingleSetIdList(ASingleSetIdList node);
    void caseATypeFparType(ATypeFparType node);
    void caseASimpleType(ASimpleType node);
    void caseARetDataRetType(ARetDataRetType node);
    void caseARetNothRetType(ARetNothRetType node);
    void caseATIntDataType(ATIntDataType node);
    void caseATCharDataType(ATCharDataType node);
    void caseAEmptyStmt(AEmptyStmt node);
    void caseAOneBlock(AOneBlock node);
    void caseAArrayDeclArrayDeclarator(AArrayDeclArrayDeclarator node);
    void caseAEmptyDeclArrayDeclarator(AEmptyDeclArrayDeclarator node);
    void caseAEmptyArrayDeclEmptyArrayDeclarator(AEmptyArrayDeclEmptyArrayDeclarator node);
    void caseALvalIdLValue(ALvalIdLValue node);
    void caseALvalStrLValue(ALvalStrLValue node);
    void caseALvalExprLValue(ALvalExprLValue node);
    void caseAAddExpr(AAddExpr node);
    void caseASubExpr(ASubExpr node);
    void caseATermExpr(ATermExpr node);
    void caseAMultTerm(AMultTerm node);
    void caseADivTerm(ADivTerm node);
    void caseAModTerm(AModTerm node);
    void caseASFactorTerm(ASFactorTerm node);
    void caseAPosSignedFactor(APosSignedFactor node);
    void caseANegSignedFactor(ANegSignedFactor node);
    void caseAFactorSignedFactor(AFactorSignedFactor node);
    void caseAFIntFactor(AFIntFactor node);
    void caseAFParensFactor(AFParensFactor node);

    void caseTWhiteSpace(TWhiteSpace node);
    void caseTComment(TComment node);
    void caseTLPar(TLPar node);
    void caseTRPar(TRPar node);
    void caseTLBracket(TLBracket node);
    void caseTRBracket(TRBracket node);
    void caseTLBrace(TLBrace node);
    void caseTRBrace(TRBrace node);
    void caseTColon(TColon node);
    void caseTSemicolon(TSemicolon node);
    void caseTComma(TComma node);
    void caseTArrow(TArrow node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTMult(TMult node);
    void caseTEq(TEq node);
    void caseTNeq(TNeq node);
    void caseTLt(TLt node);
    void caseTGt(TGt node);
    void caseTLteq(TLteq node);
    void caseTGteq(TGteq node);
    void caseTKwAnd(TKwAnd node);
    void caseTKwOr(TKwOr node);
    void caseTKwInt(TKwInt node);
    void caseTKwThen(TKwThen node);
    void caseTKwChar(TKwChar node);
    void caseTKwMod(TKwMod node);
    void caseTKwVar(TKwVar node);
    void caseTKwDiv(TKwDiv node);
    void caseTKwNot(TKwNot node);
    void caseTKwWhile(TKwWhile node);
    void caseTKwDo(TKwDo node);
    void caseTKwIf(TKwIf node);
    void caseTKwElse(TKwElse node);
    void caseTKwFun(TKwFun node);
    void caseTKwReturn(TKwReturn node);
    void caseTKwRef(TKwRef node);
    void caseTKwNothing(TKwNothing node);
    void caseTIntConst(TIntConst node);
    void caseTCharConst(TCharConst node);
    void caseTStringLiteral(TStringLiteral node);
    void caseTId(TId node);
    void caseEOF(EOF node);
}
