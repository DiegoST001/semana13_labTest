package com.tecsup.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisitTO {
    private Long id;

    private String name;

    private String apellido;

    private String direccion;

    public void setVisitDate(LocalDate visitDate) {
    }

    public void setDescription(String description) {
    }

    public void setPetId(int petId) {
    }
}
