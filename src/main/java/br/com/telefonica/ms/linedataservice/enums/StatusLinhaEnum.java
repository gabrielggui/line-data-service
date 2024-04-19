package br.com.telefonica.ms.linedataservice.enums;

import java.util.Arrays;

public enum StatusLinhaEnum {
    ATIVO,
    CANCELADO;

    public static StatusLinhaEnum fromKey(String key) {
        if (key == null)
            throw new IllegalArgumentException("Key value is null");

        return Arrays.stream(StatusLinhaEnum.values())
                .filter(enumValue -> key
                        .equalsIgnoreCase(enumValue.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant for " + key));
    }

}
