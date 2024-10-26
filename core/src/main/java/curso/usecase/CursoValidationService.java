package curso.usecase;

import curso.exception.ExceptionCursonNonExistence;
import curso.output.IPersistenceSearch;

public class CursoValidationService {
    private final IPersistenceSearch persistence;

    public CursoValidationService(IPersistenceSearch persistence) {
        this.persistence = persistence;
    }

    public void validateCursoExistence(String nameCurso) throws ExceptionCursonNonExistence {
        if (!persistence.existsCurso(nameCurso)) {
            throw new ExceptionCursonNonExistence("No se encontraron resultados para '" + nameCurso + "'");
        }
    }
}
