package br.com.telefonica.ms.linedataservice.soap.stubs;

import br.com.telefonica.ms.linedataservice.annotation.CpfOrCnpj;
import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@XmlRootElement(name = "buscarListaLinhasPorCPFCNPJRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscarListaLinhasPorCPFCNPJRequest",
        namespace = "http://www.vivo.com.br/SN/Linha",
        propOrder = {
                "numeroCPFCNPJ"
        })
public class BuscarListaLinhasPorCPFCNPJRequest {

    @CpfOrCnpj
    @XmlElement(required = true)
    private String numeroCPFCNPJ;
}
