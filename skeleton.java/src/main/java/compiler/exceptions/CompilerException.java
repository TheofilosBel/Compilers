package compiler.exceptions;

public abstract class CompilerException extends RuntimeException {

    public CompilerException(){}
    
    @Override
    public abstract String getMessage();
}
