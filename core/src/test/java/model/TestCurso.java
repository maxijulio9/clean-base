package model;

import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCurso {

    //Sin MOCKITO
      @Test
      void TestAttributesAllMandatory () {
            Curso micurso = Curso.getInstance("Física", CursoLevels.INICIAL, LocalDate.of(2025,8,15));
            assertNotNull(micurso);
      }
      @Test
      void TestAttributesIsMissing() {
          Curso micurso = Curso.getInstance("Física", null,  LocalDate.of(2025,8,15));
          assertEquals(null,micurso);
      }

      @Test
      void TestDateIsBeforeNow() {
          Curso micurso = Curso.getInstance("Física", CursoLevels.INICIAL, LocalDate.of(2024,8,15));
          assertEquals(null, micurso);

      }
    @Test
    void TestDateIsNotBeforeNow() {
        Curso micurso = Curso.getInstance("Física", CursoLevels.INICIAL, LocalDate.of(2024,9,28));
        assertEquals(micurso, micurso);
    }

    //Instanciar un meotdo UUID
    //UUID.randomUUID()
}
