package ar.edu.undec.adapter.data.implementation;

import ar.edu.undec.adapter.data.crud.ISearchCourseCRUD;
import ar.edu.undec.adapter.data.model.CourseData;
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
        return searchCourseCRUD.existsCourseByName(nameCurso);
    }


    @Override
    public Curso searchCourse(String nameCurso) {
        CourseData courseData = searchCourseCRUD.findByName(nameCurso);
        return courseData.toDomain();
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
        List<Curso> cursos = new ArrayList<>();
        searchCourseCRUD.findByNameContaining(nameCurso).forEach(courseData -> {
            Curso curso = courseData.toDomain();
            cursos.add(curso);
        });


        return cursos;
    }

    @Override
    public List<Curso> getCursoByLevel(CursoLevels level) {
        List<Curso> cursos = new ArrayList<>();
        searchCourseCRUD.findByLevel(level).forEach(courseData -> {
            Curso curso = courseData.toDomain();
            cursos.add(curso);
        });
        return cursos;

    }

    @Override
    public List<Curso> getCursoByNameAndByLevel(String nameCurso, CursoLevels level) {
        List<Curso> cursos = new ArrayList<>();
        searchCourseCRUD.findByNameAndLevel(nameCurso, level).forEach(courseData -> {
            Curso curso = courseData.toDomain();
            cursos.add(curso);
        });
        return cursos;

    }

    @Override
    public List<Curso> getCursoBetweenTwoExpirationDate(LocalDate startDate, LocalDate endDate) {
        List<Curso> cursos = new ArrayList<>();
        searchCourseCRUD.findByDateExpirationInscriptionBetween(startDate, endDate).forEach(courseData -> {
            Curso curso = courseData.toDomain();
            cursos.add(curso);
        });
        return cursos;

    }
}
