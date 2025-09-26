package com.projeto.integracao.servico_usuario.controller.impl;

import com.projeto.integracao.servico_usuario.controller.UsuarioController;
import com.projeto.integracao.servico_usuario.dto.requests.AtualizaDadosUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.requests.NovoUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioCredentialsResponse;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioResponse;
import com.projeto.integracao.servico_usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioService usuarioService;

    @Override
    public ResponseEntity<UsuarioResponse> novoUsuario(NovoUsuarioRequest request) {
        UsuarioResponse usuarioSalvo = usuarioService.salvar(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(usuarioSalvo.id())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioSalvo);
    }

    @Override
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @Override
    public ResponseEntity<UsuarioResponse> obterUsuario(String id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<UsuarioCredentialsResponse> obterUsuarioPorEmail(String email) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }

    @Override
    public ResponseEntity<UsuarioResponse> atualizarUsuario(String id, AtualizaDadosUsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.atualizar(id, request));
    }

    @Override
    public ResponseEntity<Void> deletarUsuario(String id) {
        usuarioService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
