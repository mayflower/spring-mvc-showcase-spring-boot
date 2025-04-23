package org.springframework.samples.mvc.messageconverters;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageConvertersControllerTests {

    private static String URI = "/messageconverters/{action}";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // MockMvc is already configured via @AutoConfigureMockMvc
    }

    @Test
    public void readString() throws Exception {
        this.mockMvc.perform(post(URI, "string").content("foo".getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string("Read string 'foo'"));
    }

    @Test
    public void writeString() throws Exception {
        this.mockMvc.perform(get(URI, "string"))
                .andExpect(status().isOk())
                .andExpect(content().string("Wrote a string"));
    }

    @Test
    public void readForm() throws Exception {
        this.mockMvc.perform(
                post(URI, "form")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("foo", "bar")
                        .param("fruit", "apple"))
                .andExpect(status().isOk())
                .andExpect(content().string("Read x-www-form-urlencoded: org.springframework.samples.mvc.validation.ValidationController.JavaBean {foo=[bar], fruit=[apple]}"));
    }

    @Test
    public void writeForm() throws Exception {
        this.mockMvc.perform(get(URI, "form"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(content().string("foo=bar&fruit=apple"));
    }

    private static String XML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                    + "<javaBean><foo>bar</foo><fruit>apple</fruit></javaBean>";

    @Test
    public void readXml() throws Exception {
        this.mockMvc.perform(
                post(URI, "xml")
                        .contentType(MediaType.APPLICATION_XML)
                        .content(XML.getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string("Read from XML: org.springframework.samples.mvc.validation.ValidationController.JavaBean {foo=[bar], fruit=[apple]}"));
    }

    @Test
    public void writeXml() throws Exception {
        this.mockMvc.perform(get(URI, "xml").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().xml(XML));
    }

    @Test
    public void readJson() throws Exception {
        this.mockMvc.perform(
                post(URI, "json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"foo\": \"bar\", \"fruit\": \"apple\" }".getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string("Read from JSON: org.springframework.samples.mvc.validation.ValidationController.JavaBean {foo=[bar], fruit=[apple]}"));
    }

    @Test
    public void writeJson() throws Exception {
        this.mockMvc.perform(get(URI, "json").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foo").value("bar"))
                .andExpect(jsonPath("$.fruit").value("apple"));
    }

    @Test
    public void writeJson2() throws Exception {
        this.mockMvc.perform(get(URI, "json").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foo").value("bar"))
                .andExpect(jsonPath("$.fruit").value("apple"));
    }

    private static String ATOM_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<feed xmlns=\"http://www.w3.org/2005/Atom\"><title>My Atom feed</title></feed>";

    @Test
    public void readAtom() throws Exception {
        this.mockMvc.perform(
                post(URI, "atom")
                        .contentType(MediaType.APPLICATION_ATOM_XML)
                        .content(ATOM_XML.getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string("Read My Atom feed"));
    }

    @Test
    public void writeAtom() throws Exception {
        this.mockMvc.perform(get(URI, "atom").accept(MediaType.APPLICATION_ATOM_XML))
                .andExpect(status().isOk())
                .andExpect(content().xml(ATOM_XML));
    }

    @Test
    public void readRss() throws Exception {
        String rss = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <rss version=\"2.0\">"
                + "<channel><title>My RSS feed</title></channel></rss>";

        this.mockMvc.perform(
                post(URI, "rss")
                        .contentType(MediaType.valueOf("application/rss+xml"))
                        .content(rss.getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string("Read My RSS feed"));
    }

    @Test
    public void writeRss() throws Exception {
        String rss = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<rss version=\"2.0\"><channel><title>My RSS feed</title><link>http://localhost:8080/mvc-showcase/rss</link><description>Description</description></channel></rss>";

        this.mockMvc.perform(get(URI, "rss").accept(MediaType.valueOf("application/rss+xml")))
                .andExpect(status().isOk())
                .andExpect(content().xml(rss));
    }
}
