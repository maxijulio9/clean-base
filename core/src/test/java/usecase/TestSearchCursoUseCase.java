package usecase;

import curso.exception.ExceptionCursonNonExistence;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistenceCreation;
import curso.output.IPersistenceSearch;
import curso.usecase.CursoCreateUseCase;
import curso.usecase.CursoSearchUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestSearchCursoUseCase {

    @Mock
    IPersistenceSearch myDB;
    @Mock
    IPersistenceCreation myDBCreation;

    @Test
    public void testSearchCursoFoundIt() {
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        when(myDB.existsCurso("Criptografia")).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> searchCursoUseCase.getSingleCurso("Criptografia"));
    }

    @Test
    public void testSearchCursoNotFoundIt() {
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        when(myDB.existsCurso("Criptografia")).thenReturn(false );

        Assertions.assertThrows(ExceptionCursonNonExistence.class,
                                () -> searchCursoUseCase.getSingleCurso("Criptografia"));
    }

    @Test
    public void testSearchCursoFoundItByName() {
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        //CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);

       // Curso cursito = cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2025, 8, 15));
        Curso cursitoMock = Mockito.mock(Curso.class);

        when(cursitoMock.getName()).thenReturn("Criptografia");

        when(myDB.existsCurso("Criptografia")).thenReturn(true);
        //when(myDB.getSingleCurso("Criptografia")).thenReturn(cursito);
        when(myDB.getSingleCurso("Criptografia")).thenReturn(cursitoMock);

       // Assertions.assertEquals(cursito.getName(), searchCursoUseCase.getSingleCurso("Criptografia").getName());
        Assertions.assertEquals(cursitoMock.getName(), searchCursoUseCase.getSingleCurso("Criptografia").getName());

        Mockito.verify(myDB, Mockito.times(1)).getSingleCurso("Criptografia");
        Mockito.verify(myDB, Mockito.times(1)).existsCurso("Criptografia");
    }

    @Test
    public  void testSearchAllCursos(){
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);
        List<Curso> cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                 cursoCreateUseCase.createCurso("Auditoria", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6))
                 );
        when(myDB.getAllCursos()).thenReturn(cursoList);

        Assertions.assertEquals(2, searchCursoUseCase.getAllCursos().size());
        Assertions.assertEquals("Criptografia", searchCursoUseCase.getAllCursos().get(0).getName());
        Assertions.assertEquals("Auditoria", searchCursoUseCase.getAllCursos().get(1).getName());

        Mockito.verify(myDB, Mockito.times(3)).getAllCursos();
    }


    @Test
    public  void testSearchCursoByLevel() {
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);

        Curso curso1 = Mockito.mock(Curso.class);
        Curso curso2 = Mockito.mock(Curso.class);
        Curso curso3 = Mockito.mock(Curso.class);

        when(curso1.getName()).thenReturn("Criptografia");

        when(curso2.getName()).thenReturn("Criptografia II");

        when(curso3.getName()).thenReturn("Auditoria");



        List<Curso> coursesMedio = Arrays.asList(curso1, curso2);
        List<Curso> coursesAvanzado = Arrays.asList(curso3);


        when(myDB.getCursoByLevel(CursoLevels.MEDIO)).thenReturn(coursesMedio);
        when(myDB.getCursoByLevel(CursoLevels.AVANZADO)).thenReturn(coursesAvanzado);

        Assertions.assertEquals(2, searchCursoUseCase.getCursoByLevel(CursoLevels.MEDIO).size());
        Assertions.assertEquals("Criptografia", searchCursoUseCase.getCursoByLevel(CursoLevels.MEDIO).get(0).getName());
        Assertions.assertEquals("Criptografia II", searchCursoUseCase.getCursoByLevel(CursoLevels.MEDIO).get(1).getName());

        Assertions.assertEquals(1, searchCursoUseCase.getCursoByLevel(CursoLevels.AVANZADO).size());
        Assertions.assertEquals("Auditoria", searchCursoUseCase.getCursoByLevel(CursoLevels.AVANZADO).get(0).getName());


        Mockito.verify(myDB, Mockito.times(3)).getCursoByLevel(CursoLevels.MEDIO);
        Mockito.verify(myDB, Mockito.times(2)).getCursoByLevel(CursoLevels.AVANZADO);
    }

    @Test
    public  void testSearchCursosThatMatchString(){
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);
        List<Curso> cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Auditoria", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6))
        );

        cursoList = new ArrayList<>(cursoList);
        cursoList.remove(0);
        when(myDB.existsCurso("u")).thenReturn(true);
        when(myDB.getCursoThatMatchString("u")).thenReturn(cursoList);

        Assertions.assertEquals(1, searchCursoUseCase.getCursoThatMatchString("u").size());
        Assertions.assertEquals("Auditoria", searchCursoUseCase.getCursoThatMatchString("u").get(0).getName());

    }

    @Test
    public  void testSearchCursoBetweenTwoExpirationDate(){
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);
        List<Curso> cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Algebra", CursoLevels.AVANZADO, LocalDate.of(2028, 1, 6)),
                cursoCreateUseCase.createCurso("Auditoria", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6))
        );

        cursoList = new ArrayList<>(cursoList);
        cursoList.remove(1);

        LocalDate startDate = LocalDate.of(2025, 1, 6);
        LocalDate endDate = LocalDate.of(2027, 1, 6);

        when(myDB.getCursoBetweenTwoExpirationDate(startDate, endDate)).thenReturn(cursoList);

        Assertions.assertEquals(2, searchCursoUseCase.getCursoBetweenTwoExpirationDate(startDate, endDate).size());
        Assertions.assertEquals("Auditoria", searchCursoUseCase.getCursoBetweenTwoExpirationDate(startDate, endDate).get(1).getName());

    }

    @Test
    public  void testSearchCursoByNameAndLevel(){
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);
        List<Curso> cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2030 , 10, 6)),
                cursoCreateUseCase.createCurso("Algebra", CursoLevels.AVANZADO, LocalDate.of(2028, 1, 6)),
                cursoCreateUseCase.createCurso("Auditoria", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6))
        );

        cursoList = new ArrayList<>(cursoList);
        cursoList.remove(2);
        cursoList.remove(2);


        when(myDB.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO)).thenReturn(cursoList);

        Assertions.assertEquals(2, searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO).size());

        Assertions.assertEquals("Criptografia", searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO).get(1).getName());

    }

}
