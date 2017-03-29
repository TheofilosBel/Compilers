/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AMultiSetIdList extends PIdList
{
    private TId _id_;
    private TComma _comma_;
    private PIdList _idList_;

    public AMultiSetIdList()
    {
        // Constructor
    }

    public AMultiSetIdList(
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PIdList _idList_)
    {
        // Constructor
        setId(_id_);

        setComma(_comma_);

        setIdList(_idList_);

    }

    @Override
    public Object clone()
    {
        return new AMultiSetIdList(
            cloneNode(this._id_),
            cloneNode(this._comma_),
            cloneNode(this._idList_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultiSetIdList(this);
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

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._id_)
            + toString(this._comma_)
            + toString(this._idList_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._idList_ == child)
        {
            this._idList_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._idList_ == oldChild)
        {
            setIdList((PIdList) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
