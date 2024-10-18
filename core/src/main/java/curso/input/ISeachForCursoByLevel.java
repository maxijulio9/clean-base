package curso.input;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.util.Collection;
import java.util.List;

public interface ISeachForCursoByLevel {
    List<Curso> getCursoByLevel(CursoLevels level);
}
