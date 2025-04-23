package org.springframework.samples.mvc.fileupload;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void readString() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "orig", null, "bar".getBytes());

        mockMvc.perform(multipart("/fileupload").file(file))
                .andExpect(model().attribute("message", "File 'orig' uploaded successfully"));
    }
}
