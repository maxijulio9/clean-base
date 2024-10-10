package curso.input;

import curso.modelo.Curso;

import java.util.Collection;
import java.util.List;

//la db va a usar esta interface
public interface ISearchForCursoThatMatchString {
    List<Curso> getCursoThatMatchString(String nameCurso);
}
