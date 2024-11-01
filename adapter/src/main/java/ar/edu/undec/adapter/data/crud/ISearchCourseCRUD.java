package ar.edu.undec.adapter.data.crud;

import ar.edu.undec.adapter.data.model.CourseData;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ISearchCourseCRUD extends CrudRepository<CourseData, UUID> {
    boolean searchCourseByName(String name);

    Curso searchCourse(String nameCurso);

    List<Curso> getAllCursos();

    List<Curso> findByNameContaining(String nameCurso);

    List<Curso> findByLevel(CursoLevels level);

    List<Curso> findByNameAndLevel(String nameCurso, CursoLevels level);

    List<Curso> findByExpirationDateBetween(LocalDate startDate, LocalDate endDate);
}
