package compiler;

import compiler.analysis.*;
import compiler.node.*;

public class Translation extends DepthFirstAdapter{

	
	public void outADefVariableDefinition(ADefVariableDefinition node)
	{  // out of alternative {two} then print it 
		System.out.print(node);
	}
	
}
