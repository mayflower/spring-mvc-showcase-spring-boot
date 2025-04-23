package org.springframework.samples.mvc.data;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.samples.mvc.data.standard.StandardArgumentsController;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StandardArgumentsController.class)
public class StandardArgumentsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        // MockMvc is now autowired with WebMvcTest
    }

    @Test
    public void request() throws Exception {
        this.mockMvc.perform(get("/data/standard/request"))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith(
                        "request = org.springframework.mock.web.MockHttpServletRequest@")));
    }

    @Test
    public void requestReader() throws Exception {
        this.mockMvc.perform(
                post("/data/standard/request/reader")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("foo".getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string("Read char request body = foo"));
    }

    @Test
    public void requestIs() throws Exception {
        this.mockMvc.perform(
                post("/data/standard/request/is")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("foo".getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string("Read binary request body = foo"));
    }

    @Test
    public void response() throws Exception {
        this.mockMvc.perform(get("/data/standard/response"))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith(
                        "response = org.springframework.mock.web.MockHttpServletResponse@")));
    }

    @Test
    public void writer() throws Exception {
        this.mockMvc.perform(get("/data/standard/response/writer"))
                .andExpect(status().isOk())
                .andExpect(content().string("Wrote char response using Writer"));
    }

    @Test
    public void os() throws Exception {
        this.mockMvc.perform(get("/data/standard/response/os"))
                .andExpect(status().isOk())
                .andExpect(content().string("Wrote binary response using OutputStream"));
    }

    @Test
    public void session() throws Exception {
        this.mockMvc.perform(get("/data/standard/session"))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith(
                        "session=org.springframework.mock.web.MockHttpSession@")));
    }
}
