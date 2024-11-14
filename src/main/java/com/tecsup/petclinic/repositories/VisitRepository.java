package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    // Buscar visitas por nombre (asegúrate de que exista un campo `name` en `Visit`)
    List<Visit> findByName(String name);

    // `findById` ya existe en JpaRepository; no es necesario redefinirlo

    // `findAll` también existe en JpaRepository, así que no es necesario sobreescribirlo
}
