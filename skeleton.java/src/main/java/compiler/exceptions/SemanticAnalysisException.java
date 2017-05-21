package compiler.exceptions;

@SuppressWarnings("serial")
public class SemanticAnalysisException extends CompilerException {
    
    public SemanticAnalysisException(int line, int column, String msg) {
        super(line, column, msg);
    }
}
