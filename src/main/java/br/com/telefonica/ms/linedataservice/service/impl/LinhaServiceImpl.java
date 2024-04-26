package br.com.telefonica.ms.linedataservice.service.impl;

import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.service.LinhaService;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import br.com.telefonica.ms.linedataservice.util.LinhaUtil;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class LinhaServiceImpl implements LinhaService {

    private final LinhaUtil linhaUtil;

    public LinhaServiceImpl(LinhaUtil linhaUtil) {
        this.linhaUtil = linhaUtil;
    }

    @Override
    public List<LinhaDto> findLinhasByCpf(@CPF String cpf, StatusLinhaEnum statusLinha) {
        List<Linha> linhas = linhaUtil.findLinhasByCpfCnpj(cpf, statusLinha);
        return linhaUtil.mapLinhasToLinhasDtoList(linhas);
    }

    @Override
    public List<LinhaDto> findLinhasByCnpj(@CNPJ String cnpj, StatusLinhaEnum statusLinha) {
        List<Linha> linhas = linhaUtil.findLinhasByCpfCnpj(cnpj, statusLinha);
        return linhaUtil.mapLinhasToLinhasDtoList(linhas);
    }

}
