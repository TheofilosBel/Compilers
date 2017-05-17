/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AGeqCmpOp extends PCmpOp
{
    private TGteq _gteq_;

    public AGeqCmpOp()
    {
        // Constructor
    }

    public AGeqCmpOp(
        @SuppressWarnings("hiding") TGteq _gteq_)
    {
        // Constructor
        setGteq(_gteq_);

    }

    @Override
    public Object clone()
    {
        return new AGeqCmpOp(
            cloneNode(this._gteq_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGeqCmpOp(this);
    }

    public TGteq getGteq()
    {
        return this._gteq_;
    }

    public void setGteq(TGteq node)
    {
        if(this._gteq_ != null)
        {
            this._gteq_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._gteq_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._gteq_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._gteq_ == child)
        {
            this._gteq_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._gteq_ == oldChild)
        {
            setGteq((TGteq) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}