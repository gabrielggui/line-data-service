package br.com.telefonica.ms.linedataservice.handler;

import br.com.telefonica.ms.linedataservice.dto.ExceptionMessage;
import br.com.telefonica.ms.linedataservice.enums.ErrorStatus;
import feign.RetryableException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionMessage> handleNoResourceFoundException(NoResourceFoundException e) {
        var response = ExceptionMessage.builder()
                .code(ErrorStatus.RESOURCE_NOT_FOUND.getCode())
                .status(ErrorStatus.RESOURCE_NOT_FOUND.getStatus())
                .message(ErrorStatus.RESOURCE_NOT_FOUND.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Object> handleRetryableException(RetryableException e) {
        var response = ExceptionMessage.builder()
                .code(ErrorStatus.INTERNAL_SERVER_ERROR.getCode())
                .status(ErrorStatus.INTERNAL_SERVER_ERROR.getStatus())
                .message(ErrorStatus.INTERNAL_SERVER_ERROR.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HandlerMethodValidationException.class})
    public ResponseEntity<Object> handleHandlerMethodValidationException(
            HandlerMethodValidationException ex, WebRequest request) {
        var response = ExceptionMessage.builder()
                .code(ErrorStatus.VALIDATION_ERROR.getCode())
                .status(ErrorStatus.VALIDATION_ERROR.getStatus())
                .message(ErrorStatus.VALIDATION_ERROR.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        var errorMessage = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .reduce((m1, m2) -> m1 + ", " + m2)
                .orElse(ErrorStatus.VALIDATION_ERROR.getMessage());

        var response = ExceptionMessage.builder()
                .code(ErrorStatus.VALIDATION_ERROR.getCode())
                .status(ErrorStatus.VALIDATION_ERROR.getStatus())
                .message(errorMessage)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        var response = ExceptionMessage.builder()
                .code(ErrorStatus.TYPE_MISMATCH_ERROR.getCode())
                .status(ErrorStatus.TYPE_MISMATCH_ERROR.getStatus())
                .message(ErrorStatus.TYPE_MISMATCH_ERROR.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        var parameterName = ex.getParameterName();

        var response = ExceptionMessage.builder()
                .code(ErrorStatus.MISSING_SERVLET_PARAMETER.getCode())
                .status(ErrorStatus.MISSING_SERVLET_PARAMETER.getStatus())
                .message(ErrorStatus.MISSING_SERVLET_PARAMETER.getMessage() + parameterName)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
