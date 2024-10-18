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

    private CursoValidationService cursoValidationService;
    private IPersistenceSearch myDB;

    public CursoSearchUseCase(IPersistenceSearch persistence){
        this.myDB = persistence;
        this.cursoValidationService = new CursoValidationService(persistence);
    }

    public Curso getSingleCurso(String nameCurso) throws ExceptionCursonNonExistence {

        /*
        delegupé responsabilidad a la clase servicio
        if (!myDB.existsCurso(nameCurso)) {
            System.out.println("El curso no existe");
            throw new ExceptionCursonNonExistence("No se encontraron resultados para '" + nameCurso + "'");

        }
         */
        cursoValidationService.validateCursoExistence(nameCurso);
        return myDB.getSingleCurso(nameCurso);
    }

    public List<Curso> getAllCursos() {
        return myDB.getAllCursos();
    }

    public List<Curso> getCursoThatMatchString(String nameCurso) {
        //if (!myDB.existsCurso(nameCurso)) throw new ExceptionCursonNonExistence("No se encontraron resultados para '"+nameCurso+"'");
        cursoValidationService.validateCursoExistence(nameCurso);
        return myDB.getCursoThatMatchString(nameCurso);
    }

    public List<Curso> getCursoByLevel(CursoLevels level) {
        return myDB.getCursoByLevel(level);
    }

    public List<Curso> getCursoByNameAndByLevel(String nameCurso, CursoLevels level) {
        return myDB.getCursoByNameAndByLevel(nameCurso, level);
    }

    public List<Curso> getCursoBetweenTwoExpirationDate(LocalDate startDate, LocalDate endDate) {
        return myDB.getCursoBetweenTwoExpirationDate(startDate, endDate);
    }

    /*

    @Override
    public List<Curso> searchCoursesByExpirationDateInscription(LocalDate expirationDate) {
        return List.of();
    }

 */
}
