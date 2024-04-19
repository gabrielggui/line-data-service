package br.com.telefonica.ms.linedataservice.soap.stubs;

import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "buscarListaLinhasPorCPFCNPJRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscarListaLinhasPorCPFCNPJRequest",
        namespace = "http://www.vivo.com.br/SN/Linha",
        propOrder = {
                "numeroCPFCNPJ"
        })
public class BuscarListaLinhasPorCPFCNPJRequest {
    @XmlElement(required = true)
    private String numeroCPFCNPJ;
}
