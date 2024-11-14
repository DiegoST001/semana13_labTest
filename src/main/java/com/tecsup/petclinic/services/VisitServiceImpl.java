package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Visit create(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit update(Visit visit) {
        // Verificamos si la visita con el ID proporcionado existe
        Optional<Visit> existingVisit = visitRepository.findById(visit.getId());
        if (existingVisit.isPresent()) {
            // Si existe, la actualizamos y la guardamos
            return visitRepository.save(visit);
        } else {
            // Si no existe, lanzamos una excepción o retornamos un valor adecuado
            throw new RuntimeException("Visit with ID " + visit.getId() + " not found");
        }
    }

    @Override
    public void delete(Long id) {
        // Verificamos si existe una visita con ese ID
        if (visitRepository.existsById(id)) {
            // Si existe, la eliminamos
            visitRepository.deleteById(id);
        } else {
            // Si no existe, lanzamos una excepción o mostramos un mensaje
            throw new RuntimeException("Visit with ID " + id + " not found");
        }
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Visit> findByName(String name) {
        return visitRepository.findByName(name);
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }
}
