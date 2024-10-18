package usecase;

import curso.exception.ExceptionCursonNonExistence;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistenceCreation;
import curso.output.IPersistenceSearch;
import curso.usecase.CursoSearchUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
        when(myDB.getSingleCurso("Criptografia")).thenReturn(cursitoMock);

       // Assertions.assertEquals(cursito.getName(), searchCursoUseCase.getSingleCurso("Criptografia").getName());
        Assertions.assertEquals(cursitoMock.getName(), searchCursoUseCase.getSingleCurso("Criptografia").getName());

        Mockito.verify(myDB, Mockito.times(1)).getSingleCurso("Criptografia");
        Mockito.verify(myDB, Mockito.times(1)).existsCurso("Criptografia");
    }

    @Test
    public  void testSearchAllCursos(){
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);

        Curso curso1 = Mockito.mock(Curso.class);
        Curso curso2 = Mockito.mock(Curso.class);
        Curso curso3 = Mockito.mock(Curso.class);


        List<Curso> allCursos =  Arrays.asList(curso1, curso2, curso3);

        when(myDB.getAllCursos()).thenReturn(allCursos);

        when(curso1.getName()).thenReturn("Criptografia");
        when(curso2.getName()).thenReturn("Auditoria");
        when(curso3.getName()).thenReturn("Inteligencia Artificial");



        Assertions.assertEquals(3, searchCursoUseCase.getAllCursos().size());
        Assertions.assertEquals("Criptografia", searchCursoUseCase.getAllCursos().get(0).getName());
        Assertions.assertEquals("Auditoria", searchCursoUseCase.getAllCursos().get(1).getName());
        Assertions.assertEquals("Inteligencia Artificial", searchCursoUseCase.getAllCursos().get(2).getName());

        Mockito.verify(myDB, Mockito.times(4)).getAllCursos();
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

        Curso curso1 = Mockito.mock(Curso.class);
        Curso curso2 = Mockito.mock(Curso.class);
        Curso curso3 = Mockito.mock(Curso.class);
        Curso curso4 = Mockito.mock(Curso.class);

        /*CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);
        List<Curso> cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Auditoria", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6))
        );

         */
        when(curso1.getName()).thenReturn("Física nuclear");
        when(curso2.getName()).thenReturn("Cálculo");
        when(curso3.getName()).thenReturn("Criptografía");
        when(curso4.getName()).thenReturn("u");

        List<Curso> cursitosMock1 =  Arrays.asList(curso1, curso2,  curso4);
        List<Curso> cursitosMock2 =  Arrays.asList(curso3);

        when(myDB.existsCurso("u")).thenReturn(true);
        when(myDB.getCursoThatMatchString("u")).thenReturn(cursitosMock1);

        when(myDB.existsCurso("Criptografía")).thenReturn(true);
        when(myDB.getCursoThatMatchString("Criptografía")).thenReturn(cursitosMock2);

        when(myDB.existsCurso("Criptografia II")).thenReturn(false);

        Assertions.assertEquals(3, searchCursoUseCase.getCursoThatMatchString("u").size());
        Assertions.assertEquals("Física nuclear", searchCursoUseCase.getCursoThatMatchString("u").get(0).getName());
        Assertions.assertEquals("Cálculo", searchCursoUseCase.getCursoThatMatchString("u").get(1).getName());
        Assertions.assertEquals("u", searchCursoUseCase.getCursoThatMatchString("u").get(2).getName());

        Assertions.assertEquals(1, searchCursoUseCase.getCursoThatMatchString("Criptografía").size());
        Assertions.assertEquals("Criptografía", searchCursoUseCase.getCursoThatMatchString("Criptografía").get(0).getName());


        Assertions.assertThrows(ExceptionCursonNonExistence.class, ()->searchCursoUseCase.getCursoThatMatchString("Criptografia II"));

        Mockito.verify(myDB, Mockito.times(4)).getCursoThatMatchString("u");;
        Mockito.verify(myDB, Mockito.times(2)).getCursoThatMatchString("Criptografía");;
        Mockito.verify(myDB, Mockito.times(0)).getCursoThatMatchString("Criptografia II");;
    }

    @Test
    public  void testSearchCursoBetweenTwoExpirationDate(){
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        /*CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);
        List<Curso> cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Algebra", CursoLevels.AVANZADO, LocalDate.of(2028, 1, 6)),
                cursoCreateUseCase.createCurso("Auditoria", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6))
        );

         */
        Curso curso1 = Mockito.mock(Curso.class);
        Curso curso2 = Mockito.mock(Curso.class);
        //Curso curso3 = Mockito.mock(Curso.class);

        when(curso1.getName()).thenReturn("Auditoria");

       // when(curso1.getDateExpirationInscription()).thenReturn(LocalDate.now().plusYears(2));
        //when(curso2.getDateExpirationInscription()).thenReturn(LocalDate.now().plusYears(5));


        List<Curso> cursitosMock1 =  Arrays.asList(curso1, curso2);

        LocalDate startDate = LocalDate.now().plusYears(1);
        LocalDate endDate = LocalDate.now().plusYears(6);

        when(myDB.getCursoBetweenTwoExpirationDate(startDate, endDate)).thenReturn(cursitosMock1);

        Assertions.assertEquals(2, searchCursoUseCase.getCursoBetweenTwoExpirationDate(startDate, endDate).size());
        Assertions.assertEquals("Auditoria", searchCursoUseCase.getCursoBetweenTwoExpirationDate(startDate, endDate).get(0).getName());

    }

    @Test
    public  void testSearchCursoByNameAndLevel(){
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);

       /* CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);
        List<Curso> cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2030 , 10, 6)),
                cursoCreateUseCase.createCurso("Algebra", CursoLevels.AVANZADO, LocalDate.of(2028, 1, 6)),
                cursoCreateUseCase.createCurso("Auditoria", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6))
        );

        */
        Curso curso1 = Mockito.mock(Curso.class);
        Curso curso2 = Mockito.mock(Curso.class);
        Curso curso3 = Mockito.mock(Curso.class);
        Curso curso4 = Mockito.mock(Curso.class);


        when(curso1.getName()).thenReturn("Criptografia");
        when(curso1.getLevel()).thenReturn(CursoLevels.MEDIO);

        when(curso2.getName()).thenReturn("Criptografia II");
        when(curso2.getLevel()).thenReturn(CursoLevels.MEDIO);

        when(curso3.getName()).thenReturn("Criptografia III");
        when(curso3.getLevel()).thenReturn(CursoLevels.AVANZADO);

        when(curso4.getName()).thenReturn("Blockchain");
        when(curso4.getLevel()).thenReturn(CursoLevels.AVANZADO);


        when(myDB.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO)).thenReturn(Arrays.asList(curso1,curso2));
        when(myDB.getCursoByNameAndByLevel("Criptografia", CursoLevels.AVANZADO)).thenReturn(Arrays.asList(curso3));
        when(myDB.getCursoByNameAndByLevel("B", CursoLevels.AVANZADO)).thenReturn(Arrays.asList(curso4));


        Assertions.assertEquals(2, searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO).size());
        Assertions.assertEquals("Criptografia", searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO).get(0).getName());
        Assertions.assertEquals(CursoLevels.MEDIO, searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO).get(0).getLevel());
        Assertions.assertEquals("Criptografia II", searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO).get(1).getName());
        Assertions.assertEquals(CursoLevels.MEDIO, searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO).get(1).getLevel());

        Assertions.assertEquals(1, searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.AVANZADO).size());
        Assertions.assertEquals("Criptografia III", searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.AVANZADO).get(0).getName());
        Assertions.assertEquals( CursoLevels.AVANZADO, searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.AVANZADO).get(0).getLevel());

        Assertions.assertEquals(1, searchCursoUseCase.getCursoByNameAndByLevel("Criptografia", CursoLevels.AVANZADO).size());
        Assertions.assertEquals("Blockchain", searchCursoUseCase.getCursoByNameAndByLevel("B", CursoLevels.AVANZADO).get(0).getName());
        Assertions.assertEquals( CursoLevels.AVANZADO, searchCursoUseCase.getCursoByNameAndByLevel("B", CursoLevels.AVANZADO).get(0).getLevel());

        Mockito.verify(myDB, Mockito.times(5)).getCursoByNameAndByLevel("Criptografia", CursoLevels.MEDIO);
    }

}
