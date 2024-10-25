package curso.output;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface IPersistenceSearch {
    boolean existsCurso(String nameCurso);
    Curso getSingleCurso(String nameCurso);
    List<Curso> getAllCursos();
    List<Curso> getCursoThatMatchString(String nameCurso);
    List<Curso> getCursoByLevel(CursoLevels level);
    List<Curso> getCursoByNameAndByLevel(String nameCurso, CursoLevels level);
    List<Curso> getCursoBetweenTwoExpirationDate(LocalDate startDate, LocalDate endDate );
}
