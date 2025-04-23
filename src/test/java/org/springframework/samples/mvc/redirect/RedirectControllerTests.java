package org.springframework.samples.mvc.redirect;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RedirectController.class)
@Import(DefaultFormattingConversionService.class)
public class RedirectControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DefaultFormattingConversionService conversionService;

    @BeforeEach
    public void setup() throws Exception {
        // No need to set up MockMvc manually as it's autowired
    }

    @Test
    public void uriTemplate() throws Exception {
        this.mockMvc.perform(get("/redirect/uriTemplate"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/redirect/a123?date=12%2F31%2F11"));
    }

    @Test
    public void uriComponentsBuilder() throws Exception {
        this.mockMvc.perform(get("/redirect/uriComponentsBuilder"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/redirect/a123?date=12/31/11"));
    }
}
