/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class ANotCond extends PCond
{
    private PCond _cond_;

    public ANotCond()
    {
        // Constructor
    }

    public ANotCond(
        @SuppressWarnings("hiding") PCond _cond_)
    {
        // Constructor
        setCond(_cond_);

    }

    @Override
    public Object clone()
    {
        return new ANotCond(
            cloneNode(this._cond_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotCond(this);
    }

    public PCond getCond()
    {
        return this._cond_;
    }

    public void setCond(PCond node)
    {
        if(this._cond_ != null)
        {
            this._cond_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cond_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._cond_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._cond_ == child)
        {
            this._cond_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._cond_ == oldChild)
        {
            setCond((PCond) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
