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
    public void outAVarDefVarDef(AVarDefVarDef node) 
    { 
		System.out.println(node);
    }
    
}
