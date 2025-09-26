package com.projeto.integracao.servico_autenticacao.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.integracao.servico_autenticacao.dtos.requests.LoginRequest;
import com.projeto.integracao.servico_autenticacao.dtos.requests.RefreshTokenRequest;
import com.projeto.integracao.servico_autenticacao.dtos.responses.RefreshTokenResponse;
import com.projeto.integracao.servico_autenticacao.dtos.responses.TokenResponse;
import com.projeto.integracao.servico_autenticacao.models.RefreshToken;
import com.projeto.integracao.servico_autenticacao.security.JWTAuthenticationService;
import com.projeto.integracao.servico_autenticacao.service.RefreshTokenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerImplTest {

    private static final String BASE_URI = "/api/auth";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JWTAuthenticationService authenticationService;

    @MockitoBean
    private RefreshTokenService refreshTokenService;

    @Test
    void autenticarDeveRetornarStatusOk() throws Exception {

        var request = new LoginRequest("teste@email.com", "Senha123!");
        var json = objectMapper.writeValueAsString(request);

        var tokenResponse = TokenResponse.builder()
                .tipo("Bearer")
                .token("fake-jwt-token")
                .refreshToken("fake-refresh-token")
                .build();

        var refreshToken = RefreshToken.builder()
                .id("fake-refresh-token")
                .email(request.email())
                .build();

        Mockito.when(authenticationService.authenticate(Mockito.any(LoginRequest.class)))
                .thenReturn(tokenResponse);

        Mockito.when(refreshTokenService.salvar(Mockito.anyString()))
                .thenReturn(refreshToken);

        mockMvc.perform(post(BASE_URI + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake-jwt-token"))
                .andExpect(jsonPath("$.refreshToken").value("fake-refresh-token"));
    }

    @Test
    void refreshTokenDeveRetornarStatusOk() throws Exception {

        var request = new RefreshTokenRequest("refresh-token-fake");
        var json = objectMapper.writeValueAsString(request);

        var response = RefreshTokenResponse.builder()
                .refreshToken("refresh-token-fake")
                .build();

        Mockito.when(refreshTokenService.refreshToken(Mockito.anyString()))
                .thenReturn(response);

        mockMvc.perform(post(BASE_URI + "/refresh-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
