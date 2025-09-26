package com.projeto.integracao.servico_autenticacao.controller.impl;

import com.projeto.integracao.servico_autenticacao.controller.AuthController;
import com.projeto.integracao.servico_autenticacao.dtos.requests.LoginRequest;
import com.projeto.integracao.servico_autenticacao.dtos.requests.RefreshTokenRequest;
import com.projeto.integracao.servico_autenticacao.dtos.responses.RefreshTokenResponse;
import com.projeto.integracao.servico_autenticacao.dtos.responses.TokenResponse;
import com.projeto.integracao.servico_autenticacao.models.RefreshToken;
import com.projeto.integracao.servico_autenticacao.security.JWTAuthenticationService;
import com.projeto.integracao.servico_autenticacao.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final JWTAuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<TokenResponse> tokenResponse(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        TokenResponse tokenLogin = authenticationService.authenticate(loginRequest);
        RefreshToken refreshToken = refreshTokenService.salvar(loginRequest.email());
        return ResponseEntity.ok(tokenLogin.withRefreshToken(refreshToken.getId()));
    }

    @Override
    public ResponseEntity<RefreshTokenResponse> refreshToken(RefreshTokenRequest refreshTokenRequest) throws Exception {
        return ResponseEntity.ok(refreshTokenService.refreshToken(refreshTokenRequest.refreshToken()));
    }
}
