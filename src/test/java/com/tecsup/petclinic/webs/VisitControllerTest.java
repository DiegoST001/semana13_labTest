package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.entities.Visit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class VisitControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindVisit() throws Exception {
        Long id_visit = 1L;

        mockMvc.perform(get("/visits/{id}", id_visit))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(id_visit.intValue())))
                .andExpect(jsonPath("$.name", is("Carlos")))
                .andExpect(jsonPath("$.apellido", is("Pérez")))
                .andExpect(jsonPath("$.direccion", is("Av. Siempre Viva 123")));
    }

    @Test
    public void testCreateVisit() throws Exception {
        Visit newVisit = new Visit();
        newVisit.setName("Carlos");
        newVisit.setApellido("Pérez");
        newVisit.setDireccion("Av. Siempre Viva 123");

        mockMvc.perform(post("/visits")
                        .content(om.writeValueAsString(newVisit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Carlos")))
                .andExpect(jsonPath("$.apellido", is("Pérez")))
                .andExpect(jsonPath("$.direccion", is("Av. Siempre Viva 123")));
    }

    @Test
    public void testUpdateVisit() throws Exception {
        Long idToUpdate = 3L;

        mockMvc.perform(get("/visits/{id}", idToUpdate))
                .andExpect(status().isOk())
                .andDo(result -> {
                    Visit updatedVisit = new Visit();
                    updatedVisit.setId(idToUpdate);
                    updatedVisit.setName("Luis");
                    updatedVisit.setApellido("Mendoza");
                    updatedVisit.setDireccion("Jr. Los Cedros 789");

                    mockMvc.perform(put("/visits/{id}", idToUpdate)
                                    .content(om.writeValueAsString(updatedVisit))
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name", is("Luis")))
                            .andExpect(jsonPath("$.apellido", is("Mendoza")))
                            .andExpect(jsonPath("$.direccion", is("Jr. Los Cedros 789")));
                });
    }

    @Test
    public void testDeleteVisit() throws Exception {
        Long idToDelete = 8L;

        // Intenta obtener el recurso para verificar su existencia
        var getResult = mockMvc.perform(get("/visits/{id}", idToDelete))
                .andReturn();

        // Si el recurso existe (estado 200 OK), realiza el borrado
        if (getResult.getResponse().getStatus() == 200) {
            // Realiza la eliminación
            mockMvc.perform(delete("/visits/{id}", idToDelete))
                    .andExpect(status().isNoContent());

            // Verifica que el recurso ya no esté disponible
            mockMvc.perform(get("/visits/{id}", idToDelete))
                    .andExpect(status().isNotFound());
        } else {
            // Si el recurso no existe, intenta el DELETE y espera un 404 Not Found
            mockMvc.perform(delete("/visits/{id}", idToDelete))
                    .andExpect(status().isNotFound());
        }
    }


}
