package ar.edu.undec.adapter.service.controller;

import ar.edu.undec.adapter.data.crud.ISearchCourseCRUD;
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
@RequestMapping("course")
public class SearchCourseController {


    private ISearchCursoInput searchCursoInput;

    @Autowired
    public SearchCourseController(ISearchCursoInput searchCursoInput) {

        this.searchCursoInput = searchCursoInput;
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> searchCurso(@PathVariable(name = "name") String name) {
        try {
            Curso curso = searchCursoInput.searchCurso(name);
            CourseDTO   courseDTO = CourseDTO.getInstanceDTO(curso.getId(),curso.getName(),
                                                            curso.getLevel(),curso.getDateExpirationInscription());
            return ResponseEntity.ok(courseDTO);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/allcourses")
    public ResponseEntity<?> getAllCursos() {
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

    //THIS isnottt workjinhng!!!!!!!!!!!!"Ñ;####
    @GetMapping(path = "/findByNameContaining/{nameletter}")
    public ResponseEntity<?> findByNameContaining(@PathVariable(name =  "nameletter") String nameletter) {
        try {
            System.out.println(nameletter);
            System.out.println("CURSOS OBTENIDOS:");

            List<Curso> cursos = searchCursoInput.getCursoThatMatchString(nameletter);
            System.out.println("CURSOS OBTENIDOS 2:");
            cursos.stream().forEach(c -> System.out.println(c.getName()));

            List<CourseDTO> cursosDTOs = cursos.stream()
                    .map(curso -> CourseDTO.getInstanceDTO(curso.getId(), curso.getName(), curso.getLevel(), curso.getDateExpirationInscription()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(cursosDTOs);
        }catch(Exception e) {
            //return ResponseEntity.badRequest().body(e.getMessage());
            return ResponseEntity.badRequest().body("Sin resultados");
        }
    }

    @GetMapping(path = "/findByLevel/{level}")
    public ResponseEntity<?> findByLevel(@PathVariable (name =  "level") CursoLevels level) {
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
    public ResponseEntity<?> findByNameAndLevel(@PathVariable(name =  "name") String name ,@PathVariable (name =  "level") CursoLevels level) {
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
    public ResponseEntity<?> findByExpirationDateBetween(@PathVariable(name =  "startdate") LocalDate startdate, @PathVariable(name =  "duedate") LocalDate duedate) {
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
