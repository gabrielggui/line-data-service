package br.com.telefonica.ms.linedataservice.soap.stubs;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@XmlRootElement(name = "sistemaOrigem")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sistemaOrigem",
        namespace = "http://www.vivo.com.br/MC/Catalogo",
        propOrder = {
                "sigla"
        })
public class SistemaOrigem {
    private String sigla;
}
