/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class TKwFun extends Token
{
    public TKwFun()
    {
        super.setText("fun");
    }

    public TKwFun(int line, int pos)
    {
        super.setText("fun");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwFun(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwFun(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwFun text.");
    }
}
