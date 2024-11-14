package com.tecsup.petclinic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitTO {
    private Long id;
    private Long petId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate visitDate; // Utiliza LocalDate para manejar la fecha de la visita

    private String description;
}
