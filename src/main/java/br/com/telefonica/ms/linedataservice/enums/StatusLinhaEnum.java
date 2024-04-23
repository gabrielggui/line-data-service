package br.com.telefonica.ms.linedataservice.enums;

import br.com.telefonica.ms.linedataservice.exception.InvalidStatusLinhaException;

import java.util.Arrays;
import java.util.Objects;

public enum StatusLinhaEnum {
    ATIVO,
    CANCELADO;

    public static StatusLinhaEnum fromKey(String key) {
        if (Objects.isNull(key))
            throw new NullPointerException("Key cannot be null");

        return Arrays.stream(StatusLinhaEnum.values())
                .filter(enumValue -> key
                        .equalsIgnoreCase(enumValue.name()))
                .findFirst()
                .orElseThrow(() -> new InvalidStatusLinhaException("No enum constant for " + key));
    }

}
