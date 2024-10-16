package curso.usecase;

import curso.exception.ExceptionCursoWithMissingAttributes;
import curso.input.IValidationAttributeCursoInput;
import curso.modelo.CursoLevels;

import java.time.LocalDate;

public class CursoWithNullAttributeValidatorUseCase implements IValidationAttributeCursoInput {

    @Override
    public void validate(String name, CursoLevels level, LocalDate dateExpirationInscription) throws RuntimeException {
        if (name ==null
                || level  == null
                || dateExpirationInscription ==null){
            throw new ExceptionCursoWithMissingAttributes("Debés proporcionar todos los atributos del curso a registrar.");
        }
    }
}
