package curso.input;

import curso.modelo.CursoLevels;

import java.time.LocalDate;

public interface IValidationAttributeCursoInput {
    void validate(String name, CursoLevels level, LocalDate dateExpirationInscription) throws RuntimeException;
}
