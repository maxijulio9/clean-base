package curso.output;

import curso.exception.ExceptionCursoErrorSavingInDB;
import curso.modelo.Curso;

public interface IPersistence {
    boolean existsCurso(String name);
    boolean saveCurso(Curso curso) throws ExceptionCursoErrorSavingInDB;
}
