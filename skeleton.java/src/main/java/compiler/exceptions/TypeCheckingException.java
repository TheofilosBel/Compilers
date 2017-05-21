package compiler.exceptions;

@SuppressWarnings("serial")
public class TypeCheckingException extends SemanticAnalysisException {

    public TypeCheckingException(int line, int column, String msg) {
        super(line, column, msg);
    }
}
