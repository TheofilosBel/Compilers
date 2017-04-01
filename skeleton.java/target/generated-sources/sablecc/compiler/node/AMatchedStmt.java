/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AMatchedStmt extends PStmt
{
    private PMatched _matched_;

    public AMatchedStmt()
    {
        // Constructor
    }

    public AMatchedStmt(
        @SuppressWarnings("hiding") PMatched _matched_)
    {
        // Constructor
        setMatched(_matched_);

    }

    @Override
    public Object clone()
    {
        return new AMatchedStmt(
            cloneNode(this._matched_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMatchedStmt(this);
    }

    public PMatched getMatched()
    {
        return this._matched_;
    }

    public void setMatched(PMatched node)
    {
        if(this._matched_ != null)
        {
            this._matched_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._matched_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._matched_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._matched_ == child)
        {
            this._matched_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._matched_ == oldChild)
        {
            setMatched((PMatched) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
