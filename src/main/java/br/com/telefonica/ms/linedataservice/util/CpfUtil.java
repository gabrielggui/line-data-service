package br.com.telefonica.ms.linedataservice.util;

import java.util.Objects;

public class CpfUtil {

    private CpfUtil() {
    }

    /**
     * Checks the validity of a CPF according to Brazilian checksum rules.
     *
     * @param cpf The CPF to be validated.
     * @return True if the CPF is valid, otherwise False.
     * @throws NumberFormatException if the CPF is not in a valid format.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    public static boolean isValidCpf(String cpf) {
        if (Objects.isNull(cpf) || cpf.isBlank()) {
            return false;
        }

        cpf = cpf.replace(".", "")
                .replace("-", "");

        if (cpf.length() != 11){
            return false;
        }

        char firstChar = cpf.charAt(0);
        if (cpf.chars().allMatch(ch -> ch == firstChar)) {
            return false;
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            d1 = d1 + (11 - nCount) * digitoCPF;

            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        resto = (d1 % 11);

        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        resto = (d2 % 11);

        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        return nDigVerific.equals(nDigResult);
    }

}
