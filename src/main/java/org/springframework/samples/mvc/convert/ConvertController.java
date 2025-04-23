package org.springframework.samples.mvc.convert;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert")
public class ConvertController {

    @GetMapping("primitive")
    public String primitive(@RequestParam Integer value) {
        return "Converted primitive " + value;
    }

    @GetMapping("date/{value}")
    public String date(@PathVariable @DateTimeFormat(iso = ISO.DATE) Date value) {
        return "Converted date " + value;
    }

    @GetMapping("collection")
    public String collection(@RequestParam Collection<Integer> values) {
        return "Converted collection " + values;
    }

    @GetMapping("formattedCollection")
    public String formattedCollection(@RequestParam @DateTimeFormat(iso = ISO.DATE) Collection<Date> values) {
        return "Converted formatted collection " + values;
    }

    @GetMapping("bean")
    public String bean(JavaBean bean) {
        return "Converted " + bean;
    }

    @GetMapping("value")
    public String valueObject(@RequestParam SocialSecurityNumber value) {
        return "Converted value object " + value;
    }

    @GetMapping("custom")
    public String customConverter(@RequestParam @MaskFormat("###-##-####") String value) {
        return "Converted '" + value + "' with a custom converter";
    }

    public static class JavaBean {
        private Integer primitive;
        private Date date;
        private SocialSecurityNumber ssn;
        private List<Integer> integers;
        private List<Date> dates;

        public Integer getPrimitive() {
            return primitive;
        }

        public void setPrimitive(Integer primitive) {
            this.primitive = primitive;
        }

        public Date getDate() {
            return date;
        }

        @DateTimeFormat(iso = ISO.DATE)
        public void setDate(Date date) {
            this.date = date;
        }

        public SocialSecurityNumber getSsn() {
            return ssn;
        }

        public void setSsn(SocialSecurityNumber ssn) {
            this.ssn = ssn;
        }

        public List<Integer> getIntegers() {
            return integers;
        }

        public void setIntegers(List<Integer> integers) {
            this.integers = integers;
        }

        public List<Date> getDates() {
            return dates;
        }

        @DateTimeFormat(iso = ISO.DATE)
        public void setDates(List<Date> dates) {
            this.dates = dates;
        }

        @Override
        public String toString() {
            return "JavaBean [primitive=" + primitive + ", date=" + date + ", ssn=" + ssn + ", integers=" + integers
                    + ", dates=" + dates + "]";
        }
    }

    public static class SocialSecurityNumber {
        private final String value;

        public SocialSecurityNumber(String value) {
            this.value = value;
        }

        @MaskFormat("###-##-####")
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SocialSecurityNumber that = (SocialSecurityNumber) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}