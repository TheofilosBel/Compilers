/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AFIntFactor extends PFactor
{
    private TIntConst _intConst_;

    public AFIntFactor()
    {
        // Constructor
    }

    public AFIntFactor(
        @SuppressWarnings("hiding") TIntConst _intConst_)
    {
        // Constructor
        setIntConst(_intConst_);

    }

    @Override
    public Object clone()
    {
        return new AFIntFactor(
            cloneNode(this._intConst_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFIntFactor(this);
    }

    public TIntConst getIntConst()
    {
        return this._intConst_;
    }

    public void setIntConst(TIntConst node)
    {
        if(this._intConst_ != null)
        {
            this._intConst_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._intConst_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._intConst_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._intConst_ == child)
        {
            this._intConst_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._intConst_ == oldChild)
        {
            setIntConst((TIntConst) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
