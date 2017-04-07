/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class ANotCond2 extends PCond2
{
    private TKwNot _kwNot_;
    private PCond3 _cond3_;

    public ANotCond2()
    {
        // Constructor
    }

    public ANotCond2(
        @SuppressWarnings("hiding") TKwNot _kwNot_,
        @SuppressWarnings("hiding") PCond3 _cond3_)
    {
        // Constructor
        setKwNot(_kwNot_);

        setCond3(_cond3_);

    }

    @Override
    public Object clone()
    {
        return new ANotCond2(
            cloneNode(this._kwNot_),
            cloneNode(this._cond3_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotCond2(this);
    }

    public TKwNot getKwNot()
    {
        return this._kwNot_;
    }

    public void setKwNot(TKwNot node)
    {
        if(this._kwNot_ != null)
        {
            this._kwNot_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwNot_ = node;
    }

    public PCond3 getCond3()
    {
        return this._cond3_;
    }

    public void setCond3(PCond3 node)
    {
        if(this._cond3_ != null)
        {
            this._cond3_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cond3_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._kwNot_)
            + toString(this._cond3_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._kwNot_ == child)
        {
            this._kwNot_ = null;
            return;
        }

        if(this._cond3_ == child)
        {
            this._cond3_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._kwNot_ == oldChild)
        {
            setKwNot((TKwNot) newChild);
            return;
        }

        if(this._cond3_ == oldChild)
        {
            setCond3((PCond3) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
