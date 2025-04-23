package org.springframework.samples.mvc.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ValidationController.class)
public class ValidationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validateSuccess() throws Exception {
        this.mockMvc.perform(get("/validate?number=3&date=2029-07-04"))
                .andExpect(status().isOk())
                .andExpect(content().string("No errors"));
    }

    @Test
    public void validateErrors() throws Exception {
        this.mockMvc.perform(get("/validate?number=3&date=2010-07-01"))
                .andExpect(status().isOk())
                .andExpect(content().string("Object has validation errors"));
    }

}
