package ar.edu.undec.adapter.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import curso.modelo.CursoLevels;

import java.time.LocalDate;
import java.util.UUID;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("inscription_deadline")
    private LocalDate dateExpirationInscription;

    @JsonProperty("level")
    private CursoLevels level;

    @JsonProperty("course_name")
    private String courseName;

    public CourseDTO() {}

    public CourseDTO(UUID id,  String courseName,  CursoLevels level,LocalDate dateExpirationInscription) {
        this.id = id;
        this.dateExpirationInscription = dateExpirationInscription;
        this.level = level;
        this.courseName = courseName;
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
        return courseName;
    }

    public void setName(String courseName) {
        this.courseName = courseName;
    }

}
