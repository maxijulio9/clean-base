package ar.edu.undec.adapter.data.implementation;

import ar.edu.undec.adapter.data.crud.ISearchCourseCRUD;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import curso.output.IPersistenceSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class CourseSearchDBImplementation implements IPersistenceSearch {


    ISearchCourseCRUD searchCourseCRUD;

    @Autowired
    public CourseSearchDBImplementation(ISearchCourseCRUD searchCourseCRUD) {
        this.searchCourseCRUD = searchCourseCRUD;
    }

    @Override
    public boolean existsCurso(String nameCurso) {
        return searchCourseCRUD.searchCourseByName(nameCurso);
    }


    @Override
    public Curso searchCourse(String nameCurso) {
        return searchCourseCRUD.searchCourse(nameCurso);
    }

    @Override
    public List<Curso> getAllCursos() {
        List<Curso> cursos = new ArrayList<>();
        searchCourseCRUD.findAll().forEach(courseData -> {
                                             Curso curso = courseData.toDomain();
                                            cursos.add(curso);
                                              });
        return cursos;
    }

    @Override
    public List<Curso> getCursoThatMatchString(String nameCurso) {
        return searchCourseCRUD.getCursoThatMatchString(nameCurso);
    }

    @Override
    public List<Curso> getCursoByLevel(CursoLevels level) {
        return searchCourseCRUD.getCursoByLevel(level);
    }

    @Override
    public List<Curso> getCursoByNameAndByLevel(String nameCurso, CursoLevels level) {
        return searchCourseCRUD.getCursoByNameAndByLevel(nameCurso, level);
    }

    @Override
    public List<Curso> getCursoBetweenTwoExpirationDate(LocalDate startDate, LocalDate endDate) {
        return searchCourseCRUD.getCursoBetweenTwoExpirationDate(startDate, endDate);
    }
}
