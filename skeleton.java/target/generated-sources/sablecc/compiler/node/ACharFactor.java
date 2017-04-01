/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class ACharFactor extends PFactor
{
    private TCharConst _charConst_;

    public ACharFactor()
    {
        // Constructor
    }

    public ACharFactor(
        @SuppressWarnings("hiding") TCharConst _charConst_)
    {
        // Constructor
        setCharConst(_charConst_);

    }

    @Override
    public Object clone()
    {
        return new ACharFactor(
            cloneNode(this._charConst_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACharFactor(this);
    }

    public TCharConst getCharConst()
    {
        return this._charConst_;
    }

    public void setCharConst(TCharConst node)
    {
        if(this._charConst_ != null)
        {
            this._charConst_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._charConst_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._charConst_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._charConst_ == child)
        {
            this._charConst_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._charConst_ == oldChild)
        {
            setCharConst((TCharConst) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
