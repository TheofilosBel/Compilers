/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AFparDef extends PFparDef
{
    private TKwRef _kwRef_;
    private PIdList _idList_;
    private TColon _colon_;
    private PFparType _fparType_;

    public AFparDef()
    {
        // Constructor
    }

    public AFparDef(
        @SuppressWarnings("hiding") TKwRef _kwRef_,
        @SuppressWarnings("hiding") PIdList _idList_,
        @SuppressWarnings("hiding") TColon _colon_,
        @SuppressWarnings("hiding") PFparType _fparType_)
    {
        // Constructor
        setKwRef(_kwRef_);

        setIdList(_idList_);

        setColon(_colon_);

        setFparType(_fparType_);

    }

    @Override
    public Object clone()
    {
        return new AFparDef(
            cloneNode(this._kwRef_),
            cloneNode(this._idList_),
            cloneNode(this._colon_),
            cloneNode(this._fparType_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFparDef(this);
    }

    public TKwRef getKwRef()
    {
        return this._kwRef_;
    }

    public void setKwRef(TKwRef node)
    {
        if(this._kwRef_ != null)
        {
            this._kwRef_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwRef_ = node;
    }

    public PIdList getIdList()
    {
        return this._idList_;
    }

    public void setIdList(PIdList node)
    {
        if(this._idList_ != null)
        {
            this._idList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._idList_ = node;
    }

    public TColon getColon()
    {
        return this._colon_;
    }

    public void setColon(TColon node)
    {
        if(this._colon_ != null)
        {
            this._colon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._colon_ = node;
    }

    public PFparType getFparType()
    {
        return this._fparType_;
    }

    public void setFparType(PFparType node)
    {
        if(this._fparType_ != null)
        {
            this._fparType_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fparType_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._kwRef_)
            + toString(this._idList_)
            + toString(this._colon_)
            + toString(this._fparType_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._kwRef_ == child)
        {
            this._kwRef_ = null;
            return;
        }

        if(this._idList_ == child)
        {
            this._idList_ = null;
            return;
        }

        if(this._colon_ == child)
        {
            this._colon_ = null;
            return;
        }

        if(this._fparType_ == child)
        {
            this._fparType_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._kwRef_ == oldChild)
        {
            setKwRef((TKwRef) newChild);
            return;
        }

        if(this._idList_ == oldChild)
        {
            setIdList((PIdList) newChild);
            return;
        }

        if(this._colon_ == oldChild)
        {
            setColon((TColon) newChild);
            return;
        }

        if(this._fparType_ == oldChild)
        {
            setFparType((PFparType) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
