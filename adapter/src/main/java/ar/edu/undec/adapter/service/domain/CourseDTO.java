package ar.edu.undec.adapter.service.domain;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = )
public class CourseDTO {
    @JsonProperty("id");
    private UUID id;
    @JsonProperty("inscription_deadline");
    private LocalDate inscriptionDeadline;

    @JsonProperty("level");
    private CursoLevels level;


    public static Course
}
