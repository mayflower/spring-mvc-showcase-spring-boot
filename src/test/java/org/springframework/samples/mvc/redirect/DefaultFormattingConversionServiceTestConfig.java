package org.springframework.samples.mvc.redirect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class DefaultFormattingConversionServiceTestConfig {

    @Bean
    public DefaultFormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        addFormatters(conversionService);
        return conversionService;
    }

    protected void addFormatters(FormatterRegistry registry) {
        // Add any custom formatters here if needed
        // This method can be extended if specific formatters are required
    }
}
