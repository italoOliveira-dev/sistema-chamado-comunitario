package com.projeto.integracao.servico_chamado_comunitario.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Objeto para criação de um novo chamado comunitário")
public record NovoChamadoRequest(@NotBlank
                                 @Schema(description = "Título do chamado", example = "Problema na iluminação da praça")
                                 String titulo,

                                 @NotBlank
                                 @Schema(description = "Descrição detalhada do problema", example = "O poste de luz próximo ao parquinho está apagado há vários dias e causa insegurança à noite.")
                                 String descricao,

                                 @NotBlank
                                 @Schema(description = "Identificador do usuário que abriu o chamado", example = "123")
                                 String usuarioId) {
}
