package org.springframework.samples.mvc.validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class ValidationController {

    // enforcement of constraints on the JavaBean arg require a JSR-303 provider on the classpath
    
    @GetMapping
    public String validate(@Valid JavaBean bean, BindingResult result) {
        if (result.hasErrors()) {
            return "Object has validation errors";
        } else {
            return "No errors";
        }
    }

    public static class JavaBean {

        @NotNull
        @Size(min = 1, max = 25)
        private String name;

        @NotNull
        @Size(min = 2, max = 50)
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "JavaBean [name=" + name + ", description=" + description + "]";
        }
    }
}