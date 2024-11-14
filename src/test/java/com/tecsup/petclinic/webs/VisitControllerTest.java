package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.domain.VisitTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper om = new ObjectMapper();

    @Test
    public void testFindAllVisits() throws Exception {
        this.mockMvc.perform(get("/visits"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testFindVisitById() throws Exception {
        Long VISIT_ID = 1L;
        Long PET_ID = 1L;
        LocalDate VISIT_DATE = LocalDate.of(2023, 11, 1);
        String DESCRIPTION = "Routine check-up";

        mockMvc.perform(get("/visits/" + VISIT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(VISIT_ID.intValue())))
                .andExpect(jsonPath("$.petId", is(PET_ID.intValue())))
                .andExpect(jsonPath("$.visitDate", is(VISIT_DATE.toString()))) // Formato yyyy-MM-dd
                .andExpect(jsonPath("$.description", is(DESCRIPTION)));
    }


    @Test
    public void testDeleteVisit() throws Exception {
        Long VISIT_ID = 1L;

        mockMvc.perform(delete("/visits/" + VISIT_ID))
                .andExpect(status().isOk());
    }

}
