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
@XmlRootElement(name = "linha")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Linha",
        namespace = "http://www.vivo.com.br/MC/Assinatura",
        propOrder = {
                "numeroLinha",
                "statusAssinatura",
                "tipoAssinatura",
                "sistemaOrigem"
        })
public class Linha {
    private String numeroLinha;
    private StatusAssinatura statusAssinatura;
    private TipoAssinatura tipoAssinatura;
    private SistemaOrigem sistemaOrigem;
}