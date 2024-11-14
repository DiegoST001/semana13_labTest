package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exception.VisitNotFoundException;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VisitServiceImpl implements VisitService {

    private VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit create(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit update(Visit visit) {
        // Se puede manejar la actualización si la visita ya existe, o si no se encuentra, crearla
        Optional<Visit> existingVisit = visitRepository.findById(visit.getId());
        if (!existingVisit.isPresent()) {
            log.info("Visit not found for update, creating new visit.");
        }
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Long id) throws VisitNotFoundException {
        Visit visit = findById(id);
        visitRepository.delete(visit);
    }

    @Override
    public Visit findById(Long id) throws VisitNotFoundException {
        Optional<Visit> visit = visitRepository.findById(id);
        if (!visit.isPresent()) {
            throw new VisitNotFoundException("Visit not found for id: " + id);
        }
        return visit.get();
    }

    @Override
    public List<Visit> findByDescription(String description) {
        List<Visit> visits = visitRepository.findByDescription(description);
        visits.forEach(visit -> log.info("Found visit: " + visit));
        return visits;
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }
}
