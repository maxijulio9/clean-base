package curso.modelo;

import curso.exception.ExceptionCursoWithAInvalidLevel;
import curso.exception.ExceptionCursoWithAInscriptionDateInvalid;
import curso.exception.ExceptionCursoWithMissingAttributes;
import curso.input.ICreateCursoInput;
import curso.input.IValidationAttributeCursoInput;

import java.time.LocalDate;
import java.util.UUID;

public class Curso {
    UUID id;
    String name;
 //   Integer studentQuantity;
   // Integer hoursQuantity;
    CursoLevels level;
    //String teacherAssigned;
    LocalDate dateExpirationInscription;

  //  ICreateCursoInput cursoCreateInput;

    private Curso(String name,  CursoLevels levels, LocalDate dateCloseInscription ){
        this.id = UUID.randomUUID();
        this.name = name;
        //this.studentQuantity = studentQuantity;
        //this.hoursQuantity = hoursQuantity;
        this.level = levels;
        //this.teacherAssigned = teacherAssigned;
        this.dateExpirationInscription = dateCloseInscription;
    }

    public static Curso getInstance(String name, CursoLevels level, LocalDate dateExpirationInscription) {

        Util.validatedateExpirationInscription(dateExpirationInscription,"El curso que intentas registrar tiene fecha de inscripción inválida." );
        Util.validateEmptyString(name, "El nombre del curso no puede estar vacío.");
        Util.validateLevel(level, "El curso que intentas registrar tiene un nivel inválido.");
        return new Curso(name,  level,dateExpirationInscription);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateExpirationInscription() {
        return dateExpirationInscription;
    }

    public void setDateExpirationInscription(LocalDate dateExpirationInscription) {
        this.dateExpirationInscription = dateExpirationInscription;
    }

    public CursoLevels getLevel() {
        return level;
    }

    public void setLevel(CursoLevels level) {
        this.level = level;
    }
}
//
