package ar.edu.undec.adapter.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import curso.modelo.Curso;
import curso.modelo.CursoLevels;

import java.time.LocalDate;
import java.util.UUID;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {

    @JsonProperty("idcurso")
    private UUID id;

    @JsonProperty("dateexpirationinscriptioncourse")
    private LocalDate dateExpirationInscription;

    @JsonProperty("level")
    private CursoLevels level;

    @JsonProperty("name")
    private String name;

    public CourseDTO() {}

    private CourseDTO(UUID id,  String courseName,  CursoLevels level,LocalDate dateExpirationInscription) {
        this.id = id;
        this.dateExpirationInscription = dateExpirationInscription;
        this.level = level;
        this.name = courseName;
    }
    public static CourseDTO getInstanceDTO(UUID id,String courseName,  CursoLevels level,LocalDate dateExpirationInscription){
        return new CourseDTO(id,courseName,level,dateExpirationInscription);
    }

    public static Curso toDomain(CourseDTO course){
        return Curso.getInstance(course.getName(),course.getLevel(),course.getDateExpirationInscription());//etc
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateExpirationInscription() {
        return dateExpirationInscription;
    }

    public void setDateExpirationInscription(LocalDate inscriptionDeadline) {
        this.dateExpirationInscription = inscriptionDeadline;
    }

    public CursoLevels getLevel() {
        return level;
    }

    public void setLevel(CursoLevels level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String courseName) {
        this.name = courseName;
    }

}