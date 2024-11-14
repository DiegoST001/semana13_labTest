package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.mapper.VisitMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecsup.petclinic.domain.VisitTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exception.VisitNotFoundException;
import com.tecsup.petclinic.services.VisitService;

import java.util.List;

/**
 * Controlador para manejar visitas (Visit).
 *
 * @author jgomezm
 *
 */
@RestController
@Slf4j
public class VisitController {

    private final VisitService visitService;
    private final VisitMapper visitMapper;

    /**
     * Constructor para inicializar el servicio y el mapper.
     *
     * @param visitService
     * @param visitMapper
     */
    public VisitController(VisitService visitService, VisitMapper visitMapper) {
        this.visitService = visitService;
        this.visitMapper = visitMapper;
    }

    /**
     * Obtener todas las visitas.
     *
     * @return ResponseEntity con la lista de visitas.
     */
    @GetMapping(value = "/visits")
    public ResponseEntity<List<VisitTO>> findAllVisits() {
        List<Visit> visits = visitService.findAll();
        log.info("visits: {}", visits);

        List<VisitTO> visitsTO = this.visitMapper.toVisitTOList(visits);
        visitsTO.forEach(item -> log.info("VisitTO >> {}", item));

        return ResponseEntity.ok(visitsTO);
    }

    /**
     * Crear una nueva visita.
     *
     * @param visitTO DTO de la visita a crear.
     * @return ResponseEntity con la visita creada.
     */
    @PostMapping(value = "/visits")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VisitTO> create(@RequestBody VisitTO visitTO) {
        Visit newVisit = this.visitMapper.toVisit(visitTO);
        VisitTO newVisitTO = this.visitMapper.toVisitTO(visitService.create(newVisit));

        return ResponseEntity.status(HttpStatus.CREATED).body(newVisitTO);
    }

    /**
     * Obtener una visita por su ID.
     *
     * @param id ID de la visita a obtener.
     * @return ResponseEntity con la visita encontrada.
     * @throws VisitNotFoundException Si no se encuentra la visita.
     */
    @GetMapping(value = "/visits/{id}")
    public ResponseEntity<VisitTO> findById(@PathVariable Long id) {
        VisitTO visitTO = null;

        try {
            Visit visit = visitService.findById(id);
            visitTO = this.visitMapper.toVisitTO(visit);

        } catch (VisitNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(visitTO);
    }

    /**
     * Actualizar una visita existente.
     *
     * @param visitTO DTO de la visita a actualizar.
     * @param id ID de la visita a actualizar.
     * @return ResponseEntity con la visita actualizada.
     * @throws VisitNotFoundException Si no se encuentra la visita.
     */
    @PutMapping(value = "/visits/{id}")
    public ResponseEntity<VisitTO> update(@RequestBody VisitTO visitTO, @PathVariable Long id) {
        VisitTO updatedVisitTO = null;

        try {
            Visit updateVisit = visitService.findById(id);
            updateVisit.setVisitDate(visitTO.getVisitDate());
            updateVisit.setDescription(visitTO.getDescription());

            visitService.update(updateVisit);

            updatedVisitTO = this.visitMapper.toVisitTO(updateVisit);

        } catch (VisitNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedVisitTO);
    }

    /**
     * Eliminar una visita por su ID.
     *
     * @param id ID de la visita a eliminar.
     * @return ResponseEntity con el mensaje de eliminación.
     */
    @DeleteMapping(value = "/visits/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            visitService.delete(id);
            return ResponseEntity.ok("Delete ID: " + id);
        } catch (VisitNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
