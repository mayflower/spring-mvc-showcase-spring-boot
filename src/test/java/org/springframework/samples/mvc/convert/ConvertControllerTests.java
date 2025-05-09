package org.springframework.samples.mvc.convert;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ConvertController.class)
@Import(ConvertControllerTestConfig.class)
public class ConvertControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        // Configuration is now handled by ConvertControllerTestConfig
    }

    @Test
    public void primitive() throws Exception {
        this.mockMvc.perform(get("/convert/primitive").param("value", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted primitive 3"));
    }

    @Test
    public void date() throws Exception {
        String timezone = getTimezone(2010, 7, 4);
        this.mockMvc.perform(get("/convert/date/2010-07-04"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted date Sun Jul 04 00:00:00 " + timezone + " 2010"));
    }

    @Test
    public void collection() throws Exception {
        this.mockMvc.perform(get("/convert/collection?values=1&values=2&values=3&values=4&values=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted collection [1, 2, 3, 4, 5]"));
    }

    @Test
    public void collection2() throws Exception {
        this.mockMvc.perform(get("/convert/collection?values=1,2,3,4,5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted collection [1, 2, 3, 4, 5]"));
    }

    @Test
    public void formattedCollection() throws Exception {
        String timezone2010 = getTimezone(2010, 7, 4);
        String timezone2011 = getTimezone(2011, 7, 4);
        this.mockMvc.perform(get("/convert/formattedCollection?values=2010-07-04,2011-07-04"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Converted formatted collection [Sun Jul 04 00:00:00 "
                                + timezone2010 + " 2010, Mon Jul 04 00:00:00 " + timezone2011 + " 2011]"));
    }

    @Test
    public void valueOf() throws Exception {
        this.mockMvc.perform(get("/convert/value?value=123456789"))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith(
                        "Converted value object org.springframework.samples.mvc.convert.SocialSecurityNumber")));
    }

    @Test
    public void custom() throws Exception {
        this.mockMvc.perform(get("/convert/custom?value=123-45-6789"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted '123456789' with a custom converter"));
    }

    @Test
    public void beanPrimitive() throws Exception {
        this.mockMvc.perform(get("/convert/bean?primitive=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted org.springframework.samples.mvc.validation.ValidationController.JavaBean primitive=3"));
    }

    @Test
    public void beanDate() throws Exception {
        String timezone = getTimezone(2010, 7, 4);
        this.mockMvc.perform(get("/convert/bean?date=2010-07-04"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted org.springframework.samples.mvc.validation.ValidationController.JavaBean date=Sun Jul 04 00:00:00 " + timezone + " 2010"));
    }

    @Test
    public void beanMasked() throws Exception {
        this.mockMvc.perform(get("/convert/bean?masked=(205) 333-3333"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted org.springframework.samples.mvc.validation.ValidationController.JavaBean masked=2053333333"));
    }

    @Test
    public void beanCollection() throws Exception {
        this.mockMvc.perform(get("/convert/bean?list[0]=1&list[1]=2&list[2]=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted org.springframework.samples.mvc.validation.ValidationController.JavaBean list=[1, 2, 3]"));
    }

    @Test
    public void beanFormattedCollection() throws Exception {
        String timezone2010 = getTimezone(2010, 7, 4);
        String timezone2011 = getTimezone(2011, 7, 4);
        this.mockMvc.perform(get("/convert/bean?formattedList[0]=2010-07-04&formattedList[1]=2011-07-04"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Converted org.springframework.samples.mvc.validation.ValidationController.JavaBean formattedList=[Sun Jul 04 00:00:00 " + timezone2010
                                + " 2010, Mon Jul 04 00:00:00 " + timezone2011 + " 2011]"));
    }

    @Test
    public void beanMap() throws Exception {
        this.mockMvc.perform(get("/convert/bean?map[0]=apple&map[1]=pear"))
                .andExpect(status().isOk())
                .andExpect(content().string("Converted org.springframework.samples.mvc.validation.ValidationController.JavaBean map={0=apple, 1=pear}"));
    }

    @Test
    public void beanNested() throws Exception {
        this.mockMvc.perform(get("/convert/bean?nested.foo=bar&nested.list[0].foo=baz&nested.map[key].list[0].foo=bip"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Converted org.springframework.samples.mvc.validation.ValidationController.JavaBean nested=NestedBean foo=bar list=[NestedBean foo=baz] map={key=NestedBean list=[NestedBean foo=bip]}"));
    }

    private String getTimezone(int year, int month, int day)
    {
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
