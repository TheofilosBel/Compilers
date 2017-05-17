/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import java.util.*;
import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AVarDef extends PVarDef
{
    private final LinkedList<PVariable> _varList_ = new LinkedList<PVariable>();
    private PType _type_;

    public AVarDef()
    {
        // Constructor
    }

    public AVarDef(
        @SuppressWarnings("hiding") List<?> _varList_,
        @SuppressWarnings("hiding") PType _type_)
    {
        // Constructor
        setVarList(_varList_);

        setType(_type_);

    }

    @Override
    public Object clone()
    {
        return new AVarDef(
            cloneList(this._varList_),
            cloneNode(this._type_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarDef(this);
    }

    public LinkedList<PVariable> getVarList()
    {
        return this._varList_;
    }

    public void setVarList(List<?> list)
    {
        for(PVariable e : this._varList_)
        {
            e.parent(null);
        }
        this._varList_.clear();

        for(Object obj_e : list)
        {
            PVariable e = (PVariable) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._varList_.add(e);
        }
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._varList_)
            + toString(this._type_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._varList_.remove(child))
        {
            return;
        }

        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PVariable> i = this._varList_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PVariable) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
