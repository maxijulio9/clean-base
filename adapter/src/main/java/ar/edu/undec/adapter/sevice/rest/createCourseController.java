package ar.edu.undec.adapter.sevice.rest;


import ar.edu.undec.adapter.service.domain.CourseDTO;
import curso.input.ICreateCursoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@RestController
@RequestMapping()
public class CreateCourseController {

    private ICreateCursoInput createCursoInput;
    //interaz

    @Autowired
    public CreateCourseController(ICreateCursoInput createCourseInput) {
        createCourseInput = createCourseInput;

    }


    @PostMapping
    @ResponseBody("/course")
    public ResponseEntity<?> createCourse(@ResponseBody CourseDTO courseDTO)){
        if(this.createCursoInput.createCurso(
            courseDTO.getName(),
            courseDTO.getInscriptionDeadLine(),
            courseDTO.getLevel())){

            return ResponseEntity.ok().build();

        }
        return ResponseEntity.badRequest().build();

    }
}
