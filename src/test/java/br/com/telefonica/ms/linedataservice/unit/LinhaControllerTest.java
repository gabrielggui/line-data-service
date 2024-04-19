package br.com.telefonica.ms.linedataservice.unit;

import br.com.telefonica.ms.linedataservice.controller.LinhaController;
import br.com.telefonica.ms.linedataservice.dto.LinhaDTO;
import br.com.telefonica.ms.linedataservice.service.impl.LinhaServiceImpl;
import br.com.telefonica.ms.linedataservice.soap.stubs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(LinhaController.class)
public class LinhaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LinhaServiceImpl linhaService;

    private List<LinhaDTO> linhasDTO;

    @BeforeEach
    public void setUp() {
        LinhaDTO linhaDTO1 = new LinhaDTO("1185988085", "ATIVO", "CSO", " PRÉ-PAGO");
        LinhaDTO linhaDTO2 = new LinhaDTO("1176869788", "ATIVO", "CSO", " PRÉ-PAGO");
        LinhaDTO linhaDTO3 = new LinhaDTO("1156798452", "CANCELADO", "CSO", " PRÉ-PAGO");
        LinhaDTO linhaDTO4 = new LinhaDTO("1131245798", "CANCELADO", "CSO", " PRÉ-PAGO");

        linhasDTO = List.of(linhaDTO1, linhaDTO2, linhaDTO3, linhaDTO4);
    }

    @Test
    public void testGetLinhas_validCpfCnpjAndAtivoStatus() throws Exception {
        when(linhaService.findLinhasByCpfCnpjAndStatusLinha(anyString(), anyString())).thenReturn(linhasDTO);

        mockMvc.perform(get("/linha")
                        .param("cpf_cnpj", "36587896000105")
                        .param("status", "ATIVO"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetLinhas_invalidCpfCnpj() throws Exception {
        when(linhaService.findLinhasByCpfCnpjAndStatusLinha(anyString(), anyString())).thenReturn(linhasDTO);

        mockMvc.perform(get("/linha")
                        .param("cpf_cnpj", ""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
