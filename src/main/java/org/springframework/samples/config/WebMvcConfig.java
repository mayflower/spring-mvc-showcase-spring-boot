package org.springframework.samples.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC configuration for root path mapping.
 * This configuration ensures the application has a working root URL.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Root URL mapping - prevents Whitelabel Error Page
        registry.addViewController("/").setViewName("home");
    }
}
