/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class ANeqCmpOp extends PCmpOp
{
    private TNeq _neq_;

    public ANeqCmpOp()
    {
        // Constructor
    }

    public ANeqCmpOp(
        @SuppressWarnings("hiding") TNeq _neq_)
    {
        // Constructor
        setNeq(_neq_);

    }

    @Override
    public Object clone()
    {
        return new ANeqCmpOp(
            cloneNode(this._neq_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANeqCmpOp(this);
    }

    public TNeq getNeq()
    {
        return this._neq_;
    }

    public void setNeq(TNeq node)
    {
        if(this._neq_ != null)
        {
            this._neq_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._neq_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._neq_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._neq_ == child)
        {
            this._neq_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._neq_ == oldChild)
        {
            setNeq((TNeq) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
