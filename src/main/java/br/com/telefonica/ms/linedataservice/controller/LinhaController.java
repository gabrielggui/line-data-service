package br.com.telefonica.ms.linedataservice.controller;

import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.service.LinhaService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Retrieves a client's phone lines based on the CPF/CNPJ and the line status.")
    @GetMapping
    public ResponseEntity<List<LinhaDto>> findLinhas(
            @RequestParam("cpf_cnpj") String cpfCnpj,
            @RequestParam(required = false, name = "status") StatusLinhaEnum statusLinha) {
        var linhas = linhaService.findLinhasDtoByStatusLinha(cpfCnpj, statusLinha);
        return ResponseEntity.ok().body(linhas);
    }

}
