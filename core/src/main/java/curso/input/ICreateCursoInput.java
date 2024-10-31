package curso.input;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.time.LocalDate;
import java.util.UUID;

public interface ICreateCursoInput {
    Curso createCurso(UUID id, String name, CursoLevels level, LocalDate dateExpiritInscription);

}
