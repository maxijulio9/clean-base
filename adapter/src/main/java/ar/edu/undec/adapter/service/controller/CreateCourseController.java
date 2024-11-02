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

    private  ICreateCursoInput createCursoInput;

    @Autowired
    public CreateCourseController(ICreateCursoInput createCursoInput) {
        this.createCursoInput = createCursoInput;
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO) {


        try {
            System.out.println(courseDTO.getId()+""+
                    courseDTO.getName()+""+
                    courseDTO.getLevel()+""+
                    courseDTO.getDateExpirationInscription());
            Curso courseCreated = createCursoInput.createCurso(
                    courseDTO.getId(),
                    courseDTO.getName(),
                    courseDTO.getLevel(),
                    courseDTO.getDateExpirationInscription()
            );

            return ResponseEntity.ok().body("Curso creado exitosamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
