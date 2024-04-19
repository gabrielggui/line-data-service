package br.com.telefonica.ms.linedataservice.dto;

import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;

public record LinhaDTO(String numeroLinha,
                       String statusLinha,
                       String tipoLinha,
                       String origemLinha) {

    public LinhaDTO(Linha linha) {
        this(linha.getNumeroLinha(),
                linha.getStatusAssinatura().getDescricao(),
                linha.getSistemaOrigem().getSigla(),
                linha.getTipoAssinatura().getPlataforma().getNome());
    }

}

