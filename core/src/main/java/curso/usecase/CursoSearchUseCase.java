package curso.usecase;

import curso.exception.ExceptionCursonNonExistence;
import curso.input.ISearchCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistence;

import java.time.LocalDate;
import java.util.List;

public class CursoSearchUseCase implements ISearchCursoInput {

    private IPersistence myDB;
    public CursoSearchUseCase(IPersistence persistence){
        this.myDB = persistence;
    }

    @Override
    public void searchCurso(String nameCurso) throws ExceptionCursonNonExistence {

        if (!myDB.existsCurso(nameCurso)) throw new ExceptionCursonNonExistence("No se encontraron resultados para '"+nameCurso+"'");
        myDB.searchCurso(nameCurso);
    }

  /*
  //Wstos metodos son de capa presentación mepa
    @Override
    public Curso searchSingleCourse(String nameCurso) {

        return null;
    }

    @Override
    public List<Curso> searchForCoursesThatMatchAString(String criteria) {
        return List.of();
    }

    @Override
    public List<Curso> searchCoursesByLevel(CursoLevels level) {
        return List.of();
    }

    @Override
    public List<Curso> searchCoursesByExpirationDateInscription(LocalDate expirationDate) {
        return List.of();
    }

 */
}
