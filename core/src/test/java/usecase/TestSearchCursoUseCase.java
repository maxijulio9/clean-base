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
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

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
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);

        Curso cursito = cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2025, 8, 15));

        when(myDB.existsCurso("Criptografia")).thenReturn(true);
        when(myDB.getSingleCurso("Criptografia")).thenReturn(cursito);

        Assertions.assertEquals(cursito.getName(), searchCursoUseCase.getSingleCurso("Criptografia").getName());
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
    }


    @Test
    public  void testSearchCursoByLevel(){
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDBCreation);
        List<Curso> cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Cálculo", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Auditoria", CursoLevels.AVANZADO, LocalDate.of(2026, 10, 6))
        );
        cursoList = new ArrayList<>(cursoList);
        cursoList.remove(2);
        when(myDB.getCursoByLevel(CursoLevels.MEDIO)).thenReturn(cursoList);

        Assertions.assertEquals(2, searchCursoUseCase.getCursoByLevel(CursoLevels.MEDIO).size());
        Assertions.assertEquals("Criptografia", searchCursoUseCase.getCursoByLevel(CursoLevels.MEDIO).get(0).getName());

        cursoList = Arrays.asList(
                cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Cálculo", CursoLevels.MEDIO, LocalDate.of(2026, 10, 6)),
                cursoCreateUseCase.createCurso("Auditoria", CursoLevels.AVANZADO, LocalDate.of(2026, 10, 6))
        );
        cursoList = new ArrayList<>(cursoList);
        cursoList.remove(0);
        cursoList.remove(0);

        when(myDB.getCursoByLevel(CursoLevels.AVANZADO)).thenReturn(cursoList);
        Assertions.assertEquals("Auditoria", searchCursoUseCase.getCursoByLevel(CursoLevels.AVANZADO).get(0).getName());
    }

}
