package compiler;

import compiler.analysis.*;
import compiler.node.*;

public class Translation extends DepthFirstAdapter{
	/*
	public void outADefVariableDefinition(ADefVariableDefinition node) { 
		System.out.print(node);
	}
    */
    public void outAOneProgram(AOneProgram node) { 
        System.out.print(node);
    }
	
}
