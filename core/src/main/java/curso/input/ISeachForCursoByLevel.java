package curso.input;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.util.Collection;

public interface ISeachForCursoByLevel {
    Collection<Curso> getCursoByLevel(CursoLevels level);
}
