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
    void caseADefVariableDefinition(ADefVariableDefinition node);
    void caseAMultiSetSetOfVariables(AMultiSetSetOfVariables node);
    void caseASingleSetSetOfVariables(ASingleSetSetOfVariables node);
    void caseASimpleType(ASimpleType node);
    void caseAArrayType(AArrayType node);
    void caseATIntDataType(ATIntDataType node);
    void caseATCharDataType(ATCharDataType node);

    void caseTWhiteSpace(TWhiteSpace node);
    void caseTLpar(TLpar node);
    void caseTRpar(TRpar node);
    void caseTDeftype(TDeftype node);
    void caseTCmdEnd(TCmdEnd node);
    void caseTComma(TComma node);
    void caseTAnd(TAnd node);
    void caseTOr(TOr node);
    void caseTInt(TInt node);
    void caseTThen(TThen node);
    void caseTChar(TChar node);
    void caseTMod(TMod node);
    void caseTVar(TVar node);
    void caseTDiv(TDiv node);
    void caseTNot(TNot node);
    void caseTWhile(TWhile node);
    void caseTDo(TDo node);
    void caseTIf(TIf node);
    void caseTElse(TElse node);
    void caseTFun(TFun node);
    void caseTRetrun(TRetrun node);
    void caseTRef(TRef node);
    void caseTNothing(TNothing node);
    void caseTVariable(TVariable node);
    void caseTIntegerConst(TIntegerConst node);
    void caseTArrayDeclaration(TArrayDeclaration node);
    void caseEOF(EOF node);
}
