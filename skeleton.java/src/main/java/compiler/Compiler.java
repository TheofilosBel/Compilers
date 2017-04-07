package compiler;

import compiler.parser.*;
import compiler.semanticAnalysis.*;
import compiler.lexer.*;
import compiler.node.*;
import java.io.*;

public class Compiler {
    
    public static void main(String[] arguments) {
        try {
            /* Open the input test file */
            BufferedReader input = new BufferedReader(new FileReader(arguments[0]));

            /* Create a Parser instance */
            //Parser p = new Parser(new Lexer(new PushbackReader(new InputStreamReader(System.in), 1024)));
            Parser p = new Parser(new Lexer(new PushbackReader(input, 1024)));

            /* Parse the input */
            Start tree = p.parse();
            
            /* Apply the translation */
            tree.apply(new Translation());
            tree.apply(new SemanticAnalysis());

            input.close();
        }
        catch (ParserException pa) {
            System.out.println("Parser error:");
            System.out.println(pa.getMessage());
        }
        catch (LexerException le) {
            System.out.println("LEXER error:");
            System.out.println(le.getMessage());
        }
        catch (Exception e) {
            System.out.println("General exception:");
            System.out.println(e.getMessage());
        }
    }
}