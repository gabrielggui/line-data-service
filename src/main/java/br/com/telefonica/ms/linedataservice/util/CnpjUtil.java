package br.com.telefonica.ms.linedataservice.util;

import java.util.InputMismatchException;
import java.util.Objects;

public class CnpjUtil {

    private CnpjUtil() {
    }

    /**
     * Validates a CNPJ to ensure it follows the proper format and checksum rules.
     *
     * @param cnpj The CNPJ string to be validated.
     * @return True if the CNPJ is valid, otherwise False.
     * @throws InputMismatchException if the input is not a valid CNPJ format.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    public static boolean isValidCnpj(String cnpj) {
        if (Objects.isNull(cnpj) || cnpj.isBlank()) {
            return false;
        }

        cnpj = cnpj.replace(".", "")
                .replace("-", "")
                .replace("/", "");

        if (cnpj.length() != 14){
            return false;
        }

        char firstChar = cnpj.charAt(0);
        if (cnpj.chars().allMatch(ch -> ch == firstChar)) {
            return false;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char) ((11 - r) + 48);

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char) ((11 - r) + 48);

            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}

