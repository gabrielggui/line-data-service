package br.com.telefonica.ms.linedataservice.unit.controller;

import br.com.telefonica.ms.linedataservice.controller.LinhaController;
import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.service.impl.LinhaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LinhaController.class)
public class LinhaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LinhaServiceImpl linhaService;

    private List<LinhaDto> linhasDTO;
//
//    @BeforeEach
//    public void setUp() {
//        LinhaDto linhaDTO1 = new LinhaDto("1185988085", "ATIVO", "CSO", " PRÉ-PAGO");
//        LinhaDto linhaDTO2 = new LinhaDto("1176869788", "ATIVO", "CSO", " PRÉ-PAGO");
//        LinhaDto linhaDTO3 = new LinhaDto("1156798452", "CANCELADO", "CSO", " PRÉ-PAGO");
//        LinhaDto linhaDTO4 = new LinhaDto("1131245798", "CANCELADO", "CSO", " PRÉ-PAGO");
//
//        linhasDTO = List.of(linhaDTO1, linhaDTO2, linhaDTO3, linhaDTO4);
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpj_valid() throws Exception {
//        when(linhaService.findLinhasByCnpj(anyString()))
//                .thenReturn(linhasDTO);
//
//        mockMvc.perform(get("/linha")
//                        .param("cpf_cnpj", "21666736007")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray());
//    }
//
//    @Test
//    public void testFindLinhasByCpfCnpj_withoutCpfCnpj() throws Exception {
//        when(linhaService.findLinhasByCpfCnpj(anyString()))
//                .thenReturn(linhasDTO);
//
//        mockMvc.perform(get("/linha")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }

}
