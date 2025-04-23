package org.springframework.samples.mvc.form;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.number.PercentStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class FormControllerTestConfig implements WebMvcConfigurer {

    @Bean
    public FormController formController() {
        return new FormController();
    }

    @Bean
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        // Add date formatter
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.setPattern("yyyy-MM-dd");
        conversionService.addFormatter(dateFormatter);

        // Add currency formatter
        NumberStyleFormatter currencyFormatter = new NumberStyleFormatter("¤#,##0.00");
        // setFractionDigits method doesn't exist in newer Spring versions
        conversionService.addFormatter(currencyFormatter);

        // Add percent formatter
        PercentStyleFormatter percentFormatter = new PercentStyleFormatter();
        conversionService.addFormatter(percentFormatter);

        // Add annotation formatter factory
        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        return conversionService;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Register custom formatters if needed
    }
}
