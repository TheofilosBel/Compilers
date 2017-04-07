/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AMultiFparList extends PFparList
{
    private PFparDef _fparDef_;
    private TSemicolon _semicolon_;
    private PFparList _fparList_;

    public AMultiFparList()
    {
        // Constructor
    }

    public AMultiFparList(
        @SuppressWarnings("hiding") PFparDef _fparDef_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_,
        @SuppressWarnings("hiding") PFparList _fparList_)
    {
        // Constructor
        setFparDef(_fparDef_);

        setSemicolon(_semicolon_);

        setFparList(_fparList_);

    }

    @Override
    public Object clone()
    {
        return new AMultiFparList(
            cloneNode(this._fparDef_),
            cloneNode(this._semicolon_),
            cloneNode(this._fparList_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultiFparList(this);
    }

    public PFparDef getFparDef()
    {
        return this._fparDef_;
    }

    public void setFparDef(PFparDef node)
    {
        if(this._fparDef_ != null)
        {
            this._fparDef_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fparDef_ = node;
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

    public PFparList getFparList()
    {
        return this._fparList_;
    }

    public void setFparList(PFparList node)
    {
        if(this._fparList_ != null)
        {
            this._fparList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fparList_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._fparDef_)
            + toString(this._semicolon_)
            + toString(this._fparList_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._fparDef_ == child)
        {
            this._fparDef_ = null;
            return;
        }

        if(this._semicolon_ == child)
        {
            this._semicolon_ = null;
            return;
        }

        if(this._fparList_ == child)
        {
            this._fparList_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._fparDef_ == oldChild)
        {
            setFparDef((PFparDef) newChild);
            return;
        }

        if(this._semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

        if(this._fparList_ == oldChild)
        {
            setFparList((PFparList) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
