package curso.input;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.util.Collection;

public interface ISearchCursoByNameAndByLevel {
    Collection<Curso> searchCursoByNameAndByLevel(String nameCurso, CursoLevels level);
}

