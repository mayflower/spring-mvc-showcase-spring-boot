package org.springframework.samples.mvc.exceptions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void controllerExceptionHandler() throws Exception {
        this.mockMvc.perform(get("/spring-mvc-showcase/exception"))
                .andExpect(status().isOk())
                .andExpect(content().string("IllegalStateException handled!"));
    }

    @Test
    public void globalExceptionHandler() throws Exception {
        this.mockMvc.perform(get("/spring-mvc-showcase/global-exception"))
                .andExpect(status().isOk())
                .andExpect(content().string("Handled BusinessException"));
    }
}
