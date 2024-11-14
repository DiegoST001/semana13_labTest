package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity(name = "visit")
@Data
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = true)
    private Pet pet;

    @Column(nullable = false)
    private LocalDate visitDate;

    @Column(length = 255)
    private String description;

}
