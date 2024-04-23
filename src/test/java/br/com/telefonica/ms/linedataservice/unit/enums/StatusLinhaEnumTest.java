package br.com.telefonica.ms.linedataservice.unit.enums;

import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.exception.InvalidStatusLinhaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class StatusLinhaEnumTest {

    @Test
    public void testFromKey_nullKey() {
        assertThrows(NullPointerException.class, () -> StatusLinhaEnum.fromKey(null));
    }

    @Test
    public void testFromKey_emptyKey() {
        assertThrows(InvalidStatusLinhaException.class, () -> StatusLinhaEnum.fromKey(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ATIVO", "CANCELADO"})
    void testFromKey_validKeys(String input) {
        assertEquals(StatusLinhaEnum.fromKey(input).name(), input);
    }
}

