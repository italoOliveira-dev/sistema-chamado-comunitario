package com.projeto.integracao.servico_autenticacao.dtos.responses;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(String refreshToken) {
}
