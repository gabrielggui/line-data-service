package br.com.telefonica.ms.linedataservice.service.impl;

import br.com.telefonica.ms.linedataservice.dto.LinhaDTO;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.exception.InvalidCpfCnpjException;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import br.com.telefonica.ms.linedataservice.service.LinhaService;
import br.com.telefonica.ms.linedataservice.util.CnpjUtil;
import br.com.telefonica.ms.linedataservice.util.CpfUtil;
import br.com.telefonica.ms.linedataservice.util.LinhaUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class LinhaServiceImpl implements LinhaService {

    private final LinhaUtil linhaUtil;

    public LinhaServiceImpl(LinhaUtil linhaUtil) {
        this.linhaUtil = linhaUtil;
    }

    /**
     * Retrieve a client's phone lines based on a CPF or CNPJ.
     *
     * @param cpfCnpj the CPF or CNPJ of the client.
     * @return The phone lines associated with a CPF or CNPJ.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    @Override
    public List<LinhaDTO> findLinhasByCpfCnpj(String cpfCnpj) {
        if (!CpfUtil.isValidCpf(cpfCnpj) && !CnpjUtil.isValidCnpj(cpfCnpj))
            throw new InvalidCpfCnpjException("Invalid CPF/CNPJ");

        Stream<Linha> linhasStream = linhaUtil.findLinhasByCpfCnpj(cpfCnpj).stream();
        return linhasStream.map(LinhaDTO::new).toList();
    }

    /**
     * Retrieves a client's phone lines based on a CPF or CNPJ and the line status.
     *
     * @param cpfCnpj     the client's CPF or CNPJ.
     * @param statusLinha the status of the lines to be returned.
     * @return The phone lines associated with a CPF or CNPJ, filtered by the phone line's status.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    @Override
    public List<LinhaDTO> findLinhasByCpfCnpjAndStatusLinha(String cpfCnpj, String statusLinha) {
        if (!CpfUtil.isValidCpf(cpfCnpj) && !CnpjUtil.isValidCnpj(cpfCnpj))
            throw new InvalidCpfCnpjException("Invalid CPF/CNPJ");

        StatusLinhaEnum statusLinhaEnum = StatusLinhaEnum.fromKey(statusLinha);
        Stream<Linha> linhasStream = linhaUtil.findLinhasByCpfCnpj(cpfCnpj).stream();

        linhasStream = linhasStream.filter(linha -> statusLinhaEnum.name()
                .equalsIgnoreCase(linha.getStatusAssinatura().getDescricao()));

        return linhasStream.map(LinhaDTO::new).toList();
    }
}
