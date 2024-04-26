package br.com.telefonica.ms.linedataservice.service;

import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public interface LinhaService {

    List<LinhaDto> findLinhasByCpf(@CPF String cpfCnpj, StatusLinhaEnum statusLinha);

    List<LinhaDto> findLinhasByCnpj(@CNPJ String cpfCnpj, StatusLinhaEnum statusLinha);
}
