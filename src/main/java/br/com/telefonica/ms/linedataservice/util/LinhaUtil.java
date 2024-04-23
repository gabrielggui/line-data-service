package br.com.telefonica.ms.linedataservice.util;

import br.com.telefonica.ms.linedataservice.client.LinhaClient;
import br.com.telefonica.ms.linedataservice.soap.stubs.BuscarListaLinhasPorCPFCNPJRequest;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linhas;
import org.springframework.stereotype.Component;

import java.util.*;

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
    public List<Linha> findLinhasByCpfCnpj(String cpfCnpj) {
        var request = new BuscarListaLinhasPorCPFCNPJRequest();
        request.setNumeroCPFCNPJ(cpfCnpj);

        Linhas response = linhaClient.findLinhasByCpfCnpj(request);

        if (Objects.isNull(response.getLinha()))
            return List.of();

        return response.getLinha();
    }

}
