package usecase;

import curso.exception.ExceptionCursonNonExistence;
import curso.modelo.Curso;
import curso.output.IPersistence;
import curso.usecase.CursoCreateUseCase;
import curso.usecase.CursoSearchUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        //when(myDB.searchCurso("Criptografia")).thenThrow(ExceptionCursonNonExistence.class);

        Assertions.assertThrows(ExceptionCursonNonExistence.class,
                                () -> searchCursoUseCase.searchCurso("Criptografia"));
    }

}
