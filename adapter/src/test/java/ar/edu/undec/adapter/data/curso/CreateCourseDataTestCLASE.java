package ar.edu.undec.adapter.data.curso;

import ar.edu.undec.adapter.data.crud.ICreateCourseCRUD;
import ar.edu.undec.adapter.data.implementation.CourseCreationDBImplementation;
import ar.edu.undec.adapter.data.model.CourseData;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistenceCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCourseDataTestCLASE {
    @InjectMocks //mock de una abstracion o de calse que recibe un bean
    CourseCreationDBImplementation courseCreationDB;
    @Mock
    ICreateCourseCRUD crud;

    @Test
    public void saveCourse_CourseSaved_Success(){
        Curso course = Curso.getInstance(UUID.randomUUID(), "TESt", CursoLevels.MEDIO, LocalDate.of(2026,02,03));
        when(crud.save(Mockito.any((CourseData.class)))).thenReturn(new CourseData());

        boolean result =  courseCreationDB.saveCurso(course);

        Assertions.assertTrue(result);

    }
    @Test
    public void saveCourse_CourseSaved_NOTSuccess(){
        Curso course = Curso.getInstance(UUID.randomUUID(), "E", CursoLevels.MEDIO, LocalDate.of(2026,02,03));
        when(crud.save(Mockito.any((CourseData.class)))).thenThrow(RuntimeException.class);

        boolean result =  courseCreationDB.saveCurso(course);

        Assertions.assertFalse(result);

    }

}

