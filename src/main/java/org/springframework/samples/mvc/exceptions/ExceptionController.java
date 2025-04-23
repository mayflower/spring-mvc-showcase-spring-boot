package org.springframework.samples.mvc.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/exceptions")
public class ExceptionController {

    @GetMapping("/exception")
    public String exception() {
        throw new IllegalStateException("Sorry!");
    }

    @GetMapping("/global-exception")
    public String businessException() throws BusinessException {
        throw new BusinessException();
    }

    @ExceptionHandler
    public String handle(IllegalStateException e) {
        return "IllegalStateException handled!";
    }

    /**
     * Business exception for demo purposes.
     */
    public static class BusinessException extends Exception {
        private static final long serialVersionUID = 1L;

        public BusinessException() {
            super("Business exception occurred");
        }

        public BusinessException(String message) {
            super(message);
        }
    }
}