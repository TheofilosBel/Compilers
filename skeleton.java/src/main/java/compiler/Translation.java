package compiler;

import compiler.analysis.*;
import compiler.node.*;

public class Translation extends DepthFirstAdapter{

	public void caseTElse(TElse node)
	{// When we see a number, we print it.
		System.out.print(node);
	}	
}
