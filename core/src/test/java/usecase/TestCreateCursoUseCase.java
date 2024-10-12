package usecase;

import curso.exception.*;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistence;
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
    IPersistence myDB;


    @Test
    void CourseWithEmptyNameThrowException() {
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDB);

      //  when(myDB.existsCurso("")).thenReturn(false);
        Assertions.assertThrows(ExceptionCourseEmptyString.class, () -> cursoCreateUseCase.createCurso("   ", CursoLevels.MEDIO,LocalDate.now().plusYears(1)));

    }
    @Test
    void CourseWithValidNameDoesNotThrowException() {
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDB);

        //  when(myDB.existsCurso("")).thenReturn(false);
        Assertions.assertDoesNotThrow(() -> cursoCreateUseCase.createCurso("  a  ", CursoLevels.MEDIO, LocalDate.now().plusYears(1)));

    }

    @Test
    void CourseWithTheSameNameThrowException() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);

        when(myDB.existsCurso("Matematicas")).thenReturn(true);

        Assertions.assertThrows(ExceptionCursoWithTheSameName.class
                , () -> curso.createCurso("Matematicas", CursoLevels.INICIAL,LocalDate.now().plusYears(1)));

    }
    @Test
    void CourseCreatedCorrectly(){
        CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);

        when(myDB.existsCurso("Programación")).thenReturn(false);

        Curso cursito = curso.createCurso("Programación", CursoLevels.MEDIO,LocalDate.now().plusDays(1));

        Assertions.assertEquals("Programación",cursito.getName());
    }

    @Test
    void CourseWithADifferentNameDoesNotThrowException() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);
        when(myDB.existsCurso("Cálculo estádistico")).thenReturn(false);

        Assertions.assertDoesNotThrow(() -> curso.createCurso("Cálculo estádistico", CursoLevels.INICIAL, LocalDate.now().plusYears(1)));
        Mockito.verify(myDB).existsCurso("Cálculo estádistico");

    }
    @Test
    void CourseWithAllAttributesDoesNotThrowAnyException() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);

        when(myDB.existsCurso("Física nuclear")).thenReturn(false);

       Curso cursito = Assertions.assertDoesNotThrow(() -> curso.createCurso("Física nuclear", CursoLevels.INICIAL,LocalDate.now().plusYears(1)));

        Assertions.assertEquals("Física nuclear", cursito.getName());

        Mockito.verify(myDB, Mockito.times(1)).existsCurso("Física nuclear");
        Mockito.verify(myDB).saveCurso(Mockito.any(Curso.class));

    }
/*
    @Test
    void CourseWithMissingAttributesThrowException() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);
        //hen(myDB.existsCurso("Lengua")).thenReturn(false);
        Assertions.assertThrows(ExceptionCursoWithMissingAttributes.class, () -> curso.createCurso("Algebra", null, LocalDate.of(2025, 8, 15)));
    }
 */


    @Test
    void CourseWithADateBeforeToTheCurrentDateThrowException(){
       CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);
         //when(myDB.existsCurso("Programación")).thenReturn(false);

         Assertions.assertThrows(ExceptionCursoWithAInscriptionDateInvalid.class,
                 () -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.of(2024,03,1)));

         Mockito.verify(myDB, Mockito.never()).existsCurso("Programación");
        Mockito.verify(myDB, Mockito.never()).saveCurso(Mockito.any(Curso.class));
    }

    @Test
    void CourseWithADateBeforeToCurrentDateDoesNotThrowException(){
        CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);

        Assertions.assertDoesNotThrow(() -> curso.createCurso("Programación", CursoLevels.MEDIO,LocalDate.now().plusYears(1)));

        Mockito.verify(myDB, Mockito.times(1)).saveCurso(Mockito.any(Curso.class));
        Mockito.verify(myDB).saveCurso(Mockito.any(Curso.class));
    }

    @Test
    void CourseWithAInvalidLevelThrowException(){
        CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);

        Assertions.assertThrows(ExceptionCursoWithAInvalidLevel.class,() -> curso.createCurso("Programación", CursoLevels.SUPERSAYAYIN, LocalDate.now().plusYears(1)));
        Assertions.assertThrows(ExceptionCursoWithAInvalidLevel.class,() -> curso.createCurso("Programación", null, LocalDate.now().plusYears(1)));

        Mockito.verify(myDB, Mockito.never()).existsCurso("Programación");
        Mockito.verify(myDB, Mockito.never()).saveCurso(Mockito.any(Curso.class));

    }

    @Test
    void CourseWithAValidLevelDoesNotThrowException(){
        CursoCreateUseCase curso =  new CursoCreateUseCase(myDB);

        Assertions.assertDoesNotThrow(() -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.now().plusYears(1)));
        Mockito.verify(myDB).existsCurso("Programación");

    }

    @Test
    void CourseDoesNotExistAndSavesInDBSuccessfully() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);

        when(myDB.existsCurso("Programación")).thenReturn(false);

        when(myDB.saveCurso(Mockito.any(Curso.class))).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.now().plusYears(1)));

        Mockito.verify(myDB).existsCurso("Programación");
        Mockito.verify(myDB).saveCurso(Mockito.any(Curso.class));
    }

    @Test
    void CourseExistsAndDoesNotSavesInDBSuccessfully() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);

        when(myDB.existsCurso("Programación")).thenReturn(true);

        //when(myDB.saveCurso(Mockito.any(Curso.class))).thenThrow(ExceptionCursoErrorSavingInDB.class);

        Assertions.assertThrows(ExceptionCursoWithTheSameName.class,  () -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.now().plusYears(1)));

        Mockito.verify(myDB).existsCurso("Programación");
        Mockito.verify(myDB, Mockito.never()).saveCurso(Mockito.any(Curso.class));

    }

    @Test
    void CourseDoesNotExistsButDoesNotSavesInDBSuccessfully() {
        CursoCreateUseCase curso = new CursoCreateUseCase(myDB);

        when(myDB.existsCurso("Programación")).thenReturn(false);

        when(myDB.saveCurso(Mockito.any(Curso.class))).thenThrow(ExceptionCursoErrorSavingInDB.class);

        Assertions.assertThrows(ExceptionCursoErrorSavingInDB.class, () -> curso.createCurso("Programación", CursoLevels.MEDIO, LocalDate.now().plusYears(1)));

        Mockito.verify(myDB).existsCurso("Programación");
        Mockito.verify(myDB, Mockito.times(1)).saveCurso(Mockito.any(Curso.class));
    }

}
