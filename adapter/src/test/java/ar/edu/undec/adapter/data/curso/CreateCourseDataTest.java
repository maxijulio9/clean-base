package ar.edu.undec.adapter.data.curso;

import ar.edu.undec.adapter.data.crud.ICreateCourseCRUD;
import ar.edu.undec.adapter.data.implementation.CourseCreationDBImplementation;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class CreateCourseDataTest {
    @InjectMocks
    CourseCreationDBImplementation courseCreationDBImple;
    @Mock
    ICreateCourseCRUD createCourseCRUD;
    @Test
    public void testCreateCourseAndSaveIt() {
        Curso curso = Curso.getInstance("Auditoria", CursoLevels.MEDIO, LocalDate.now().plusDays(4));

        boolean result = courseCreationDBImple.saveCurso(curso);
        Assertions.assertTrue(result);

    }




}
