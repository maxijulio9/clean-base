package curso.modelo;

import curso.exception.ExceptionCursoWithASuscriptionDateInvalid;

import javax.swing.*;
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
        if (name ==null
                || level  == null
                || dateExpirationInscription ==null){
            return null;
        }

        if(dateExpirationInscription.isBefore(LocalDate.now())){
            throw new ExceptionCursoWithASuscriptionDateInvalid("El curso que intentas registrar tiene fecha de inscripción inválida.");
        }

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
}
//
