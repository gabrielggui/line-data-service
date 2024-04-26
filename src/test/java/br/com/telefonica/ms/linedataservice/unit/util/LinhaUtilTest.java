package br.com.telefonica.ms.linedataservice.unit.util;

import br.com.telefonica.ms.linedataservice.client.LinhaClient;
import br.com.telefonica.ms.linedataservice.soap.stubs.*;
import br.com.telefonica.ms.linedataservice.util.LinhaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LinhaUtilTest {

    @InjectMocks
    private LinhaUtil linhaUtil;

    @Mock
    private LinhaClient linhaClient;

    private Linhas linhas;

    private List<Linha> linhasList;

//    @BeforeEach
//    public void setUp() {
//        Linha linha1 = Linha.builder()
//                .numeroLinha("1185988085")
//                .statusAssinatura((new StatusAssinatura(null, "ATIVO")))
//                .sistemaOrigem(new SistemaOrigem("CSO"))
//                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();
//
//        Linha linha2 = Linha.builder()
//                .numeroLinha("1176869788")
//                .statusAssinatura((new StatusAssinatura(null, "ATIVO")))
//                .sistemaOrigem(new SistemaOrigem("CSO"))
//                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();
//
//        Linha linha3 = Linha.builder()
//                .numeroLinha("1156798452")
//                .statusAssinatura((new StatusAssinatura(null, "CANCELADO")))
//                .sistemaOrigem(new SistemaOrigem("CSO"))
//                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();
//
//        Linha linha4 = Linha.builder()
//                .numeroLinha("1131245798")
//                .statusAssinatura((new StatusAssinatura(null, "CANCELADO")))
//                .sistemaOrigem(new SistemaOrigem("CSO"))
//                .tipoAssinatura(new TipoAssinatura(new Plataforma(" PRÉ-PAGO"))).build();
//
//        linhasList = List.of(linha1, linha2, linha3, linha4);
//        linhas = new Linhas(linhasList);
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpj() {
//        when(linhaClient.findLinhasByCpfCnpj(any()))
//                .thenReturn(linhas);
//
//        List<Linha> linhas = linhaUtil.findLinhasByCpfCnpj("21666736007");
//
//        assertEquals(this.linhasList, linhas);
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpj_noLinhasFound() {
//        when(linhaClient.findLinhasByCpfCnpj(any()))
//                .thenReturn(null);
//
//        List<Linha> linhas = linhaUtil.findLinhasByCpfCnpj("21666736007");
//
//        assertTrue(linhas.isEmpty());
//    }
}
