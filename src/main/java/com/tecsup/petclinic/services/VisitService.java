package com.tecsup.petclinic.services;

import java.util.List;
import com.tecsup.petclinic.entities.Visit;

public interface VisitService {

    /**
     * Crea un nuevo registro de visita.
     *
     * @param visit
     * @return el objeto Visit creado
     */
    Visit create(Visit visit);

    /**
     * Actualiza un registro de visita existente.
     *
     * @param visit
     * @return el objeto Visit actualizado
     */
    Visit update(Visit visit);

    /**
     * Elimina un registro de visita por ID.
     *
     * @param id ID del registro de visita a eliminar
     */
    void delete(Long id);

    /**
     * Encuentra un registro de visita por ID.
     *
     * @param id ID del registro de visita a encontrar
     * @return el objeto Visit encontrado o null si no se encuentra
     */
    Visit findById(Long id);

    /**
     * Encuentra registros de visita por nombre.
     *
     * @param name Nombre para buscar registros de visita
     * @return una lista de objetos Visit que coinciden con el nombre dado
     */
    List<Visit> findByName(String name);

    /**
     * Obtiene todos los registros de visita.
     *
     * @return una lista de todos los objetos Visit
     */
    List<Visit> findAll();
}
