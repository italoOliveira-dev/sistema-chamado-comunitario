package com.projeto.integracao.servico_autenticacao.service;

import com.projeto.integracao.servico_autenticacao.dtos.responses.RefreshTokenResponse;
import com.projeto.integracao.servico_autenticacao.models.RefreshToken;

import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {

    RefreshToken salvar(final String email);

    RefreshTokenResponse refreshToken(final String refreshTokenId);
}
