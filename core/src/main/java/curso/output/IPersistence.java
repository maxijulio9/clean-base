package curso.output;

import curso.modelo.Curso;

public interface IPersistence {
    boolean existsCurso(String name);
    boolean saveCurso(Curso curso);
    void searchCurso(String name);
}
