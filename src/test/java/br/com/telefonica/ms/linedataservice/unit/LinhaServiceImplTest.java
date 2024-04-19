package br.com.telefonica.ms.linedataservice.unit;

import br.com.telefonica.ms.linedataservice.dto.LinhaDTO;
import br.com.telefonica.ms.linedataservice.service.impl.LinhaServiceImpl;
import br.com.telefonica.ms.linedataservice.soap.stubs.*;
import br.com.telefonica.ms.linedataservice.util.LinhaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinhaServiceImplTest {

    @InjectMocks
    private LinhaServiceImpl linhaServiceImpl;

    @Mock
    private LinhaUtil linhaUtil;

    private String cpfCnpj;

    private List<Linha> linhas;

    private List<LinhaDTO> linhasDTO;


    @BeforeEach
    public void setUp() {
        cpfCnpj = "36598746000106";
        Linha linha1 = Linha.builder()
                .numeroLinha("1185988085")
                .statusAssinatura((new StatusAssinatura(null, "ATIVO")))
                .sistemaOrigem(new SistemaOrigem("CSO"))
                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();

        LinhaDTO linhaDTO1 = new LinhaDTO("1185988085", "ATIVO", "CSO", " PRÉ-PAGO");

        Linha linha2 = Linha.builder()
                .numeroLinha("1176869788")
                .statusAssinatura((new StatusAssinatura(null, "ATIVO")))
                .sistemaOrigem(new SistemaOrigem("CSO"))
                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();

        LinhaDTO linhaDTO2 = new LinhaDTO("1176869788", "ATIVO", "CSO", " PRÉ-PAGO");

        Linha linha3 = Linha.builder()
                .numeroLinha("1156798452")
                .statusAssinatura((new StatusAssinatura(null, "CANCELADO")))
                .sistemaOrigem(new SistemaOrigem("CSO"))
                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();

        LinhaDTO linhaDTO3 = new LinhaDTO("1156798452", "CANCELADO", "CSO", " PRÉ-PAGO");

        Linha linha4 = Linha.builder()
                .numeroLinha("1131245798")
                .statusAssinatura((new StatusAssinatura(null, "CANCELADO")))
                .sistemaOrigem(new SistemaOrigem("CSO"))
                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();

        LinhaDTO linhaDTO4 = new LinhaDTO("1131245798", "CANCELADO", "CSO", " PRÉ-PAGO");

        linhas = List.of(linha1, linha2, linha3, linha4);
        linhasDTO = List.of(linhaDTO1, linhaDTO2, linhaDTO3, linhaDTO4);
    }

    @Test
    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndNullStatus() {
        when(linhaUtil.findLinhasByCpfCnpj(anyString()))
                .thenReturn(linhas);

        List<LinhaDTO> linhas = linhaServiceImpl.findLinhasByCpfCnpjAndStatusLinha(cpfCnpj, null);

        assertEquals(linhasDTO, linhas);
    }

    @Test
    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndEmptyStatus() {
        when(linhaUtil.findLinhasByCpfCnpj(anyString()))
                .thenReturn(linhas);

        List<LinhaDTO> linhas = linhaServiceImpl.findLinhasByCpfCnpjAndStatusLinha(cpfCnpj, "");

        assertEquals(linhasDTO, linhas);
    }

    @Test
    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndAtivoStatus() {
        when(linhaUtil.findLinhasByCpfCnpj(anyString()))
                .thenReturn(linhas);

        List<LinhaDTO> linhas = linhaServiceImpl.findLinhasByCpfCnpjAndStatusLinha(cpfCnpj, "ativo");
        List<LinhaDTO> ativoLinhas = List.of(this.linhasDTO.getFirst(), this.linhasDTO.get(1));

        assertEquals(ativoLinhas, linhas);
    }

    @Test
    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndCanceladoStatus() {
        when(linhaUtil.findLinhasByCpfCnpj(anyString()))
                .thenReturn(linhas);

        List<LinhaDTO> linhas = linhaServiceImpl.findLinhasByCpfCnpjAndStatusLinha(cpfCnpj, "CANCELADO");
        List<LinhaDTO> canceladoLinhas = List.of(this.linhasDTO.get(2), this.linhasDTO.get(3));

        assertEquals(canceladoLinhas, linhas);
    }

    @Test
    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndInvalidStatus() {
        when(linhaUtil.findLinhasByCpfCnpj(anyString()))
                .thenReturn(linhas);

        assertThrows(IllegalArgumentException.class, () -> linhaServiceImpl.findLinhasByCpfCnpjAndStatusLinha(cpfCnpj, "FOO"));
    }

    @Test
    public void testFindLinhasByCpfCnpjAndStatusLinha_NullCpfCnpjAndValidStatus() {
        assertThrows(IllegalArgumentException.class, () -> linhaServiceImpl.findLinhasByCpfCnpjAndStatusLinha(null, "ATIVO"));
    }
}
