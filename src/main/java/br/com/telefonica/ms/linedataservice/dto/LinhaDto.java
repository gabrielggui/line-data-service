package br.com.telefonica.ms.linedataservice.dto;

import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;

public record LinhaDto(String numeroLinha,
                       String statusLinha,
                       String tipoLinha,
                       String origemLinha) {

    public LinhaDto(Linha linha) {
        this(linha.getNumeroLinha(),
                linha.getStatusAssinatura().getDescricao(),
                linha.getSistemaOrigem().getSigla(),
                linha.getTipoAssinatura().getPlataforma().getNome());
    }

}

