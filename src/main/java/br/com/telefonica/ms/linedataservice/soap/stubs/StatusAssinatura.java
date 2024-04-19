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
@Builder
@XmlRootElement(name = "statusAssinatura")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusAssinatura",
        namespace = "http://www.vivo.com.br/MC/Assinatura",
        propOrder = {
                "codigo",
                "descricao"
        })
public class StatusAssinatura {
    private Integer codigo;
    private String descricao;
}
