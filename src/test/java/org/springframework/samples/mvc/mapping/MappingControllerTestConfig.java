package org.springframework.samples.mvc.mapping;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MappingControllerTestConfig implements WebMvcConfigurer {

    @Bean
    public MappingController mappingController() {
        return new MappingController();
    }

    @Bean
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService conversionService = new FormattingConversionService();
        return conversionService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Add any custom formatters here if needed
    }
}
