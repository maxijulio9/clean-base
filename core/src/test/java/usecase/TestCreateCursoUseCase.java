package usecase;

import curso.exception.ExceptionCursoWithAInvalidLevel;
import curso.exception.ExceptionCursoWithAInscriptionDateInvalid;
import curso.exception.ExceptionCursoWithMissingAttributes;
import curso.exception.ExceptionCursoWithTheSameName;
import curso.modelo.CursoLevels;
import curso.output.IPersistenceCreation;
import curso.usecase.CursoCreateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestCreateCursoUseCase {

    @Mock
    IPersistenceCreation myDB;


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
    void CourseWithAllAttributesDoesNotThrowException() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);

        when(myDB.existsCurso("Física nuclear")).thenReturn(false);
        Assertions.assertDoesNotThrow(() -> curso.createCurso("Física nuclear", CursoLevels.INICIAL, LocalDate.of(2025, 8, 15)));
        Mockito.verify(myDB, Mockito.times(1)).existsCurso("Física nuclear");
    }

    @Test
    void CourseWithMissingAttributesThrowException() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);
        //when(myDB.existsCurso("Lengua")).thenReturn(false);
        Assertions.assertThrows(ExceptionCursoWithMissingAttributes.class, () -> curso.createCurso(null, CursoLevels.INICIAL, LocalDate.of(2025, 8, 15)));
    }


    @Test
    void CourseWithADateBeforeToTheCurrentDateThrowException(){
       CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);
         //when(myDB.existsCurso("Programación")).thenReturn(false);

         Assertions.assertThrows(ExceptionCursoWithAInscriptionDateInvalid.class,
                 () -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.of(2022, 8, 15)));


    }
    @Test
    void CourseWithADateBeforeToCurrentDateDoesNotThrowException(){
        CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);
        //when(myDB.existsCurso("Programación")).thenReturn(false);

        Assertions.assertDoesNotThrow(() -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.of(2024, 10, 6)));
    }
    @Test
    void CourseWithALevelInvalidThrowException(){
        CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);
        //when(myDB.existsCurso("Programación")).thenReturn(false);

        Assertions.assertThrows(ExceptionCursoWithAInvalidLevel.class,() -> curso.createCurso("Programación", CursoLevels.SUPERSAYAYIN, LocalDate.of(2024, 10, 6)));
    }
    @Test
    void CourseWithALevelInvalidDoesNotThrowException(){
        CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);

        Assertions.assertDoesNotThrow(() -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.of(2024, 10, 6)));
    }

}
