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

public class CursoSearchUseCase{

    private CursoValidationService cursoValidationService;
    private IPersistenceSearch myDB;

    public CursoSearchUseCase(IPersistenceSearch persistence){
        this.myDB = persistence;
        this.cursoValidationService = new CursoValidationService(persistence);
    }

    @Override
    public Curso searchCurso(String nameCurso) throws ExceptionCursonNonExistence {

        if (!myDB.existsCurso(nameCurso)) throw new ExceptionCursonNonExistence("No se encontraron resultados para '"+nameCurso+"'");
        return myDB.searchCurso(nameCurso);
    }

    public List<Curso> getAllCursos() {
        return myDB.getAllCursos();
    }

    public List<Curso> getCursoThatMatchString(String nameCurso) {
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

  
}
