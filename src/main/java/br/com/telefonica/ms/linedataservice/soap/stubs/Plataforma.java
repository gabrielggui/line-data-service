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
@XmlRootElement(name = "plataforma")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "plataforma",
        namespace = "http://www.vivo.com.br/MC/Catalogo",
        propOrder = {
                "nome"
        })
public class Plataforma {
    private String nome;
}
