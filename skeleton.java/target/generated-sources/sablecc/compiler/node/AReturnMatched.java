/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AReturnMatched extends PMatched
{
    private TKwReturn _kwReturn_;
    private PExpr _expr_;
    private TSemicolon _semicolon_;

    public AReturnMatched()
    {
        // Constructor
    }

    public AReturnMatched(
        @SuppressWarnings("hiding") TKwReturn _kwReturn_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setKwReturn(_kwReturn_);

        setExpr(_expr_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new AReturnMatched(
            cloneNode(this._kwReturn_),
            cloneNode(this._expr_),
            cloneNode(this._semicolon_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAReturnMatched(this);
    }

    public TKwReturn getKwReturn()
    {
        return this._kwReturn_;
    }

    public void setKwReturn(TKwReturn node)
    {
        if(this._kwReturn_ != null)
        {
            this._kwReturn_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwReturn_ = node;
    }

    public PExpr getExpr()
    {
        return this._expr_;
    }

    public void setExpr(PExpr node)
    {
        if(this._expr_ != null)
        {
            this._expr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr_ = node;
    }

    public TSemicolon getSemicolon()
    {
        return this._semicolon_;
    }

    public void setSemicolon(TSemicolon node)
    {
        if(this._semicolon_ != null)
        {
            this._semicolon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._semicolon_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._kwReturn_)
            + toString(this._expr_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._kwReturn_ == child)
        {
            this._kwReturn_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._semicolon_ == child)
        {
            this._semicolon_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._kwReturn_ == oldChild)
        {
            setKwReturn((TKwReturn) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
