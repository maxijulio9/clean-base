package curso.input;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.time.LocalDate;
import java.util.List;

public interface ISearchCursoInput {

    void searchCurso(String nameCurso);
    //Curso searchSingleCourse(String nameCurso);

//    List<Curso> searchForCoursesThatMatchAString(String criteria);

  //  List<Curso> searchCoursesByLevel(CursoLevels level);

    //List<Curso> searchCoursesByExpirationDateInscription(LocalDate expirationDate);

  //  List<Curso> searchCoursesBetweenExpirationDateInscription(LocalDate expirationDate);

}
