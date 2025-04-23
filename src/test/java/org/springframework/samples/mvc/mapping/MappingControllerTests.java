package org.springframework.samples.mvc.mapping;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MappingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // MockMvc is now autowired with all necessary configuration
    }

    @Test
    public void byPath() throws Exception {
        this.mockMvc.perform(get("/mapping/path"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mapped by path!"));
    }

    @Test
    public void byPathPattern() throws Exception {
        this.mockMvc.perform(get("/mapping/path/wildcard"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mapped by path pattern ('/mapping/path/wildcard')"));
    }

    @Test
    public void byMethod() throws Exception {
        this.mockMvc.perform(get("/mapping/method"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mapped by path + method"));
    }

    @Test
    public void byParameter() throws Exception {
        this.mockMvc.perform(get("/mapping/parameter?foo=bar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mapped by path + method + presence of query parameter!"));
    }

    @Test
    public void byNotParameter() throws Exception {
        this.mockMvc.perform(get("/mapping/parameter"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mapped by path + method + not presence of query parameter!"));
    }

    @Test
    public void byHeader() throws Exception {
        this.mockMvc.perform(get("/mapping/header").header("FooHeader", "foo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mapped by path + method + presence of header!"));
    }

    @Test
    public void byHeaderNegation() throws Exception {
        this.mockMvc.perform(get("/mapping/header"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mapped by path + method + absence of header!"));
    }

    @Test
    public void byConsumes() throws Exception {
        this.mockMvc.perform(
                post("/mapping/consumes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"foo\": \"bar\", \"fruit\": \"apple\" }".getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("Mapped by path + method + consumable media type (javaBean")));
    }

    @Test
    public void byProducesAcceptJson() throws Exception {
        this.mockMvc.perform(get("/mapping/produces").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foo").value("bar"))
                .andExpect(jsonPath("$.fruit").value("apple"));
    }

    @Test
    public void byProducesAcceptXml() throws Exception {
        this.mockMvc.perform(get("/mapping/produces").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("/javaBean/foo").string("bar"))
                .andExpect(xpath("/javaBean/fruit").string("apple"));
    }

    @Test
    public void byProducesJsonExtension() throws Exception {
        this.mockMvc.perform(get("/mapping/produces.json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foo").value("bar"))
                .andExpect(jsonPath("$.fruit").value("apple"));
    }

    @Test
    public void byProducesXmlExtension() throws Exception {
        this.mockMvc.perform(get("/mapping/produces.xml"))
                .andExpect(status().isOk())
                .andExpect(xpath("/javaBean/foo").string("bar"))
                .andExpect(xpath("/javaBean/fruit").string("apple"));
    }
}
