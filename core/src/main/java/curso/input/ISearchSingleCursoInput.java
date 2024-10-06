package curso.input;

import curso.modelo.Curso;

import java.util.List;

public interface ISearchSingleCursoInput {

    Curso getSingleCurso(String nameCurso);
    //Se separó por interfaces
    //Curso searchSingleCourse(String nameCurso);

//    List<Curso> searchForCoursesThatMatchAString(String criteria);

  //  List<Curso> searchCoursesByLevel(CursoLevels level);

    //List<Curso> searchCoursesByExpirationDateInscription(LocalDate expirationDate);

  //  List<Curso> searchCoursesBetweenExpirationDateInscription(LocalDate expirationDate);

}
