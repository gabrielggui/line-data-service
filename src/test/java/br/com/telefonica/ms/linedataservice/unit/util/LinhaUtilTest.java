package br.com.telefonica.ms.linedataservice.unit.util;

import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.soap.stubs.*;
import br.com.telefonica.ms.linedataservice.util.LinhaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LinhaUtilTest {

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
    public void shouldReturnAllLines() {
        List<Linha> linhas = LinhaUtil.filterLinhasByStatus(linhasList, null);
        assertEquals(List.of(linha1, linha2, linha3, linha4), linhas);
    }

    @Test
    public void shouldReturnAllActiveLines() {
        List<Linha> linhas = LinhaUtil.filterLinhasByStatus(linhasList, StatusLinhaEnum.ATIVO);
        assertEquals(List.of(linha1, linha2), linhas);
    }

    @Test
    public void shouldReturnAllCancelledLines() {
        List<Linha> linhas = LinhaUtil.filterLinhasByStatus(linhasList, StatusLinhaEnum.CANCELADO);
        assertEquals(List.of(linha3, linha4), linhas);
    }

    @Test
    public void shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> LinhaUtil.filterLinhasByStatus(null, StatusLinhaEnum.ATIVO));
    }

    @Test
    public void shouldReturnAllLinesMappedToDto() {
        List<LinhaDto> linhasDto = LinhaUtil.mapLinhasToLinhasDtoList(linhasList);
        assertEquals(List.of(new LinhaDto(linha1),
                        new LinhaDto(linha2),
                        new LinhaDto(linha3),
                        new LinhaDto(linha4)),
                linhasDto);
    }

}
