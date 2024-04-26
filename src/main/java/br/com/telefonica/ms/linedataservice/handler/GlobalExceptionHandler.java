package br.com.telefonica.ms.linedataservice.handler;

import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MISSING_PARAMETER_MESSAGE = "A required parameter is missing or invalid.";
    private static final String VALIDATION_ERROR_MESSAGE = "Validation error. Please check the input data.";
    private static final String TYPE_MISMATCH_ERROR_MESSAGE = "Invalid parameter type. Please provide valid data.";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "An error occurred. Please try again later.";
    private static final String RESOURCE_NOT_FOUND_MESSAGE = "The requested resource was not found.";

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException e) {
        Map<String, String> errors = createResponse("RESOURCE_NOT_FOUND", RESOURCE_NOT_FOUND_MESSAGE);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RetryableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleRetryableException(RetryableException e) {
        Map<String, String> errors = createResponse("INTERNAL_SERVER_ERROR", INTERNAL_SERVER_ERROR_MESSAGE);
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HandlerMethodValidationException.class})
    public ResponseEntity<Object> handleHandlerMethodValidationException(
            HandlerMethodValidationException ex, WebRequest request) {
        Map<String, String> errors = createResponse("VALIDATION_ERROR", VALIDATION_ERROR_MESSAGE);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Map<String, String> errors = createResponse("TYPE_MISMATCH_ERROR", TYPE_MISMATCH_ERROR_MESSAGE);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleUnsatisfiedServletRequestParameterException(UnsatisfiedServletRequestParameterException e) {
        Map<String, String> errors = createResponse("MISSING_PARAMETER", MISSING_PARAMETER_MESSAGE);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    public final Map<String, String> createResponse(String error, String message) {
        Map<String, String> errors = new HashMap<>();
        errors.put("code", error);
        errors.put("message", message);
        return errors;
    }
}
