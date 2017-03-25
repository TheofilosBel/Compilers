package compiler;

import compiler.analysis.*;
import compiler.node.*;

public class Translation extends DepthFirstAdapter{
	
	public void outADefVariableDefinition(ADefVariableDefinition node) { 
		System.out.print(node);
	}
	
}
