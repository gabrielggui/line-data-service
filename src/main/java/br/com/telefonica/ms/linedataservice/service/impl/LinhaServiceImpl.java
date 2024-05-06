package br.com.telefonica.ms.linedataservice.service.impl;

import br.com.telefonica.ms.linedataservice.client.LinhaClient;
import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.service.LinhaService;
import br.com.telefonica.ms.linedataservice.soap.stubs.BuscarListaLinhasPorCPFCNPJRequest;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import br.com.telefonica.ms.linedataservice.util.LinhaUtil;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinhaServiceImpl implements LinhaService{

    @Autowired
    private LinhaClient linhaClient;

    @Autowired
    private Validator validator;

    @Override
    public List<LinhaDto> findLinhasDtoByStatusLinha(String cpfCnpj, StatusLinhaEnum statusLinha) {
        var response = this.findLinhasByStatusLinha(cpfCnpj, statusLinha);
        return LinhaUtil.mapLinhasToLinhasDtoList(response);
    }

    @Override
    public List<Linha> findLinhasByStatusLinha(String cpfCnpj, StatusLinhaEnum statusLinha) {
        var response = this.findLinhas(cpfCnpj).orElse(List.of());
        return LinhaUtil.filterLinhasByStatus(response, statusLinha);
    }

    @Override
    public Optional<List<Linha>> findLinhas(String cpfCnpj) {
        var request = new BuscarListaLinhasPorCPFCNPJRequest(cpfCnpj);
        var violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

        return Optional.ofNullable(linhaClient.findLinhasByCpfCnpj(request).getLinha());
    }

}
