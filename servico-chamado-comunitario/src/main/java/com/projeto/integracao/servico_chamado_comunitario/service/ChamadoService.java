package com.projeto.integracao.servico_chamado_comunitario.service;

import com.projeto.integracao.servico_chamado_comunitario.dto.request.NovoChamadoRequest;
import com.projeto.integracao.servico_chamado_comunitario.dto.response.ChamadoResponse;

import java.util.List;

public interface ChamadoService {

    ChamadoResponse criarChamado(NovoChamadoRequest chamadoRequest);

    List<ChamadoResponse> listarPorUsuario(String usuarioId);
}
