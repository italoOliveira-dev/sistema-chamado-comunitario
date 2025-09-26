package com.projeto.integracao.servico_usuario.service;

import com.projeto.integracao.servico_usuario.dto.requests.AtualizaDadosUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.requests.NovoUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioCredentialsResponse;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse salvar(NovoUsuarioRequest request);

    List<UsuarioResponse> listarTodos();

    UsuarioResponse buscarPorId(String id);

    UsuarioResponse atualizar(String id, AtualizaDadosUsuarioRequest request);

    void deletarPorId(String id);

    UsuarioCredentialsResponse buscarPorEmail(String email);
}
