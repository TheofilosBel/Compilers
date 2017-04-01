/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AModTerm extends PTerm
{
    private PTerm _term_;
    private TKwMod _kwMod_;
    private PSignedFactor _signedFactor_;

    public AModTerm()
    {
        // Constructor
    }

    public AModTerm(
        @SuppressWarnings("hiding") PTerm _term_,
        @SuppressWarnings("hiding") TKwMod _kwMod_,
        @SuppressWarnings("hiding") PSignedFactor _signedFactor_)
    {
        // Constructor
        setTerm(_term_);

        setKwMod(_kwMod_);

        setSignedFactor(_signedFactor_);

    }

    @Override
    public Object clone()
    {
        return new AModTerm(
            cloneNode(this._term_),
            cloneNode(this._kwMod_),
            cloneNode(this._signedFactor_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAModTerm(this);
    }

    public PTerm getTerm()
    {
        return this._term_;
    }

    public void setTerm(PTerm node)
    {
        if(this._term_ != null)
        {
            this._term_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._term_ = node;
    }

    public TKwMod getKwMod()
    {
        return this._kwMod_;
    }

    public void setKwMod(TKwMod node)
    {
        if(this._kwMod_ != null)
        {
            this._kwMod_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwMod_ = node;
    }

    public PSignedFactor getSignedFactor()
    {
        return this._signedFactor_;
    }

    public void setSignedFactor(PSignedFactor node)
    {
        if(this._signedFactor_ != null)
        {
            this._signedFactor_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._signedFactor_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._term_)
            + toString(this._kwMod_)
            + toString(this._signedFactor_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._term_ == child)
        {
            this._term_ = null;
            return;
        }

        if(this._kwMod_ == child)
        {
            this._kwMod_ = null;
            return;
        }

        if(this._signedFactor_ == child)
        {
            this._signedFactor_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._term_ == oldChild)
        {
            setTerm((PTerm) newChild);
            return;
        }

        if(this._kwMod_ == oldChild)
        {
            setKwMod((TKwMod) newChild);
            return;
        }

        if(this._signedFactor_ == oldChild)
        {
            setSignedFactor((PSignedFactor) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
