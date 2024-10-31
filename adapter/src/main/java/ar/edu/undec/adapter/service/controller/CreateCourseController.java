package ar.edu.undec.adapter.service.controller;

import ar.edu.undec.adapter.service.domain.CourseDTO;
import curso.input.ICreateCursoInput;
import curso.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CreateCourseController {

    private final ICreateCursoInput createCursoInput;

    @Autowired
    public CreateCourseController(ICreateCursoInput createCursoInput) {
        this.createCursoInput = createCursoInput;
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO) {
        Curso courseCreated = createCursoInput.createCurso(
                courseDTO.getId(),
                courseDTO.getName(),
                courseDTO.getLevel(),
                courseDTO.getDateExpirationInscription()
        );

        if(courseCreated.getName() != null ) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
