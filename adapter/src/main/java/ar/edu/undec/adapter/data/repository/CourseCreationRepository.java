package ar.edu.undec.adapter.data.repository;

import curso.exception.ExceptionCursoWithTheSameName;
import curso.input.ICreateCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistenceCreation;

import java.time.LocalDate;

public class CourseCreationRepository implements ICreateCursoInput {

    IPersistenceCreation persistenceCreation;

    public CourseCreationRepository(IPersistenceCreation persistenceCreation) {
        this.persistenceCreation = persistenceCreation;
    }

    @Override
    public Curso createCurso(String name, CursoLevels level, LocalDate dateExpiritInscription) {
        Curso curso  = Curso.getInstance(name, level, dateExpiritInscription);

        if (persistenceCreation.existsCurso(name)){
            throw new ExceptionCursoWithTheSameName("El curso que intentas registrar ye existe.");
        }
        persistenceCreation.saveCurso(curso);
        return curso;
    }
}
