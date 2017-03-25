/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class ADefVariableDefinition extends PVariableDefinition
{
    private TVar _var_;
    private PSetOfVariables _setOfVariables_;
    private TDeftype _deftype_;
    private PType _type_;
    private TCmdEnd _cmdEnd_;

    public ADefVariableDefinition()
    {
        // Constructor
    }

    public ADefVariableDefinition(
        @SuppressWarnings("hiding") TVar _var_,
        @SuppressWarnings("hiding") PSetOfVariables _setOfVariables_,
        @SuppressWarnings("hiding") TDeftype _deftype_,
        @SuppressWarnings("hiding") PType _type_,
        @SuppressWarnings("hiding") TCmdEnd _cmdEnd_)
    {
        // Constructor
        setVar(_var_);

        setSetOfVariables(_setOfVariables_);

        setDeftype(_deftype_);

        setType(_type_);

        setCmdEnd(_cmdEnd_);

    }

    @Override
    public Object clone()
    {
        return new ADefVariableDefinition(
            cloneNode(this._var_),
            cloneNode(this._setOfVariables_),
            cloneNode(this._deftype_),
            cloneNode(this._type_),
            cloneNode(this._cmdEnd_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADefVariableDefinition(this);
    }

    public TVar getVar()
    {
        return this._var_;
    }

    public void setVar(TVar node)
    {
        if(this._var_ != null)
        {
            this._var_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._var_ = node;
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

    public TDeftype getDeftype()
    {
        return this._deftype_;
    }

    public void setDeftype(TDeftype node)
    {
        if(this._deftype_ != null)
        {
            this._deftype_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._deftype_ = node;
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

    public TCmdEnd getCmdEnd()
    {
        return this._cmdEnd_;
    }

    public void setCmdEnd(TCmdEnd node)
    {
        if(this._cmdEnd_ != null)
        {
            this._cmdEnd_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cmdEnd_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._var_)
            + toString(this._setOfVariables_)
            + toString(this._deftype_)
            + toString(this._type_)
            + toString(this._cmdEnd_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._var_ == child)
        {
            this._var_ = null;
            return;
        }

        if(this._setOfVariables_ == child)
        {
            this._setOfVariables_ = null;
            return;
        }

        if(this._deftype_ == child)
        {
            this._deftype_ = null;
            return;
        }

        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        if(this._cmdEnd_ == child)
        {
            this._cmdEnd_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._var_ == oldChild)
        {
            setVar((TVar) newChild);
            return;
        }

        if(this._setOfVariables_ == oldChild)
        {
            setSetOfVariables((PSetOfVariables) newChild);
            return;
        }

        if(this._deftype_ == oldChild)
        {
            setDeftype((TDeftype) newChild);
            return;
        }

        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

        if(this._cmdEnd_ == oldChild)
        {
            setCmdEnd((TCmdEnd) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
