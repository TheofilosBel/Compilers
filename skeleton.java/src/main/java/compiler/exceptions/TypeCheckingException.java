package compiler.exceptions;

@SuppressWarnings("serial")
public class TypeCheckingException extends SemanticAnalysisException {

    public TypeCheckingException(String msg) {
        super(msg);
    }
}
