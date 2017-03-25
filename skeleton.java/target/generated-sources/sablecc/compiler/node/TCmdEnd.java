/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class TCmdEnd extends Token
{
    public TCmdEnd()
    {
        super.setText(";");
    }

    public TCmdEnd(int line, int pos)
    {
        super.setText(";");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TCmdEnd(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTCmdEnd(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TCmdEnd text.");
    }
}
