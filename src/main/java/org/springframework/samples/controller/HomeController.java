package org.springframework.samples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController provides a root mapping for the application.
 * This was generated as a fallback controller to ensure the application has a working home page.
 */
@Controller
public class HomeController {

    /**
     * Maps the root URL to the home view.
     * @return the name of the view to render
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Alternative root mapping for context path access.
     * @return the name of the view to render
     */
    @RequestMapping
    public String index() {
        return "home";
    }
}
