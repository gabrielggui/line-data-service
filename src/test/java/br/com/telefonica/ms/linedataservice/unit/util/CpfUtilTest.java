package br.com.telefonica.ms.linedataservice.unit.util;

import br.com.telefonica.ms.linedataservice.util.CpfUtil;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class CpfUtilTest {

    @ParameterizedTest
    @CsvSource({"13999967030, true",
            "44841562028, true",
            "11111111111, false",
            "65465848484, false"})
    void testCpfUtil(String cpf, boolean expected) {
        assertEquals(expected, CpfUtil.isValidCpf(cpf));
    }
}
