/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import java.util.*;
import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AArrayDeclArrayDeclarator extends PArrayDeclarator
{
    private TLBracket _lBracket_;
    private TIntConst _intConst_;
    private TRBracket _rBracket_;
    private final LinkedList<PArrayDeclaratorTail> _arrayDeclaratorTail_ = new LinkedList<PArrayDeclaratorTail>();

    public AArrayDeclArrayDeclarator()
    {
        // Constructor
    }

    public AArrayDeclArrayDeclarator(
        @SuppressWarnings("hiding") TLBracket _lBracket_,
        @SuppressWarnings("hiding") TIntConst _intConst_,
        @SuppressWarnings("hiding") TRBracket _rBracket_,
        @SuppressWarnings("hiding") List<?> _arrayDeclaratorTail_)
    {
        // Constructor
        setLBracket(_lBracket_);

        setIntConst(_intConst_);

        setRBracket(_rBracket_);

        setArrayDeclaratorTail(_arrayDeclaratorTail_);

    }

    @Override
    public Object clone()
    {
        return new AArrayDeclArrayDeclarator(
            cloneNode(this._lBracket_),
            cloneNode(this._intConst_),
            cloneNode(this._rBracket_),
            cloneList(this._arrayDeclaratorTail_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAArrayDeclArrayDeclarator(this);
    }

    public TLBracket getLBracket()
    {
        return this._lBracket_;
    }

    public void setLBracket(TLBracket node)
    {
        if(this._lBracket_ != null)
        {
            this._lBracket_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lBracket_ = node;
    }

    public TIntConst getIntConst()
    {
        return this._intConst_;
    }

    public void setIntConst(TIntConst node)
    {
        if(this._intConst_ != null)
        {
            this._intConst_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._intConst_ = node;
    }

    public TRBracket getRBracket()
    {
        return this._rBracket_;
    }

    public void setRBracket(TRBracket node)
    {
        if(this._rBracket_ != null)
        {
            this._rBracket_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rBracket_ = node;
    }

    public LinkedList<PArrayDeclaratorTail> getArrayDeclaratorTail()
    {
        return this._arrayDeclaratorTail_;
    }

    public void setArrayDeclaratorTail(List<?> list)
    {
        for(PArrayDeclaratorTail e : this._arrayDeclaratorTail_)
        {
            e.parent(null);
        }
        this._arrayDeclaratorTail_.clear();

        for(Object obj_e : list)
        {
            PArrayDeclaratorTail e = (PArrayDeclaratorTail) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._arrayDeclaratorTail_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._lBracket_)
            + toString(this._intConst_)
            + toString(this._rBracket_)
            + toString(this._arrayDeclaratorTail_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lBracket_ == child)
        {
            this._lBracket_ = null;
            return;
        }

        if(this._intConst_ == child)
        {
            this._intConst_ = null;
            return;
        }

        if(this._rBracket_ == child)
        {
            this._rBracket_ = null;
            return;
        }

        if(this._arrayDeclaratorTail_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lBracket_ == oldChild)
        {
            setLBracket((TLBracket) newChild);
            return;
        }

        if(this._intConst_ == oldChild)
        {
            setIntConst((TIntConst) newChild);
            return;
        }

        if(this._rBracket_ == oldChild)
        {
            setRBracket((TRBracket) newChild);
            return;
        }

        for(ListIterator<PArrayDeclaratorTail> i = this._arrayDeclaratorTail_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PArrayDeclaratorTail) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}
