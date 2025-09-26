package com.projeto.integracao.servico_autenticacao.service.impl;

import com.projeto.integracao.servico_autenticacao.dtos.responses.RefreshTokenResponse;
import com.projeto.integracao.servico_autenticacao.exceptions.RefreshTokenExpiredException;
import com.projeto.integracao.servico_autenticacao.exceptions.ResourceNotFoundException;
import com.projeto.integracao.servico_autenticacao.models.RefreshToken;
import com.projeto.integracao.servico_autenticacao.repository.RefreshTokenRepository;
import com.projeto.integracao.servico_autenticacao.security.dto.UsuarioDetailsDTO;
import com.projeto.integracao.servico_autenticacao.service.RefreshTokenService;
import com.projeto.integracao.servico_autenticacao.service.UsuarioDetailsServiceImpl;
import com.projeto.integracao.servico_autenticacao.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenImpl implements RefreshTokenService {

    private final JWTUtils jWTUtils;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UsuarioDetailsServiceImpl usuarioDetailsService;

    @Value("${jwt.expiration-sec}")
    private Long expirationSec;

    @Override
    public RefreshToken salvar(final String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusSeconds(expirationSec))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshTokenResponse refreshToken(final String refreshTokenId) {
        final var refreshToken = refreshTokenRepository.findById(refreshTokenId).orElseThrow(
                () -> new ResourceNotFoundException("Refresh token não encontrado: " + refreshTokenId)
        );

        if (refreshToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RefreshTokenExpiredException("Refresh token expirado! id: " + refreshTokenId);
        }

        String token = jWTUtils.generateToken((UsuarioDetailsDTO) usuarioDetailsService.loadUserByUsername(refreshToken.getEmail()));

        return RefreshTokenResponse.builder()
                .refreshToken(token)
                .build();
    }
}
