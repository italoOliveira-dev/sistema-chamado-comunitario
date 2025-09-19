package com.projeto.integracao.servico_usuario.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.integracao.servico_usuario.dto.requests.AtualizaDadosUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.requests.NovoUsuarioRequest;
import com.projeto.integracao.servico_usuario.entity.Usuario;
import com.projeto.integracao.servico_usuario.factory.UsuarioFactory;
import com.projeto.integracao.servico_usuario.repository.UsuarioRepository;
import com.projeto.integracao.servico_usuario.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerImplTest {

    public static final String BASE_URI = "/api/usuarios";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void novoUsuarioDeveRetornarStatusCriado() throws Exception {
        final var request = UsuarioFactory.novoUsuarioRequest();
        final var json = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
        ).andExpect(
                status().isCreated()
        );

        usuarioRepository.findByEmail(request.email()).ifPresent(usuario -> usuarioRepository.delete(usuario));
    }

    @Test
    void listarTodosDeveRetornarStatusOk() throws Exception {
        final var request1 = UsuarioFactory.novoUsuarioRequest();
        final var request2 = UsuarioFactory.novoUsuarioRequest();

        final var usuario1 = usuarioService.salvar(request1);
        final var usuario2 = usuarioService.salvar(request2);

        mockMvc.perform(get(BASE_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(jsonPath("$[0].perfis[0]").value("CIDADAO"))
                .andExpect(jsonPath("$.[1]").isNotEmpty());

        usuarioRepository.deleteAllById(List.of(usuario1.id(), usuario2.id()));
    }

    @Test
    void obterUsuarioDeveRetornarNotFoundQuandoPassarUmIdNaoExistente() throws Exception {

        mockMvc.perform(get(BASE_URI + "/{id}", "123"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.erro").value(HttpStatus.NOT_FOUND.getReasonPhrase()))
                .andExpect(jsonPath("$.path").value(BASE_URI + "/123"));
    }

    @Test
    void atualizarUsuarioDeveRetornarStatusOk() throws Exception {
        NovoUsuarioRequest request = UsuarioFactory.novoUsuarioRequest();
        usuarioService.salvar(request);

        var usuario = usuarioRepository.findByEmail(request.email()).orElseThrow();

        AtualizaDadosUsuarioRequest atualizaRequest = UsuarioFactory.atualizaDadosUsuarioRequest();

        final var json = objectMapper.writeValueAsString(atualizaRequest);

        mockMvc.perform(put(BASE_URI + "/{id}", usuario.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
        ).andExpect(status().isOk());

        usuarioRepository.deleteById(usuario.getId());
    }

    @Test
    void deletarUsuarioDeveRetornarStatusNoContentQuandoPassadoUmIdValido() throws Exception {
        NovoUsuarioRequest request = UsuarioFactory.novoUsuarioRequest();
        usuarioService.salvar(request);

        Usuario usuario = usuarioRepository.findByEmail(request.email()).orElseThrow();

        mockMvc.perform(delete(BASE_URI + "/{id}", usuario.getId()))
                .andExpect(status().isNoContent());
    }
}
