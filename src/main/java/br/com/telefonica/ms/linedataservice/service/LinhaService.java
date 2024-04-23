package br.com.telefonica.ms.linedataservice.service;

import br.com.telefonica.ms.linedataservice.dto.LinhaDTO;

import java.util.List;

public interface LinhaService {

    List<LinhaDTO> findLinhasByCpfCnpj(String cpfCnpj);

    List<LinhaDTO> findLinhasByCpfCnpjAndStatusLinha(String CpfCnpj, String statusLinha);
}
