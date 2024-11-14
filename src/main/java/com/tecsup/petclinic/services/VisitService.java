package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exception.VisitNotFoundException;

import java.util.List;

public interface VisitService {

    Visit create(Visit visit);

    Visit update(Visit visit);


    void delete(Long id) throws VisitNotFoundException;

    Visit findById(Long id) throws VisitNotFoundException;

    List<Visit> findByDescription(String description);

    List<Visit> findAll();
}
