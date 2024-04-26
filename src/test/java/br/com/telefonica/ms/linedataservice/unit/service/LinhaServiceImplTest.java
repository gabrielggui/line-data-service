package br.com.telefonica.ms.linedataservice.unit.service;

import br.com.telefonica.ms.linedataservice.service.impl.LinhaServiceImpl;
import br.com.telefonica.ms.linedataservice.soap.stubs.*;
import br.com.telefonica.ms.linedataservice.util.LinhaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LinhaServiceImplTest {

    @InjectMocks
    private LinhaServiceImpl linhaServiceImpl;

    @Mock
    private LinhaUtil linhaUtil;

    private List<Linha> linhas;
//
//    private List<LinhaDTO> linhasDTO;
//
//
//    @BeforeEach
//    public void setUp() {
//        Linha linha1 = Linha.builder()
//                .numeroLinha("1185988085")
//                .statusAssinatura((new StatusAssinatura(null, "ATIVO")))
//                .sistemaOrigem(new SistemaOrigem("CSO"))
//                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();
//
//        LinhaDTO linhaDTO1 = new LinhaDTO("1185988085", "ATIVO", "CSO", " PRÉ-PAGO");
//
//        Linha linha2 = Linha.builder()
//                .numeroLinha("1176869788")
//                .statusAssinatura((new StatusAssinatura(null, "ATIVO")))
//                .sistemaOrigem(new SistemaOrigem("CSO"))
//                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();
//
//        LinhaDTO linhaDTO2 = new LinhaDTO("1176869788", "ATIVO", "CSO", " PRÉ-PAGO");
//
//        Linha linha3 = Linha.builder()
//                .numeroLinha("1156798452")
//                .statusAssinatura((new StatusAssinatura(null, "CANCELADO")))
//                .sistemaOrigem(new SistemaOrigem("CSO"))
//                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();
//
//        LinhaDTO linhaDTO3 = new LinhaDTO("1156798452", "CANCELADO", "CSO", " PRÉ-PAGO");
//
//        Linha linha4 = Linha.builder()
//                .numeroLinha("1131245798")
//                .statusAssinatura((new StatusAssinatura(null, "CANCELADO")))
//                .sistemaOrigem(new SistemaOrigem("CSO"))
//                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();
//
//        LinhaDTO linhaDTO4 = new LinhaDTO("1131245798", "CANCELADO", "CSO", " PRÉ-PAGO");
//
//        linhas = List.of(linha1, linha2, linha3, linha4);
//        linhasDTO = List.of(linhaDTO1, linhaDTO2, linhaDTO3, linhaDTO4);
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndNullStatusLinha() {
//        assertThrows(NullPointerException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpjAndStatusLinha("21666736007", null));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndEmptyStatus() {
//        assertThrows(InvalidStatusLinhaException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpjAndStatusLinha("21666736007", ""));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndAtivoStatus() {
//        when(linhaUtil.findLinhasByCpfCnpj(anyString()))
//                .thenReturn(linhas);
//
//        List<LinhaDTO> linhas = linhaServiceImpl.findLinhasByCpfCnpjAndStatusLinha("21666736007", "ativo");
//        List<LinhaDTO> ativoLinhas = List.of(this.linhasDTO.getFirst(), this.linhasDTO.get(1));
//
//        assertEquals(ativoLinhas, linhas);
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndCanceladoStatus() {
//        when(linhaUtil.findLinhasByCpfCnpj(anyString()))
//                .thenReturn(linhas);
//
//        List<LinhaDTO> linhas = linhaServiceImpl.findLinhasByCpfCnpjAndStatusLinha("21666736007", "cancelado");
//        List<LinhaDTO> canceladoLinhas = List.of(this.linhasDTO.get(2), this.linhasDTO.get(3));
//
//        assertEquals(canceladoLinhas, linhas);
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_ValidCpfCnpjAndInvalidStatus() {
//        assertThrows(InvalidStatusLinhaException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpjAndStatusLinha("21666736007", "foo"));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_NullCpfCnpjAndValidStatus() {
//        assertThrows(InvalidCpfCnpjException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpjAndStatusLinha(null, "ativo"));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_EmptyCpfCnpjAndValidStatus() {
//        assertThrows(InvalidCpfCnpjException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpjAndStatusLinha("", "ativo"));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_InvalidCpfCnpjAndValidStatus() {
//        assertThrows(InvalidCpfCnpjException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpjAndStatusLinha("58798745632", "ativo"));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpjAndStatusLinha_InvalidCpfCnpjAndInvalidStatus() {
//        assertThrows(InvalidCpfCnpjException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpjAndStatusLinha("58798745632", "foo"));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpj_ValidCpfCnpj() {
//        when(linhaUtil.findLinhasByCpfCnpj(anyString()))
//                .thenReturn(linhas);
//
//        List<LinhaDTO> linhas = linhaServiceImpl.findLinhasByCpfCnpj("21666736007");
//
//        assertEquals(this.linhasDTO, linhas);
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpj_NullCpfCnpj() {
//        assertThrows(InvalidCpfCnpjException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpj(null));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpj_EmptyCpfCnpj() {
//        assertThrows(InvalidCpfCnpjException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpj(""));
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpj_InvalidCpfCnpj() {
//        assertThrows(InvalidCpfCnpjException.class, () -> linhaServiceImpl
//                .findLinhasByCpfCnpj("58798745632"));
//    }

}
