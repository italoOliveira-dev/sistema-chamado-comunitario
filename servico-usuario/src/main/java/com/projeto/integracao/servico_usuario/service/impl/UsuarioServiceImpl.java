package com.projeto.integracao.servico_usuario.service.impl;

import com.projeto.integracao.servico_usuario.dto.requests.AtualizaDadosUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.requests.NovoUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioCredentialsResponse;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioResponse;
import com.projeto.integracao.servico_usuario.entity.RolePerfilEnum;
import com.projeto.integracao.servico_usuario.entity.Usuario;
import com.projeto.integracao.servico_usuario.exception.EmailExistenteException;
import com.projeto.integracao.servico_usuario.exception.SenhaDiferenteException;
import com.projeto.integracao.servico_usuario.exception.UsuarioNaoEncontradoException;
import com.projeto.integracao.servico_usuario.mapper.UsuarioMapper;
import com.projeto.integracao.servico_usuario.repository.UsuarioRepository;
import com.projeto.integracao.servico_usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    @Override
    @Transactional
    public UsuarioResponse salvar(NovoUsuarioRequest request) {
        if (!request.senha().equals(request.confirmarSenha())) {
            throw new SenhaDiferenteException("As senhas não conferem!");
        }

        if(usuarioRepository.existsByEmail(request.email())) {
            throw new EmailExistenteException("Já existe um usuário com este email: " + request.email());
        }

        Usuario usuario = mapper.toEntityUsuario(request);
        usuario.setPerfis(Set.of(RolePerfilEnum.CIDADAO));
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        return mapper.fromEntityUsuario(usuarioRepository.save(usuario));
    }

    @Override
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream().map(mapper::fromEntityUsuario).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(String id) {
        return mapper.fromEntityUsuario(obterPorId(id));
    }

    @Override
    public UsuarioResponse atualizar(String id, AtualizaDadosUsuarioRequest request) {
        Usuario usuario = obterPorId(id);
        Usuario usuarioAtualizado = atualizaDadosUsuario(request, usuario);
        return mapper.fromEntityUsuario(usuarioAtualizado);
    }

    @Override
    public void deletarPorId(String id) {
        usuarioRepository.delete(obterPorId(id));
    }

    @Override
    public UsuarioCredentialsResponse buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsuarioNaoEncontradoException(
                String.format("Usuario com email %s não encontrado!", email)
        ));

        return usuarioMapper.toCredentialUsers(usuario);
    }

    private Usuario obterPorId(String id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException(String.format("Usuário com id %s não existe!", id))
        );
    }

    private Usuario atualizaDadosUsuario(AtualizaDadosUsuarioRequest request,  Usuario usuario) {
        if (request.nome() != null && !request.nome().isBlank()) {
            usuario.setNome(request.nome());
        }

        if (request.email() != null && !request.email().isBlank()) {
            if (usuarioRepository.existsByEmail(request.email()) && !usuario.getEmail().equals(request.email())) {
                throw new EmailExistenteException("Já existe um usuário com este email: " + request.email());
            }
            usuario.setEmail(request.email());
        }

        if (request.endereco() != null) {
            usuario.setEndereco(mapper.addEndereco(request.endereco()));
        }

        if (request.telefones() != null && !request.telefones().isEmpty()) {
            usuario.setTelefones(mapper.addTelefones(request.telefones()));
        }

        return usuarioRepository.save(usuario);
    }
}
