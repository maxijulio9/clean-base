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

import java.time.LocalDate;

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

}
