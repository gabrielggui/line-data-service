package br.com.telefonica.ms.linedataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ExceptionMessage {

    private Integer code;
    private String status;
    private String message;

}