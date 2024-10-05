package usecase;

import curso.exception.ExceptionCursonNonExistence;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistence;
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
    IPersistence myDB;

    @Test
    public void testSearchCursoFoundIt() {
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        when(myDB.existsCurso("Criptografia")).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> searchCursoUseCase.searchCurso("Criptografia"));
    }

    @Test
    public void testSearchCursoNotFoundIt() {
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        when(myDB.existsCurso("Criptografia")).thenReturn(false );
        Assertions.assertThrows(ExceptionCursonNonExistence.class,
                                () -> searchCursoUseCase.searchCurso("Criptografia"));
    }
    @Test
    public void testSearchCursoFoundItByName() {
        CursoSearchUseCase searchCursoUseCase = new CursoSearchUseCase(myDB);
        CursoCreateUseCase cursoCreateUseCase = new CursoCreateUseCase(myDB);
        Curso cursito = cursoCreateUseCase.createCurso("Criptografia", CursoLevels.MEDIO, LocalDate.of(2025, 8, 15));

        when(myDB.existsCurso("Criptografia")).thenReturn(true);
        when(myDB.searchCurso("Criptografia")).thenReturn(cursito);

        Assertions.assertEquals(cursito.getName(), searchCursoUseCase.searchCurso("Criptografia").getName());
    }

}
