package curso.input;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.time.LocalDate;

public interface ICreateCursoInput {
    Curso createCurso(String name, CursoLevels level, LocalDate dateExpiritInscription);

}
