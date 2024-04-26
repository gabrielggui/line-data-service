package br.com.telefonica.ms.linedataservice.util;

import br.com.telefonica.ms.linedataservice.client.LinhaClient;
import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.soap.stubs.BuscarListaLinhasPorCPFCNPJRequest;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class LinhaUtil {

    private final LinhaClient linhaClient;

    public LinhaUtil(LinhaClient linhaRepository) {
        this.linhaClient = linhaRepository;
    }

    /**
     * Makes a request to an external web service to retrieve all phone lines associated with a CPF or CNPJ.
     *
     * @param cpfCnpj     the client's CPF or CNPJ.
     * @param statusLinha the status of the phone lines to be returned.
     * @return a list with all phone lines.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    public List<Linha> findLinhasByCpfCnpj(String cpfCnpj, StatusLinhaEnum statusLinha) {
        var request = new BuscarListaLinhasPorCPFCNPJRequest();
        request.setNumeroCPFCNPJ(cpfCnpj);
        List<Linha> response = linhaClient.findLinhasByCpfCnpj(request).getLinha();

        if (Objects.isNull(response))
            return List.of();

        if (!Objects.isNull(statusLinha)) {
            Stream<Linha> responseStream = response
                    .stream()
                    .filter(linha -> statusLinha.name().equalsIgnoreCase(linha.getStatusAssinatura().getDescricao()));

            return responseStream.toList();
        }

        return response;
    }

    /**
     * Converts a list of "Linha" objects to a list of "LinhaDto" objects.
     *
     * @param linhas a list of "Linha" objects to be converted.
     * @return a list of converted "LinhaDto" objects.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    public List<LinhaDto> mapLinhasToLinhasDtoList(List<Linha> linhas) {
        return linhas.stream().map(LinhaDto::new).toList();
    }

}
