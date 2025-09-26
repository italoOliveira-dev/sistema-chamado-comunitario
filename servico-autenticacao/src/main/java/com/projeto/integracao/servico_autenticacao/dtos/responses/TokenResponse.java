package com.projeto.integracao.servico_autenticacao.dtos.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record TokenResponse(String token, String tipo, String refreshToken) {
}
