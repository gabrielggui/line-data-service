package br.com.telefonica.ms.linedataservice.soap.stubs;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@XmlRootElement(name = "Linhas", namespace = "http://www.vivo.com.br/MC/Assinatura")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Linhas",
        namespace = "http://www.vivo.com.br/MC/Assinatura",
        propOrder = {
                "linha"
        })
public class Linhas {
    private List<Linha> linha;
}
