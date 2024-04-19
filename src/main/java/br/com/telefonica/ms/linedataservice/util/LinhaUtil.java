package br.com.telefonica.ms.linedataservice.util;

import br.com.telefonica.ms.linedataservice.client.LinhaClient;
import br.com.telefonica.ms.linedataservice.soap.stubs.BuscarListaLinhasPorCPFCNPJRequest;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Stream;

@Validated
@Component
public class LinhaUtil {

    private final LinhaClient linhaClient;

    public LinhaUtil(LinhaClient linhaRepository) {
        this.linhaClient = linhaRepository;
    }

    /**
     * Method responsible for returning a stream with all phone lines associated with a CPF or CNPJ.
     *
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     * @param cpfCnpj     the CPF or CNPJ of the client.
     * @return a stream with all phone lines.
     */
    public List<Linha> findLinhasByCpfCnpj(@NotBlank String cpfCnpj) {
        var request = new BuscarListaLinhasPorCPFCNPJRequest();
        request.setNumeroCPFCNPJ(cpfCnpj);

        List<Linha> response = linhaClient.findLinhasByCpfCnpj(request).getLinha();

        if (Objects.isNull(response))
            return List.of();

        return response;
    }

}
