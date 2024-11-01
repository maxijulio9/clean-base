package ar.edu.undec.adapter.service.controller;

import ar.edu.undec.adapter.service.domain.CourseDTO;
import curso.input.ISearchCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("courses")
public class SearchCourseController {

    private ISearchCursoInput searchCursoInput;

    @Autowired
    public SearchCourseController(ISearchCursoInput searchCursoInput) {
        this.searchCursoInput = searchCursoInput;
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<?> searchCurso(@PathVariable String name) {
        try {
            Curso curso = searchCursoInput.searchCurso(name);
            CourseDTO   courseDTO = CourseDTO.getInstanceDTO(curso.getId(),curso.getName(),
                                                            curso.getLevel(),curso.getDateExpirationInscription());
            return ResponseEntity.ok(courseDTO);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/findByName/allcourses")
    public ResponseEntity<?> searchAllCourses() {
        try {
            List<Curso> cursos = searchCursoInput.getAllCursos();
            //cocnveritmos a dtos todos
            List<CourseDTO> cursosDTOs = cursos.stream()
                    .map(curso -> CourseDTO.getInstanceDTO(curso.getId(), curso.getName(),
                                                        curso.getLevel(), curso.getDateExpirationInscription()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(cursosDTOs);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/findByNameContaining/{nameletter}")
    public ResponseEntity<?> findByNameContaining(@PathVariable String nameLetter) {
        try {
            List<Curso> cursos = searchCursoInput.getCursoThatMatchString(nameLetter);
            List<CourseDTO> cursosDTOs = cursos.stream()
                    .map(curso -> CourseDTO.getInstanceDTO(curso.getId(), curso.getName(), curso.getLevel(), curso.getDateExpirationInscription()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(cursosDTOs);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/findByLevel/{level}")
    public ResponseEntity<?> findByLevel(@PathVariable CursoLevels level) {
        try {
            List<Curso> cursos = searchCursoInput.getCursoByLevel(level);
            List<CourseDTO> cursosDTOs = cursos.stream()
                    .map(curso -> CourseDTO.getInstanceDTO(curso.getId(), curso.getName(), curso.getLevel(), curso.getDateExpirationInscription()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(cursosDTOs);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/findByNameAndLevel/{name}/{level}")
    public ResponseEntity<?> findByNameAndLevel(@PathVariable String name ,@PathVariable CursoLevels level) {
        try {
            List<Curso> cursos = searchCursoInput.getCursoByNameAndByLevel(name ,level);
            List<CourseDTO> cursosDTOs = cursos.stream()
                    .map(curso -> CourseDTO.getInstanceDTO(curso.getId(), curso.getName(), curso.getLevel(), curso.getDateExpirationInscription()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(cursosDTOs);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/findByExpirationDateBetween/{startdate}/{duedate}")
    public ResponseEntity<?> findByExpirationDateBetween(@PathVariable LocalDate startdate, @PathVariable LocalDate duedate) {
        try {
            List<Curso> cursos = searchCursoInput.getCursoBetweenTwoExpirationDate(startdate,duedate);
            List<CourseDTO> cursosDTOs = cursos.stream()
                    .map(curso -> CourseDTO.getInstanceDTO(curso.getId(), curso.getName(), curso.getLevel(), curso.getDateExpirationInscription()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(cursosDTOs);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
