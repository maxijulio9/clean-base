package ar.edu.undec.adapter.data.dbapi;

import curso.modelo.Curso;
import curso.output.IPersistenceCreation;

public class CourseCreationDBImplementation implements IPersistenceCreation {

    @Override
    public boolean existsCurso(String nameCurso) {
        return false;
    }

    @Override
    public boolean saveCurso(Curso cursoCurso) {
        return false;
    }
}
