package br.com.telefonica.ms.linedataservice.unit.soap.stubs;

import br.com.telefonica.ms.linedataservice.soap.stubs.BuscarListaLinhasPorCPFCNPJRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
public class BuscarListaLinhasPorCPFCNPJRequestTest {

    @Test
    public void testGettersAndSetters() {
        var linhaRequest = new BuscarListaLinhasPorCPFCNPJRequest();
        linhaRequest.setNumeroCPFCNPJ("02558157000162");
        assertEquals("02558157000162", linhaRequest.getNumeroCPFCNPJ());
    }

    @Test
    public void testConstructor() {
        var linhaRequest = new BuscarListaLinhasPorCPFCNPJRequest("02558157000162");
        assertEquals("02558157000162", linhaRequest.getNumeroCPFCNPJ());
    }

    @Test
    public void testEqualsAndHashCode() {
        var linhaRequest1 = new BuscarListaLinhasPorCPFCNPJRequest("02558157000162");
        var linhaRequest2 = new BuscarListaLinhasPorCPFCNPJRequest("02558157000162");
        var linhaRequest3 = new BuscarListaLinhasPorCPFCNPJRequest("87402237000187");

        assertEquals(linhaRequest1, linhaRequest2);
        assertNotEquals(linhaRequest1, linhaRequest3);
    }

    @Test
    public void testBuilder() {
        var linhaRequest1 = BuscarListaLinhasPorCPFCNPJRequest.builder()
                .numeroCPFCNPJ("02558157000162")
                .build();

        assertEquals("02558157000162", linhaRequest1.getNumeroCPFCNPJ());
    }
}
