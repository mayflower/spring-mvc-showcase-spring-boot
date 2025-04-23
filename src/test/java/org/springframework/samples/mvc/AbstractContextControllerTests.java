package org.springframework.samples.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class AbstractContextControllerTests {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    protected void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .defaultRequest(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/").contextPath("/spring-mvc-showcase"))
                .build();
    }
}
