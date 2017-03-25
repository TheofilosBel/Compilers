package compiler;

import compiler.parser.*;
import compiler.lexer.*;
import compiler.node.*;
import java.io.*;

public class Compiler {
	
	public static void main(String[] arguments) {
		try {
			/* Create a Parser instance */
			Parser p = new Parser(new Lexer(new PushbackReader(new InputStreamReader(System.in), 1024)));

			/* Parse the input */
			Start tree = p.parse();
			
			/* Apply the translation */
			tree.apply(new Translation());
		}
		catch (ParserException p) {
			System.out.println("Parser error:");
			System.out.println(p.getMessage());
		}
		catch (LexerException l) {
			System.out.println("LEXER error:");
			System.out.println(l.getMessage());
		}
		catch (Exception e) {
			System.out.println("General exception:");
			System.out.println(e.getMessage());
		}
	}
}

