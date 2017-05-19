package compiler.exceptions;

@SuppressWarnings("serial")
public class SemanticAnalysisException extends CompilerException {

    private String msg;
    
    public SemanticAnalysisException(String msg) {
        this.msg = new String(msg);
    }
        
    @Override
    public String getMessage() {
        return this.msg;
    }
}
