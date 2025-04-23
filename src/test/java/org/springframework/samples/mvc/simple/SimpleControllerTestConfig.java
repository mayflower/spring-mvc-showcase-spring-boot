package org.springframework.samples.mvc.simple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SimpleControllerTestConfig implements WebMvcConfigurer {

    @Bean
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService conversionService = new FormattingConversionService();
        return conversionService;
    }

    @Bean
    public SimpleController simpleController() {
        return new SimpleController();
    }
}
