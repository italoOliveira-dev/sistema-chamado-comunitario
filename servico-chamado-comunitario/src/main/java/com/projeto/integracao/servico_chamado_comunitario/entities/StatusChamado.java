package com.projeto.integracao.servico_chamado_comunitario.entities;

import com.projeto.integracao.servico_chamado_comunitario.exceptions.StatusChamadoNaoExisteException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum StatusChamado {

    ABERTO("Aberto"),
    EM_ANDAMENTO("Em Andamento"),
    RESOLVIDO("Resolvido"),
    CANCELADO("Cancelado");

    private final String status;

    public static StatusChamado toEnum(final String status) {
        return Arrays.stream(StatusChamado.values())
                .filter(statusChamado -> statusChamado.getStatus().equals(status))
                .findFirst()
                .orElseThrow(() -> new StatusChamadoNaoExisteException("Status não existente: " +  status));
    }
}
