package org.springframework.samples.mvc.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle BusinessException.
     */
    @ExceptionHandler(ExceptionController.BusinessException.class)
    @ResponseBody
    public String handleBusinessException(ExceptionController.BusinessException ex) {
        return "Handled BusinessException";
    }
}
