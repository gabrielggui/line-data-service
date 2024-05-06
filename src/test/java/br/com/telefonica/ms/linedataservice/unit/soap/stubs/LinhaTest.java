package br.com.telefonica.ms.linedataservice.unit.soap.stubs;

import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import br.com.telefonica.ms.linedataservice.soap.stubs.SistemaOrigem;
import br.com.telefonica.ms.linedataservice.soap.stubs.StatusAssinatura;
import br.com.telefonica.ms.linedataservice.soap.stubs.TipoAssinatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LinhaTest {

    private SistemaOrigem sistemaOrigem;
    private StatusAssinatura statusAssinatura;
    private TipoAssinatura tipoAssinatura;

    @BeforeEach
    public void setUp() {
        sistemaOrigem = new SistemaOrigem();
        statusAssinatura = new StatusAssinatura();
        tipoAssinatura = new TipoAssinatura();
    }

    @Test
    public void testGettersAndSetters() {
        var linhaRequest = new Linha();
        linhaRequest.setNumeroLinha("11369874563");
        linhaRequest.setSistemaOrigem(sistemaOrigem);
        linhaRequest.setStatusAssinatura(statusAssinatura);
        linhaRequest.setTipoAssinatura(tipoAssinatura);

        assertEquals("11369874563", linhaRequest.getNumeroLinha());
        assertEquals(sistemaOrigem, linhaRequest.getSistemaOrigem());
        assertEquals(statusAssinatura, linhaRequest.getStatusAssinatura());
        assertEquals(tipoAssinatura, linhaRequest.getTipoAssinatura());
    }

    @Test
    public void testConstructor() {
        var linhaRequest = new Linha("11369874563", statusAssinatura, tipoAssinatura, sistemaOrigem);

        assertEquals("11369874563", linhaRequest.getNumeroLinha());
        assertEquals(statusAssinatura, linhaRequest.getStatusAssinatura());
        assertEquals(tipoAssinatura, linhaRequest.getTipoAssinatura());
        assertEquals(sistemaOrigem, linhaRequest.getSistemaOrigem());
    }

    @Test
    public void testEqualsAndHashCode() {
        var linhaRequest1 = new Linha("11369874563", statusAssinatura, tipoAssinatura, sistemaOrigem);
        var linhaRequest2 = new Linha("11369874563", statusAssinatura, tipoAssinatura, sistemaOrigem);
        var linhaRequest3 = new Linha("22222222222", statusAssinatura, tipoAssinatura, sistemaOrigem);

        assertEquals(linhaRequest1, linhaRequest2);
        assertNotEquals(linhaRequest1, linhaRequest3);
    }

    @Test
    public void testBuilder() {
        var linhaRequest = Linha.builder()
                .numeroLinha("11369874563")
                .sistemaOrigem(sistemaOrigem)
                .statusAssinatura(statusAssinatura)
                .tipoAssinatura(tipoAssinatura)
                .build();

        assertEquals("11369874563", linhaRequest.getNumeroLinha());
        assertEquals(statusAssinatura, linhaRequest.getStatusAssinatura());
        assertEquals(tipoAssinatura, linhaRequest.getTipoAssinatura());
        assertEquals(sistemaOrigem, linhaRequest.getSistemaOrigem());
    }
}
