package br.com.telefonica.ms.linedataservice.service.impl;

import br.com.telefonica.ms.linedataservice.dto.LinhaDTO;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import br.com.telefonica.ms.linedataservice.service.LinhaService;
import br.com.telefonica.ms.linedataservice.util.LinhaUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class LinhaServiceImpl implements LinhaService {

    private final LinhaUtil linhaUtil;

    public LinhaServiceImpl(LinhaUtil linhaUtil) {
        this.linhaUtil = linhaUtil;
    }

    /**
     * Retrieve a client's phone lines based on a CPF or CNPJ and the line status.
     *
     * @param cpfCnpj     the CPF or CNPJ of the client.
     * @param statusLinha the status of the lines to be returned.
     * @return The phone lines associated with a CPF or CNPJ.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    @Override
    public List<LinhaDTO> findLinhasByCpfCnpjAndStatusLinha(String cpfCnpj, String statusLinha) {
        if (Objects.isNull(cpfCnpj))
            throw new IllegalArgumentException("CPF/CNPJ is required");

        List<Linha> linhas = linhaUtil.findLinhasByCpfCnpj(cpfCnpj);
        Stream<Linha> linhasStream = linhas.stream();

        if (!Objects.isNull(statusLinha) && !statusLinha.isBlank()) {
            StatusLinhaEnum statusLinhaEnum = StatusLinhaEnum.fromKey(statusLinha);
            linhasStream = linhasStream.filter(linha -> statusLinhaEnum.name()
                    .equalsIgnoreCase(linha.getStatusAssinatura().getDescricao()));
        }

        return linhasStream
                .map(LinhaDTO::new)
                .toList();
    }

}
