package curso.usecase;

import curso.exception.ExceptionCursoWithAInvalidLevel;
import curso.input.IValidationAttributeCursoInput;
import curso.modelo.CursoLevels;

import java.time.LocalDate;

public class CursoWithInvalidLevelUseCase implements IValidationAttributeCursoInput {

    @Override
    public void validate(String name, CursoLevels level, LocalDate dateExpirationInscription) throws RuntimeException {
         if (level != CursoLevels.INICIAL && level != CursoLevels.MEDIO && level != CursoLevels.AVANZADO ){
            throw new ExceptionCursoWithAInvalidLevel("El curso que intentas registrar tiene un nivel inválido.");
        }
    }
}
