/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiler.analysis;

import java.util.*;
import compiler.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    public void caseAOneProgram(AOneProgram node)
    {
        defaultCase(node);
    }

    public void caseADefVariableDefinition(ADefVariableDefinition node)
    {
        defaultCase(node);
    }

    public void caseAMultiSetSetOfVariables(AMultiSetSetOfVariables node)
    {
        defaultCase(node);
    }

    public void caseASingleSetSetOfVariables(ASingleSetSetOfVariables node)
    {
        defaultCase(node);
    }

    public void caseASimpleType(ASimpleType node)
    {
        defaultCase(node);
    }

    public void caseAArrayType(AArrayType node)
    {
        defaultCase(node);
    }

    public void caseATIntDataType(ATIntDataType node)
    {
        defaultCase(node);
    }

    public void caseATCharDataType(ATCharDataType node)
    {
        defaultCase(node);
    }

    public void caseTWhiteSpace(TWhiteSpace node)
    {
        defaultCase(node);
    }

    public void caseTComment(TComment node)
    {
        defaultCase(node);
    }

    public void caseTColon(TColon node)
    {
        defaultCase(node);
    }

    public void caseTSemicolon(TSemicolon node)
    {
        defaultCase(node);
    }

    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    public void caseTKwAnd(TKwAnd node)
    {
        defaultCase(node);
    }

    public void caseTKwOr(TKwOr node)
    {
        defaultCase(node);
    }

    public void caseTKwInt(TKwInt node)
    {
        defaultCase(node);
    }

    public void caseTKwThen(TKwThen node)
    {
        defaultCase(node);
    }

    public void caseTKwChar(TKwChar node)
    {
        defaultCase(node);
    }

    public void caseTKwMod(TKwMod node)
    {
        defaultCase(node);
    }

    public void caseTKwVar(TKwVar node)
    {
        defaultCase(node);
    }

    public void caseTKwDiv(TKwDiv node)
    {
        defaultCase(node);
    }

    public void caseTKwNot(TKwNot node)
    {
        defaultCase(node);
    }

    public void caseTKwWhile(TKwWhile node)
    {
        defaultCase(node);
    }

    public void caseTKwDo(TKwDo node)
    {
        defaultCase(node);
    }

    public void caseTKwIf(TKwIf node)
    {
        defaultCase(node);
    }

    public void caseTKwElse(TKwElse node)
    {
        defaultCase(node);
    }

    public void caseTKwFun(TKwFun node)
    {
        defaultCase(node);
    }

    public void caseTKwReturn(TKwReturn node)
    {
        defaultCase(node);
    }

    public void caseTKwRef(TKwRef node)
    {
        defaultCase(node);
    }

    public void caseTKwNothing(TKwNothing node)
    {
        defaultCase(node);
    }

    public void caseTIdentifier(TIdentifier node)
    {
        defaultCase(node);
    }

    public void caseTInteger(TInteger node)
    {
        defaultCase(node);
    }

    public void caseTArrayDeclaration(TArrayDeclaration node)
    {
        defaultCase(node);
    }

    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
