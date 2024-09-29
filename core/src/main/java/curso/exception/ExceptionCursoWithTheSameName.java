package curso.exception;

public class ExceptionCursoWithTheSameName extends RuntimeException {
    public ExceptionCursoWithTheSameName(String message){
        super(message);
    }
}
