package curso.usecase;

import curso.exception.ExceptionCursonNonExistence;
import curso.input.ISeachForCursoByLevel;
import curso.input.ISearchForCursoThatMatchString;
import curso.input.ISearchSingleCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistenceSearch;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class CursoSearchUseCase{// implements ISearchSingleCursoInput, ISearchForCursoThatMatchString, ISeachForCursoByLevel {

    private IPersistenceSearch myDB;
    public CursoSearchUseCase(IPersistenceSearch persistence){
        this.myDB = persistence;
    }

    public Curso getSingleCurso(String nameCurso) throws ExceptionCursonNonExistence {

        if (!myDB.existsCurso(nameCurso)) {
            System.out.println("El curso no existe");
            throw new ExceptionCursonNonExistence("No se encontraron resultados para '" + nameCurso + "'");

        }
        return myDB.getSingleCurso(nameCurso);
    }

    public Collection<Curso> getAllCursos() {
        return myDB.getAllCursos();
    }

    public Collection<Curso> getCursoThatMatchString(String nameCurso) {
        if (!myDB.existsCurso(nameCurso)) throw new ExceptionCursonNonExistence("No se encontraron resultados para '"+nameCurso+"'");
        return myDB.getCursoThatMatchString(nameCurso);
    }

    public Collection<Curso> getCursoByLevel(CursoLevels level) {
        return myDB.getCursoByLevel(level);
    }

    public Collection<Curso> getCursoByNameAndByLevel(String nameCurso, CursoLevels level) {
        return myDB.getCursoByNameAndByLevel(nameCurso, level);
    }

    public Collection<Curso> getCursoBetweenTwoExpirationDate(LocalDate startDate, LocalDate endDate) {
        return myDB.getCursoBetweenTwoExpirationDate(startDate, endDate);
    }

    /*

    //Wstos metodos son de capa presentación mepa
        @Override
        public Curso searchSingleCourse(String nameCurso) {

            return null;
        }


    @Override
    public List<Curso> searchCoursesByExpirationDateInscription(LocalDate expirationDate) {
        return List.of();
    }

 */
}
