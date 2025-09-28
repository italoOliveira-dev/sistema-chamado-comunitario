package com.projeto.integracao.servico_chamado_comunitario.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.integracao.servico_chamado_comunitario.dto.request.NovoChamadoRequest;
import com.projeto.integracao.servico_chamado_comunitario.dto.response.ChamadoResponse;
import com.projeto.integracao.servico_chamado_comunitario.service.ChamadoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChamadoControllerImpl.class)
@ActiveProfiles("test")
public class ChamadoControllerImplTest {

    private static final String BASE_URI = "/api/chamados";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ChamadoService chamadoService;

    @Test
    void criarChamadoDeveRetornar200() throws Exception {

        var request = new NovoChamadoRequest("Título teste", "Descrição teste", "123");
        var response = ChamadoResponse.builder()
                .id(1L)
                .titulo("Título teste")
                .descricao("Descrição teste")
                .usuarioId("123")
                .status("ABERTO")
                .criadoEm(LocalDateTime.now())
                .build();

        Mockito.when(chamadoService.criarChamado(Mockito.any(NovoChamadoRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post(BASE_URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.titulo").value("Título teste"))
                .andExpect(jsonPath("$.descricao").value("Descrição teste"))
                .andExpect(jsonPath("$.usuarioId").value("123"));
    }

    @Test
    void listarChamadosDeveRetornar200() throws Exception {

        var usuarioId = "123";
        var responseList = List.of(
                ChamadoResponse.builder().id(1L).titulo("Título 1").descricao("Desc 1").usuarioId(usuarioId).status("ABERTO").criadoEm(LocalDateTime.now()).build(),
                ChamadoResponse.builder().id(2L).titulo("Título 2").descricao("Desc 2").usuarioId(usuarioId).status("ABERTO").criadoEm(LocalDateTime.now()).build()
        );

        Mockito.when(chamadoService.listarPorUsuario(usuarioId))
                .thenReturn(responseList);

        mockMvc.perform(get(BASE_URI + "/" + usuarioId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[0].usuarioId").value(usuarioId))
                .andExpect(jsonPath("$[1].usuarioId").value(usuarioId));
    }
}
