package br.com.telefonica.ms.linedataservice.controller;

import br.com.telefonica.ms.linedataservice.dto.LinhaDTO;
import br.com.telefonica.ms.linedataservice.service.LinhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Linha")
@RestController
@RequestMapping("/linha")
public class LinhaController {

    public LinhaService linhaService;

    public LinhaController(LinhaService linhaService) {
        this.linhaService = linhaService;
    }

    /**
     * Retrieve a client's phone lines based on a CPF or CNPJ.
     *
     * @param cpfCnpj the CPF or CNPJ of the client.
     * @return The phone lines associated with a CPF or CNPJ.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    @Operation(summary = "Retrieve a client's phone lines based on a CPF or CNPJ.")
    @GetMapping(params = "cpf_cnpj")
    public ResponseEntity<List<LinhaDTO>> findLinhasByCpfCnpj(
            @RequestParam("cpf_cnpj") String cpfCnpj) {
        List<LinhaDTO> linhas = linhaService.findLinhasByCpfCnpj(cpfCnpj);

        return ResponseEntity.ok().body(linhas);
    }

    /**
     * Retrieves a client's phone lines based on their CPF or CNPJ and the phone line status.
     *
     * @param cpfCnpj     the client's CPF or CNPJ.
     * @param statusLinha the status of the phone lines to be returned.
     * @return The phone lines associated with a CPF or CNPJ.
     * @author Gabriel Guilherme (gabriel.guilherme@compasso.com.br)
     */
    @Operation(summary = "Retrieves a client's phone lines based on their CPF or CNPJ and the phone line status.")
    @GetMapping(params = {"cpf_cnpj", "status_linha"})
    public ResponseEntity<List<LinhaDTO>> findLinhasByCpfCnpjAndStatusLinha(
            @RequestParam("cpf_cnpj") String cpfCnpj,
            @RequestParam(name = "status_linha")
            @Parameter(allowEmptyValue = true, description = "Available values: ATIVO, CANCELADO") String statusLinha) {
        List<LinhaDTO> linhas = linhaService.findLinhasByCpfCnpjAndStatusLinha(cpfCnpj, statusLinha);

        return ResponseEntity.ok().body(linhas);
    }

}
