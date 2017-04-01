/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AHeaderHeader extends PHeader
{
    private TKwFun _kwFun_;
    private TId _id_;
    private TLPar _lPar_;
    private PFparList _fparList_;
    private TRPar _rPar_;
    private TColon _colon_;
    private PRetType _retType_;

    public AHeaderHeader()
    {
        // Constructor
    }

    public AHeaderHeader(
        @SuppressWarnings("hiding") TKwFun _kwFun_,
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") TLPar _lPar_,
        @SuppressWarnings("hiding") PFparList _fparList_,
        @SuppressWarnings("hiding") TRPar _rPar_,
        @SuppressWarnings("hiding") TColon _colon_,
        @SuppressWarnings("hiding") PRetType _retType_)
    {
        // Constructor
        setKwFun(_kwFun_);

        setId(_id_);

        setLPar(_lPar_);

        setFparList(_fparList_);

        setRPar(_rPar_);

        setColon(_colon_);

        setRetType(_retType_);

    }

    @Override
    public Object clone()
    {
        return new AHeaderHeader(
            cloneNode(this._kwFun_),
            cloneNode(this._id_),
            cloneNode(this._lPar_),
            cloneNode(this._fparList_),
            cloneNode(this._rPar_),
            cloneNode(this._colon_),
            cloneNode(this._retType_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAHeaderHeader(this);
    }

    public TKwFun getKwFun()
    {
        return this._kwFun_;
    }

    public void setKwFun(TKwFun node)
    {
        if(this._kwFun_ != null)
        {
            this._kwFun_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwFun_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public TLPar getLPar()
    {
        return this._lPar_;
    }

    public void setLPar(TLPar node)
    {
        if(this._lPar_ != null)
        {
            this._lPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lPar_ = node;
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

    public TRPar getRPar()
    {
        return this._rPar_;
    }

    public void setRPar(TRPar node)
    {
        if(this._rPar_ != null)
        {
            this._rPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rPar_ = node;
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

    public PRetType getRetType()
    {
        return this._retType_;
    }

    public void setRetType(PRetType node)
    {
        if(this._retType_ != null)
        {
            this._retType_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._retType_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._kwFun_)
            + toString(this._id_)
            + toString(this._lPar_)
            + toString(this._fparList_)
            + toString(this._rPar_)
            + toString(this._colon_)
            + toString(this._retType_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._kwFun_ == child)
        {
            this._kwFun_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._lPar_ == child)
        {
            this._lPar_ = null;
            return;
        }

        if(this._fparList_ == child)
        {
            this._fparList_ = null;
            return;
        }

        if(this._rPar_ == child)
        {
            this._rPar_ = null;
            return;
        }

        if(this._colon_ == child)
        {
            this._colon_ = null;
            return;
        }

        if(this._retType_ == child)
        {
            this._retType_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._kwFun_ == oldChild)
        {
            setKwFun((TKwFun) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._lPar_ == oldChild)
        {
            setLPar((TLPar) newChild);
            return;
        }

        if(this._fparList_ == oldChild)
        {
            setFparList((PFparList) newChild);
            return;
        }

        if(this._rPar_ == oldChild)
        {
            setRPar((TRPar) newChild);
            return;
        }

        if(this._colon_ == oldChild)
        {
            setColon((TColon) newChild);
            return;
        }

        if(this._retType_ == oldChild)
        {
            setRetType((PRetType) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
