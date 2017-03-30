package compiler;

import compiler.analysis.*;
import compiler.node.*;

public class Translation extends DepthFirstAdapter{
	/*
	public void outADefVariableDefinition(ADefVariableDefinition node) { 
		System.out.print(node);
	}
    
	public void caseTId(TId node)
	{
		System.out.println(node);
	}*/
	
	@Override
	public void outAVar(AVar node)
    {
	    /*Print the ids */
	    System.out.print("VARS list=");
        System.out.print(node.getIdList());
        System.out.print(" type = ");
        /* Print the type */
        System.out.println(node.getType().toString());
    }
	
	@Override
	public void outAVarList(AVarList node)
    {
        System.out.print("Var def's found : ");
        System.out.println(node.getVList().size());
    }
	
}
