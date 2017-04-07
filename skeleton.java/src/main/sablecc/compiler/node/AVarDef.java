/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AVarDef extends PVarDef
{
    private TKwVar _kwVar_;
    private PIdList _idList_;
    private TColon _colon_;
    private PType _type_;
    private TSemicolon _semicolon_;

    public AVarDef()
    {
        // Constructor
    }

    public AVarDef(
        @SuppressWarnings("hiding") TKwVar _kwVar_,
        @SuppressWarnings("hiding") PIdList _idList_,
        @SuppressWarnings("hiding") TColon _colon_,
        @SuppressWarnings("hiding") PType _type_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setKwVar(_kwVar_);

        setIdList(_idList_);

        setColon(_colon_);

        setType(_type_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new AVarDef(
            cloneNode(this._kwVar_),
            cloneNode(this._idList_),
            cloneNode(this._colon_),
            cloneNode(this._type_),
            cloneNode(this._semicolon_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarDef(this);
    }

    public TKwVar getKwVar()
    {
        return this._kwVar_;
    }

    public void setKwVar(TKwVar node)
    {
        if(this._kwVar_ != null)
        {
            this._kwVar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwVar_ = node;
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

    public PType getType()
    {
        return this._type_;
    }

    public void setType(PType node)
    {
        if(this._type_ != null)
        {
            this._type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._type_ = node;
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
            + toString(this._kwVar_)
            + toString(this._idList_)
            + toString(this._colon_)
            + toString(this._type_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._kwVar_ == child)
        {
            this._kwVar_ = null;
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

        if(this._type_ == child)
        {
            this._type_ = null;
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
        if(this._kwVar_ == oldChild)
        {
            setKwVar((TKwVar) newChild);
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

        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
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
