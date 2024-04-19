package br.com.telefonica.ms.linedataservice.client;

import br.com.telefonica.ms.linedataservice.config.SoapClientConfig;
import br.com.telefonica.ms.linedataservice.soap.stubs.BuscarListaLinhasPorCPFCNPJRequest;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linhas;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "consulta-linha",
        url = "${linha.url}",
        configuration = SoapClientConfig.class)
public interface LinhaClient {

    @GetMapping
    Linhas findLinhasByCpfCnpj(@RequestBody BuscarListaLinhasPorCPFCNPJRequest param);
}