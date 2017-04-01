/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class TKwRef extends Token
{
    public TKwRef()
    {
        super.setText("ref");
    }

    public TKwRef(int line, int pos)
    {
        super.setText("ref");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwRef(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwRef(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwRef text.");
    }
}
