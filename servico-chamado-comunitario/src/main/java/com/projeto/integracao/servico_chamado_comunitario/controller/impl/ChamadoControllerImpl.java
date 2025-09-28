package com.projeto.integracao.servico_chamado_comunitario.controller.impl;

import com.projeto.integracao.servico_chamado_comunitario.controller.ChamadoController;
import com.projeto.integracao.servico_chamado_comunitario.dto.request.NovoChamadoRequest;
import com.projeto.integracao.servico_chamado_comunitario.dto.response.ChamadoResponse;
import com.projeto.integracao.servico_chamado_comunitario.service.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ChamadoControllerImpl implements ChamadoController {

    private final ChamadoService chamadoService;

    @Override
    public ResponseEntity<ChamadoResponse> criar(NovoChamadoRequest chamadoRequest) {

        return ResponseEntity.ok(chamadoService.criarChamado(chamadoRequest));
    }

    @Override
    public ResponseEntity<List<ChamadoResponse>> listarTodos(String usuarioId) {
        return ResponseEntity.ok(chamadoService.listarPorUsuario(usuarioId));
    }
}
