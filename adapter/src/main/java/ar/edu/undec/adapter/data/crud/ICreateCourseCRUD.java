package ar.edu.undec.adapter.data.crud;

import ar.edu.undec.adapter.data.model.CourseData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICreateCourseCRUD extends CrudRepository<CourseData, UUID> {
    Optional<CourseData> existsCourseByName(String name);
}
