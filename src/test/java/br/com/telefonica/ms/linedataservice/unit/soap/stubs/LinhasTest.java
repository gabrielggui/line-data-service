package br.com.telefonica.ms.linedataservice.unit.soap.stubs;

import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linhas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
public class LinhasTest {

    private List<Linha> linhasList;

    @BeforeEach
    public void setUp() {
        linhasList = List.of(Linha.builder().numeroLinha("11999999999").build());
    }

    @Test
    public void testGettersAndSetters() {
        var linhas = new Linhas();
        linhas.setLinha(linhasList);

        assertEquals(linhasList, linhas.getLinha());
    }

    @Test
    public void testConstructor() {
        var linhas = new Linhas(linhasList);
        assertEquals(linhasList, linhas.getLinha());
    }

    @Test
    public void testEqualsAndHashCode() {
        var linhas1 = new Linhas(linhasList);
        var linhas2 = new Linhas(linhasList);
        var linhas3 = new Linhas(List.of());

        assertEquals(linhas1, linhas2);
        assertNotEquals(linhas1, linhas3);
    }

    @Test
    public void testBuilder() {
        var linhas = Linhas.builder()
                .linha(linhasList)
                .build();

        assertEquals(linhasList, linhas.getLinha());
    }
}
