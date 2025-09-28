package com.projeto.integracao.servico_chamado_comunitario.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChamadoResponse(Long id,
                              String titulo,
                              String descricao,
                              String status,
                              String usuarioId,
                              LocalDateTime criadoEm) {
}
