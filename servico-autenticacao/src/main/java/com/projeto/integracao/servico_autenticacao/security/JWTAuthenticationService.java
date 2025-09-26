package com.projeto.integracao.servico_autenticacao.security;

import com.projeto.integracao.servico_autenticacao.dtos.requests.LoginRequest;
import com.projeto.integracao.servico_autenticacao.dtos.responses.TokenResponse;
import com.projeto.integracao.servico_autenticacao.exceptions.AuthenticationServiceException;
import com.projeto.integracao.servico_autenticacao.exceptions.BadCredentialsException;
import com.projeto.integracao.servico_autenticacao.security.dto.UsuarioDetailsDTO;
import com.projeto.integracao.servico_autenticacao.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTAuthenticationService {


    private final JWTUtils jwtUtils;
    private final AuthenticationConfiguration authenticationConfiguration;

    public TokenResponse authenticate(final LoginRequest loginRequest) {

        try {
            final AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

            final var authResult = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha())
            );

            return buildAuthenticationResponse((UsuarioDetailsDTO) authResult.getPrincipal());
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Email ou senha inválidos");
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Erro na autenticação: " + ex.getMessage());
        }
    }

    protected  TokenResponse buildAuthenticationResponse(final UsuarioDetailsDTO dto) {
        final var token = jwtUtils.generateToken(dto);
        return TokenResponse.builder()
                .tipo("Bearer")
                .token(token)
                .build();
    }
}
