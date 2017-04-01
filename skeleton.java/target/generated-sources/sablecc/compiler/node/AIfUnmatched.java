/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.node;

import compiler.analysis.*;

@SuppressWarnings("nls")
public final class AIfUnmatched extends PUnmatched
{
    private PUnmatchedIf _unmatchedIf_;

    public AIfUnmatched()
    {
        // Constructor
    }

    public AIfUnmatched(
        @SuppressWarnings("hiding") PUnmatchedIf _unmatchedIf_)
    {
        // Constructor
        setUnmatchedIf(_unmatchedIf_);

    }

    @Override
    public Object clone()
    {
        return new AIfUnmatched(
            cloneNode(this._unmatchedIf_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIfUnmatched(this);
    }

    public PUnmatchedIf getUnmatchedIf()
    {
        return this._unmatchedIf_;
    }

    public void setUnmatchedIf(PUnmatchedIf node)
    {
        if(this._unmatchedIf_ != null)
        {
            this._unmatchedIf_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._unmatchedIf_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._unmatchedIf_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._unmatchedIf_ == child)
        {
            this._unmatchedIf_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._unmatchedIf_ == oldChild)
        {
            setUnmatchedIf((PUnmatchedIf) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
