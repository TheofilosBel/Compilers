/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AOrCond extends PCond
{
    private PCond _l_;
    private PCond _r_;

    public AOrCond()
    {
        // Constructor
    }

    public AOrCond(
        @SuppressWarnings("hiding") PCond _l_,
        @SuppressWarnings("hiding") PCond _r_)
    {
        // Constructor
        setL(_l_);

        setR(_r_);

    }

    @Override
    public Object clone()
    {
        return new AOrCond(
            cloneNode(this._l_),
            cloneNode(this._r_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOrCond(this);
    }

    public PCond getL()
    {
        return this._l_;
    }

    public void setL(PCond node)
    {
        if(this._l_ != null)
        {
            this._l_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._l_ = node;
    }

    public PCond getR()
    {
        return this._r_;
    }

    public void setR(PCond node)
    {
        if(this._r_ != null)
        {
            this._r_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._r_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._l_)
            + toString(this._r_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._l_ == child)
        {
            this._l_ = null;
            return;
        }

        if(this._r_ == child)
        {
            this._r_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._l_ == oldChild)
        {
            setL((PCond) newChild);
            return;
        }

        if(this._r_ == oldChild)
        {
            setR((PCond) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
