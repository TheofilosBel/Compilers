/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.parser;

import compiler.node.*;
import compiler.analysis.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTLpar(@SuppressWarnings("unused") TLpar node)
    {
        this.index = 0;
    }

    @Override
    public void caseTRpar(@SuppressWarnings("unused") TRpar node)
    {
        this.index = 1;
    }

    @Override
    public void caseTDeftype(@SuppressWarnings("unused") TDeftype node)
    {
        this.index = 2;
    }

    @Override
    public void caseTCmdEnd(@SuppressWarnings("unused") TCmdEnd node)
    {
        this.index = 3;
    }

    @Override
    public void caseTComma(@SuppressWarnings("unused") TComma node)
    {
        this.index = 4;
    }

    @Override
    public void caseTAnd(@SuppressWarnings("unused") TAnd node)
    {
        this.index = 5;
    }

    @Override
    public void caseTOr(@SuppressWarnings("unused") TOr node)
    {
        this.index = 6;
    }

    @Override
    public void caseTInt(@SuppressWarnings("unused") TInt node)
    {
        this.index = 7;
    }

    @Override
    public void caseTThen(@SuppressWarnings("unused") TThen node)
    {
        this.index = 8;
    }

    @Override
    public void caseTChar(@SuppressWarnings("unused") TChar node)
    {
        this.index = 9;
    }

    @Override
    public void caseTMod(@SuppressWarnings("unused") TMod node)
    {
        this.index = 10;
    }

    @Override
    public void caseTVar(@SuppressWarnings("unused") TVar node)
    {
        this.index = 11;
    }

    @Override
    public void caseTDiv(@SuppressWarnings("unused") TDiv node)
    {
        this.index = 12;
    }

    @Override
    public void caseTNot(@SuppressWarnings("unused") TNot node)
    {
        this.index = 13;
    }

    @Override
    public void caseTWhile(@SuppressWarnings("unused") TWhile node)
    {
        this.index = 14;
    }

    @Override
    public void caseTDo(@SuppressWarnings("unused") TDo node)
    {
        this.index = 15;
    }

    @Override
    public void caseTIf(@SuppressWarnings("unused") TIf node)
    {
        this.index = 16;
    }

    @Override
    public void caseTElse(@SuppressWarnings("unused") TElse node)
    {
        this.index = 17;
    }

    @Override
    public void caseTFun(@SuppressWarnings("unused") TFun node)
    {
        this.index = 18;
    }

    @Override
    public void caseTRetrun(@SuppressWarnings("unused") TRetrun node)
    {
        this.index = 19;
    }

    @Override
    public void caseTRef(@SuppressWarnings("unused") TRef node)
    {
        this.index = 20;
    }

    @Override
    public void caseTNothing(@SuppressWarnings("unused") TNothing node)
    {
        this.index = 21;
    }

    @Override
    public void caseTVariable(@SuppressWarnings("unused") TVariable node)
    {
        this.index = 22;
    }

    @Override
    public void caseTIntegerConst(@SuppressWarnings("unused") TIntegerConst node)
    {
        this.index = 23;
    }

    @Override
    public void caseTArrayDeclaration(@SuppressWarnings("unused") TArrayDeclaration node)
    {
        this.index = 24;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 25;
    }
}
