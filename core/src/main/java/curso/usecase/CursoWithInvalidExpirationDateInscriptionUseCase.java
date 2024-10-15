package curso.usecase;

import curso.exception.ExceptionCursoWithAInscriptionDateInvalid;
import curso.input.IValidationAttributeCursoInput;
import curso.modelo.CursoLevels;

import java.time.LocalDate;

public class CursoWithInvalidExpirationDateInscriptionUseCase implements IValidationAttributeCursoInput {
    @Override
    public void validate(String name, CursoLevels level, LocalDate dateExpirationInscription) throws RuntimeException {
        if(dateExpirationInscription.isBefore(LocalDate.now())){
            throw new ExceptionCursoWithAInscriptionDateInvalid("El curso que intentas registrar tiene fecha de inscripción inválida.");
        }
    }
}
