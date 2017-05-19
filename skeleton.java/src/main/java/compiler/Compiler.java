package compiler;

import compiler.parser.*;
import compiler.semanticAnalysis.*;
import compiler.exceptions.*;
import compiler.lexer.*;
import compiler.node.*;
import java.io.*;

public class Compiler {
    
    public static void main(String[] arguments) {
        
        BufferedReader input = null;
        try {
            /* Open the input test file */
            input = new BufferedReader(new FileReader(arguments[0]));
            

            /* Create a Parser instance */
            //Parser p = new Parser(new Lexer(new PushbackReader(new InputStreamReader(System.in), 1024)));
            Parser p = new Parser(new Lexer(new PushbackReader(input, 1024)));

            /* Parse the input */
            Start tree = p.parse();
            
            /* Apply the translation */
            //tree.apply(new Translation());
            tree.apply(new SemanticAnalysis());

        }
        catch (ParserException pa) {
            System.out.println("Parser error:");
            System.out.println(pa.getMessage());
        }
        catch (LexerException le) {
            System.out.println("LEXER error:");
            System.out.println(le.getMessage());
        }
        catch (TypeCheckingException te) {
            System.out.println("TypeChecking error:");
            System.out.println(te.getMessage());
        }
        catch (SemanticAnalysisException se) {
            System.out.println("Semantics error:");
            System.out.println(se.getMessage());
        }

        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
        catch (Exception e) {
            System.out.println("General exception:");
            System.out.println(e.getMessage());
        }        
        finally {
            
            /* Close the stream in any case */
            try {
                input.close();
            }
            catch (IOException ioe){
                System.out.println(ioe.getMessage());
            }
        }
    }
}