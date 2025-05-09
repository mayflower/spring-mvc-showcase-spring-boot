package org.springframework.samples.mvc.form;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(FormController.class)
public class FormControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void submitSuccess() throws Exception {
        String timezone = getTimezone(1941, 12, 16);
        this.mockMvc.perform(
                post("/form")
                        .param("name", "Joe")
                        .param("age", "56")
                        .param("birthDate", "1941-12-16")
                        .param("phone", "(347) 888-1234")
                        .param("currency", "$123.33")
                        .param("percent", "89%")
                        .param("inquiry", "comment")
                        .param("inquiryDetails", "what is?")
                        .param("additionalInfo[mvc]", "true")
                        .param("_additionalInfo[mvc]", "on")
                        .param("additionalInfo[java]", "true")
                        .param("_additionalInfo[java]", "on")
                        .param("subscribeNewsletter", "false"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/form"))
                .andExpect(flash().attribute("message",
                        "Form submitted successfully.  Bound properties name='Joe', age=56, "
                                + "birthDate=Tue Dec 16 00:00:00 " + timezone + " 1941, phone='(347) 888-1234', "
                                + "currency=123.33, percent=0.89, inquiry=comment, inquiryDetails='what is?',"
                                + " subscribeNewsletter=false, additionalInfo={java=true, mvc=true}"));
    }

    @Test
    public void submitSuccessAjax() throws Exception {
        String timezone = getTimezone(1941, 12, 16);
        this.mockMvc.perform(
                post("/form")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .param("name", "Joe")
                        .param("age", "56")
                        .param("birthDate", "1941-12-16")
                        .param("phone", "(347) 888-1234")
                        .param("currency", "$123.33")
                        .param("percent", "89%")
                        .param("inquiry", "comment")
                        .param("inquiryDetails", "what is?")
                        .param("additionalInfo[mvc]", "true")
                        .param("_additionalInfo[mvc]", "on")
                        .param("additionalInfo[java]", "true")
                        .param("_additionalInfo[java]", "on")
                        .param("subscribeNewsletter", "false"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attribute("message",
                        "Form submitted successfully.  Bound properties name='Joe', age=56, "
                                + "birthDate=Tue Dec 16 00:00:00 " + timezone + " 1941, phone='(347) 888-1234', "
                                + "currency=123.33, percent=0.89, inquiry=comment, inquiryDetails='what is?',"
                                + " subscribeNewsletter=false, additionalInfo={java=true, mvc=true}"));
    }

    @Test
    public void submitError() throws Exception {
        this.mockMvc.perform(
                post("/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().errorCount(2))
                .andExpect(model().attributeHasFieldErrors("formBean", "name", "age"));
    }

    private String getTimezone(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date date = calendar.getTime();
        TimeZone timezone = TimeZone.getDefault();
        boolean inDaylight = timezone.inDaylightTime(date);
        return timezone.getDisplayName(inDaylight, TimeZone.SHORT);
    }
}
