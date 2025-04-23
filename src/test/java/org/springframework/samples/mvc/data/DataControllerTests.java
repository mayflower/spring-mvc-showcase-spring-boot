package org.springframework.samples.mvc.data;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
public class DataControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        // MockMvc is now autowired with all necessary configuration
    }

    @Test
    public void param() throws Exception {
        this.mockMvc.perform(get("/data/param?foo=bar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Obtained 'foo' query parameter value 'bar'"));
    }

    @Test
    public void group() throws Exception {
        this.mockMvc.perform(get("/data/group?param1=foo&param2=bar&param3=baz"))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith(
                        "Obtained parameter group org.springframework.samples.mvc.data.JavaBean@")));
    }

    @Test
    public void pathVar() throws Exception {
        this.mockMvc.perform(get("/data/path/foo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Obtained 'var' path variable value 'foo'"));
    }

    @Test
    public void matrixVar() throws Exception {
        this.mockMvc.perform(get("/data/matrixvars;foo=bar/simple"))
                .andExpect(status().isOk())
                .andExpect(content().string("Obtained matrix variable 'foo=bar' from path segment 'matrixvars'"));
    }

    @Test
    public void matrixVarMultiple() throws Exception {
        this.mockMvc.perform(get("/data/matrixvars;foo=bar1/multiple;foo=bar2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Obtained matrix variable foo=bar1 from path segment 'matrixvars' and variable 'foo=bar2 from path segment 'multiple'"));
    }

    @Test
    public void header() throws Exception {
        this.mockMvc.perform(get("/data/header").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("Obtained 'Accept' header '*/*'"));
    }

    @Test
    public void requestBody() throws Exception {
        this.mockMvc.perform(
                post("/data/body")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("foo".getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string("Posted request body 'foo'"));
    }

    @Test
    public void requestBodyAndHeaders() throws Exception {
        this.mockMvc.perform(
                post("/data/entity")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("foo".getBytes()))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Posted request body 'foo'; headers = {Content-Type=[text/plain], Content-Length=[3]}"));
    }

    // Fixed the nested class declaration
    public static class JavaBean {
        private String param1;
        private String param2;
        private String param3;

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }

        public String getParam2() {
            return param2;
        }

        public void setParam2(String param2) {
            this.param2 = param2;
        }

        public String getParam3() {
            return param3;
        }

        public void setParam3(String param3) {
            this.param3 = param3;
        }
    }
}