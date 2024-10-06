package curso.input;

import curso.modelo.Curso;

import java.util.Collection;

//la db va a usar esta interface
public interface ISearchForCursoThatMatchString {
    Collection<Curso> getCursoThatMatchString(String nameCurso);
}
