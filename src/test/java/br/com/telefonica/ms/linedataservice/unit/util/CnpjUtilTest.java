package br.com.telefonica.ms.linedataservice.unit.util;

import br.com.telefonica.ms.linedataservice.util.CnpjUtil;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class CnpjUtilTest {

    @ParameterizedTest
    @CsvSource({"02558157000162, true",
            "58842267000106, true",
            "11111111111111, false",
            "12345678987654, false"})
    void testCnpjUtil(String cnpj, boolean expected) {
        assertEquals(expected, CnpjUtil.isValidCnpj(cnpj));
    }
}
