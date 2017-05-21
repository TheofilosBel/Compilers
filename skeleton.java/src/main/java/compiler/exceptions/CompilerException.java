package compiler.exceptions;

public abstract class CompilerException extends RuntimeException {

    int    line;
    int    column;
    String msg;
    
    public CompilerException(int line, int column, String msg) {
        this.line   = line;
        this.column = column;
        this.msg    = msg;
    }
    
    @Override
    public String getMessage() {
        return "Error in line " + this.line + " column " + this.column +
                ":\n" + this.msg;
    }
}
