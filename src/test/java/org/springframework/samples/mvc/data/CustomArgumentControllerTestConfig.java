package org.springframework.samples.mvc.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.mvc.data.custom.CustomArgumentController;
import org.springframework.samples.mvc.data.custom.CustomArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CustomArgumentControllerTestConfig implements WebMvcConfigurer {

    @Bean
    public CustomArgumentController customArgumentController() {
        return new CustomArgumentController();
    }

    @Bean
    public CustomArgumentResolver customArgumentResolver() {
        return new CustomArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(customArgumentResolver());
    }
}
