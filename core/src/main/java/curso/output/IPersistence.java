package curso.output;

import curso.modelo.Curso;

public interface IPersistence {
    boolean existsCurso(String name);
    void saveCurso(Curso curso);
    void searchCurso(String name);
}
