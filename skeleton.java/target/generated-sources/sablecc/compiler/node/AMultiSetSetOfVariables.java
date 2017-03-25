/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AMultiSetSetOfVariables extends PSetOfVariables
{
    private TVariable _variable_;
    private TComma _comma_;
    private PSetOfVariables _setOfVariables_;

    public AMultiSetSetOfVariables()
    {
        // Constructor
    }

    public AMultiSetSetOfVariables(
        @SuppressWarnings("hiding") TVariable _variable_,
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PSetOfVariables _setOfVariables_)
    {
        // Constructor
        setVariable(_variable_);

        setComma(_comma_);

        setSetOfVariables(_setOfVariables_);

    }

    @Override
    public Object clone()
    {
        return new AMultiSetSetOfVariables(
            cloneNode(this._variable_),
            cloneNode(this._comma_),
            cloneNode(this._setOfVariables_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultiSetSetOfVariables(this);
    }

    public TVariable getVariable()
    {
        return this._variable_;
    }

    public void setVariable(TVariable node)
    {
        if(this._variable_ != null)
        {
            this._variable_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._variable_ = node;
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

    public PSetOfVariables getSetOfVariables()
    {
        return this._setOfVariables_;
    }

    public void setSetOfVariables(PSetOfVariables node)
    {
        if(this._setOfVariables_ != null)
        {
            this._setOfVariables_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._setOfVariables_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._variable_)
            + toString(this._comma_)
            + toString(this._setOfVariables_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._variable_ == child)
        {
            this._variable_ = null;
            return;
        }

        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._setOfVariables_ == child)
        {
            this._setOfVariables_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._variable_ == oldChild)
        {
            setVariable((TVariable) newChild);
            return;
        }

        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._setOfVariables_ == oldChild)
        {
            setSetOfVariables((PSetOfVariables) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
