package br.com.telefonica.ms.linedataservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {
    MISSING_PARAMETER(400, "BAD_REQUEST.MISSING_PARAMETER", "A required parameter is missing or invalid."),
    VALIDATION_ERROR(400, "BAD_REQUEST.VALIDATION_ERROR", "Validation error. Please check the input data."),
    TYPE_MISMATCH_ERROR(400, "BAD_REQUEST.TYPE_MISMATCH", "Invalid parameter value. Please provide valid data."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "An error occurred. Please try again later."),
    RESOURCE_NOT_FOUND(400, "BAD_REQUEST.RESOURCE_NOT_FOUND", "The requested resource was not found."),
    MISSING_SERVLET_PARAMETER(400, "BAD_REQUEST.MISSING_SERVLET_PARAMETER", "A required servlet request parameter is missing: ");

    private final Integer code;
    private final String status;
    private final String message;
}
