package org.springframework.samples.mvc.data;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.samples.mvc.data.custom.CustomArgumentController;
import org.springframework.samples.mvc.data.custom.CustomArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomArgumentController.class)
@Import(CustomArgumentResolver.class)
public class CustomArgumentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void param() throws Exception {
        this.mockMvc.perform(get("/data/custom"))
                .andExpect(content().string("Got 'foo' request attribute value 'bar'"));
    }
}
