package curso.output;

import curso.modelo.Curso;

public interface IPersistenceCreation {
    boolean existsCurso(String nameCurso);
    boolean saveCurso(Curso cursoCurso);
}
