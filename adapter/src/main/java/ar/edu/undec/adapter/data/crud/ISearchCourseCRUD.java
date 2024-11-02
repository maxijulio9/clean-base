package ar.edu.undec.adapter.data.crud;

import ar.edu.undec.adapter.data.model.CourseData;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ISearchCourseCRUD extends CrudRepository<CourseData, UUID> {
    boolean existsCourseByName(String name);

    CourseData findByName(String nameCurso);

    List<CourseData> findAll();

    @Query("SELECT c FROM cursos c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<CourseData> findByNameContaining(@Param("name") String name);

    List<CourseData> findByLevel(CursoLevels level);

    List<CourseData> findByNameAndLevel(String nameCurso, CursoLevels level);

    List<CourseData> findByDateExpirationInscriptionBetween(LocalDate startDate, LocalDate endDate);
}
