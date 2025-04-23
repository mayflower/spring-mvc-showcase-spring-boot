package org.springframework.samples.mvc.views;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ViewsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // MockMvc is now autowired with all the necessary configuration
    }

    @Test
    public void htmlView() throws Exception {
        this.mockMvc.perform(get("/views/html"))
                .andExpect(status().isOk())
                .andExpect(view().name(containsString("views/html")))
                .andExpect(model().attribute("foo", "bar"))
                .andExpect(model().attribute("fruit", "apple"))
                .andExpect(model().size(2));
    }

    @Test
    public void viewName() throws Exception {
        this.mockMvc.perform(get("/views/viewName"))
                .andExpect(status().isOk())
                .andExpect(view().name(containsString("views/viewName")))
                .andExpect(model().attribute("foo", "bar"))
                .andExpect(model().attribute("fruit", "apple"))
                .andExpect(model().size(2));
    }

    @Test
    public void uriTemplate() throws Exception {
        this.mockMvc.perform(get("/views/pathVariables/bar/apple"))
                .andExpect(status().isOk())
                .andExpect(view().name(containsString("views/html")));
    }
}
