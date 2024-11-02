package ar.edu.undec.adapter.data.model;

import curso.input.ICreateCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "cursos")
public class CourseData {

    @Id
    @Column(name="idcurso")
    UUID id;

    @Column(name="name")
    String name;
    @Column(name="level")
    CursoLevels level;
    @Column(name="dateexpirationinscriptioncourse")
    LocalDate dateExpirationInscription;

    public CourseData() {

    }
    public CourseData(UUID id, String name, CursoLevels level, LocalDate dateExpirationInscription) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.dateExpirationInscription = dateExpirationInscription;
    }

    public static CourseData toData(Curso coreCourse){
        return new CourseData(coreCourse.getId(),coreCourse.getName()
                ,coreCourse.getLevel(),
                coreCourse.getDateExpirationInscription());
    }

    public Curso toDomain() {
        return  Curso.getInstance(this.id, this.name, this.level, this.dateExpirationInscription);
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

    public CursoLevels getLevel() {
        return level;
    }

    public void setLevel(CursoLevels level) {
        this.level = level;
    }

    public LocalDate getDateExpirationInscription() {
        return dateExpirationInscription;
    }

    public void setDateExpirationInscription(LocalDate dateExpirationInscription) {
        this.dateExpirationInscription = dateExpirationInscription;
    }

}
