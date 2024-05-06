package br.com.telefonica.ms.linedataservice.util;

import br.com.telefonica.ms.linedataservice.dto.LinhaDto;
import br.com.telefonica.ms.linedataservice.enums.StatusLinhaEnum;
import br.com.telefonica.ms.linedataservice.soap.stubs.Linha;

import java.util.List;

public final class LinhaUtil {

    private LinhaUtil() {
    }

    public static List<LinhaDto> mapLinhasToLinhasDtoList(List<Linha> linhas) {
        return linhas.stream().map(LinhaDto::new).toList();
    }

    public static List<Linha> filterLinhasByStatus(List<Linha> linhas, StatusLinhaEnum statusLinha) {
        return statusLinha == null ? linhas : linhas
                .stream()
                .filter(linha -> statusLinha.name().equalsIgnoreCase(linha.getStatusAssinatura().getDescricao()))
                .toList();
    }
}