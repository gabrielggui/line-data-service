package br.com.telefonica.ms.linedataservice.unit.service;

import br.com.telefonica.ms.linedataservice.client.LinhaClient;
import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.service.impl.LinhaServiceImpl;
import br.com.telefonica.ms.linedataservice.soap.stubs.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LinhaServiceImplTest {

    @InjectMocks
    private LinhaServiceImpl linhaServiceImpl;

    @Mock
    private LinhaClient linhaClient;

    @Mock
    private Validator validator;

    private List<Linha> linhasList;

    Linha linha1, linha2, linha3, linha4;

    @BeforeEach
    public void setUp() {
        linha1 = Linha.builder()
                .numeroLinha("1185988085")
                .statusAssinatura((new StatusAssinatura(null, "ATIVO")))
                .sistemaOrigem(new SistemaOrigem("CSO"))
                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();

        linha2 = Linha.builder()
                .numeroLinha("1176869788")
                .statusAssinatura((new StatusAssinatura(null, "ATIVO")))
                .sistemaOrigem(new SistemaOrigem("CSO"))
                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();

        linha3 = Linha.builder()
                .numeroLinha("1156798452")
                .statusAssinatura((new StatusAssinatura(null, "CANCELADO")))
                .sistemaOrigem(new SistemaOrigem("CSO"))
                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();

        linha4 = Linha.builder()
                .numeroLinha("1131245798")
                .statusAssinatura((new StatusAssinatura(null, "CANCELADO")))
                .sistemaOrigem(new SistemaOrigem("CSO"))
                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();

        linhasList = List.of(linha1, linha2, linha3, linha4);
    }

    @Test
    public void findLinhasDtoByStatusLinha_shouldReturnAllLinesMappedToDto() {
        when(linhaClient.findLinhasByCpfCnpj(any()))
                .thenReturn(new Linhas(linhasList));

        var response = linhaServiceImpl.findLinhasDtoByStatusLinha("02558157000162", null);

        assertEquals(List.of(new LinhaDto(linha1),
                        new LinhaDto(linha2),
                        new LinhaDto(linha3),
                        new LinhaDto(linha4)),
                response);
    }

    @Test
    public void findLinhasDtoByStatusLinha_shouldReturnAllAtivoLinesMappedToDto() {
        when(linhaClient.findLinhasByCpfCnpj(any()))
                .thenReturn(new Linhas(linhasList));

        var response = linhaServiceImpl.findLinhasDtoByStatusLinha("02558157000162", StatusLinhaEnum.ATIVO);
        assertEquals(List.of(new LinhaDto(linha1), new LinhaDto(linha2)), response);
    }

    @Test
    public void findLinhasDtoByStatusLinha_shouldReturnAllCanceladoLinesMappedToDto() {
        when(linhaClient.findLinhasByCpfCnpj(any()))
                .thenReturn(new Linhas(linhasList));

        var response = linhaServiceImpl.findLinhasDtoByStatusLinha("02558157000162", StatusLinhaEnum.CANCELADO);
        assertEquals(List.of(new LinhaDto(linha3), new LinhaDto(linha4)), response);
    }

    @Test
    public void findLinhasDtoByStatusLinha_shouldReturnEmptyList() {
        when(linhaClient.findLinhasByCpfCnpj(any()))
                .thenReturn(new Linhas());

        var response = linhaServiceImpl.findLinhasDtoByStatusLinha("02558157000162", null);
        assertEquals(List.of(), response);
    }

    //////

    @Test
    public void findLinhasByStatusLinha_shouldReturnAllLines() {
        when(linhaClient.findLinhasByCpfCnpj(any()))
                .thenReturn(new Linhas(linhasList));

        var response = linhaServiceImpl.findLinhasByStatusLinha("02558157000162", null);

        assertEquals(List.of(linha1, linha2, linha3, linha4), response);
    }

    @Test
    public void findLinhasByStatusLinha_shouldReturnAllAtivoLines() {
        when(linhaClient.findLinhasByCpfCnpj(any()))
                .thenReturn(new Linhas(linhasList));

        var response = linhaServiceImpl.findLinhasByStatusLinha("02558157000162", StatusLinhaEnum.ATIVO);
        assertEquals(List.of(linha1, linha2), response);
    }

    @Test
    public void findLinhasByStatusLinha_shouldReturnAllCanceladoLines() {
        when(linhaClient.findLinhasByCpfCnpj(any()))
                .thenReturn(new Linhas(linhasList));

        var response = linhaServiceImpl.findLinhasByStatusLinha("02558157000162", StatusLinhaEnum.CANCELADO);
        assertEquals(List.of(linha3, linha4), response);
    }

    @Test
    public void findLinhasByStatusLinha_shouldReturnEmptyList() {
        when(linhaClient.findLinhasByCpfCnpj(any()))
                .thenReturn(new Linhas());

        var response = linhaServiceImpl.findLinhasByStatusLinha("02558157000162", null);
        assertEquals(List.of(), response);
    }

    @Test
    public void findLinhas_shouldReturnLinhasObject() {
        String cpfCnpj = "02558157000162";
        var request = new BuscarListaLinhasPorCPFCNPJRequest(cpfCnpj);
        when(linhaClient.findLinhasByCpfCnpj(request))
                .thenReturn(new Linhas(linhasList));

        var response = linhaServiceImpl.findLinhas(cpfCnpj);

        assertTrue(response.isPresent());
        assertEquals(linhasList, response.get());
    }

    @Test
    public void findLinhas_withConstraintViolation() {
        var cpfCnpj = "invalid";
        var request = new BuscarListaLinhasPorCPFCNPJRequest(cpfCnpj);
        var violations = new HashSet<ConstraintViolation<BuscarListaLinhasPorCPFCNPJRequest>>();
        violations.add(mock(ConstraintViolation.class));
        when(validator.validate(request)).thenReturn(violations);

        assertThrows(ConstraintViolationException.class, () -> linhaServiceImpl.findLinhas(cpfCnpj));
    }
}