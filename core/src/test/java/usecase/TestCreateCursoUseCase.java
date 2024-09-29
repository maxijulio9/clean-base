package usecase;

import curso.exception.ExceptionCursoWithASuscriptionDateInvalid;
import curso.exception.ExceptionCursoWithTheSameName;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistence;
import curso.usecase.CursoCreateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.Object;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestCreateCursoUseCase {

    @Mock
    IPersistence myDB;

    @Test
    void CourseWithTheSameNameThrowException() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);
        when(myDB.existsCurso("Matematicas")).thenReturn(true);


        Assertions.assertThrows(ExceptionCursoWithTheSameName.class
                , () -> curso.createCurso("Matematicas", CursoLevels.INICIAL, LocalDate.of(2025, 8, 15)));
    }

    @Test
    void CourseWithADifferentNameDoesNotThrowException() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);
        when(myDB.existsCurso("Lengua")).thenReturn(false);

        Assertions.assertDoesNotThrow(() -> curso.createCurso("Lengua", CursoLevels.INICIAL, LocalDate.of(2025, 8, 15)));
    }

    @Test
    void CourseWithADateBeforeToCurrentDateThrowException(){
       CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);
         //when(myDB.existsCurso("Programación")).thenReturn(false);

         Assertions.assertThrows(ExceptionCursoWithASuscriptionDateInvalid.class,
                 () -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.of(2022, 8, 15)));


    }
}