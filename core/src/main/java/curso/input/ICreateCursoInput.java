package curso.input;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.time.LocalDate;

public interface ICreateCursoInput {
    void createCurso(String name, CursoLevels level, LocalDate dateExpiritInscription);

}
