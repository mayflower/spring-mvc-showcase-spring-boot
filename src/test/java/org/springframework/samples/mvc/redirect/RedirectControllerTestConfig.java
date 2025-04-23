package org.springframework.samples.mvc.redirect;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@TestConfiguration
public class RedirectControllerTestConfig implements WebMvcConfigurer {

    @org.springframework.context.annotation.Bean
    public DefaultFormattingConversionService conversionService() {
        return new DefaultFormattingConversionService();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Add any custom formatters if needed
    }
}
