package br.com.telefonica.ms.linedataservice.service;

import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;

import java.util.List;
import java.util.Optional;

public interface LinhaService {

    List<LinhaDto> findLinhasDtoByStatusLinha(String cpfCnpj, StatusLinhaEnum statusLinha);

    List<Linha> findLinhasByStatusLinha(String cpfCnpj, StatusLinhaEnum statusLinha);

    Optional<List<Linha>> findLinhas(String cpfCnpj);
    
}
