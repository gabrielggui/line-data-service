package br.com.telefonica.ms.linedataservice.soap.stubs;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
