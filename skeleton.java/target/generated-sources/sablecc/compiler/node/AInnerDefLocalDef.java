/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AInnerDefLocalDef extends PLocalDef
{
    private PFuncDef _funcDef_;

    public AInnerDefLocalDef()
    {
        // Constructor
    }

    public AInnerDefLocalDef(
        @SuppressWarnings("hiding") PFuncDef _funcDef_)
    {
        // Constructor
        setFuncDef(_funcDef_);

    }

    @Override
    public Object clone()
    {
        return new AInnerDefLocalDef(
            cloneNode(this._funcDef_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInnerDefLocalDef(this);
    }

    public PFuncDef getFuncDef()
    {
        return this._funcDef_;
    }

    public void setFuncDef(PFuncDef node)
    {
        if(this._funcDef_ != null)
        {
            this._funcDef_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._funcDef_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._funcDef_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._funcDef_ == child)
        {
            this._funcDef_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._funcDef_ == oldChild)
        {
            setFuncDef((PFuncDef) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
