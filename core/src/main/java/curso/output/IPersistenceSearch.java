package curso.output;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.time.LocalDate;
import java.util.Collection;

public interface IPersistenceSearch {
    boolean existsCurso(String nameCurso);
    Curso getSingleCurso(String nameCurso);
    Collection<Curso> getAllCursos();
    Collection<Curso> getCursoThatMatchString(String nameCurso);
    Collection<Curso> getCursoByLevel(CursoLevels level);
    Collection<Curso> getCursoByNameAndByLevel(String nameCurso, CursoLevels level);
    Collection<Curso> getCursoBetweenTwoExpirationDate(LocalDate startDate, LocalDate endDate );
}
