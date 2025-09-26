package com.projeto.integracao.servico_usuario.mapper;

import com.projeto.integracao.servico_usuario.dto.EnderecoDto;
import com.projeto.integracao.servico_usuario.dto.TelefoneDto;
import com.projeto.integracao.servico_usuario.dto.requests.NovoUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioCredentialsResponse;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioResponse;
import com.projeto.integracao.servico_usuario.entity.Endereco;
import com.projeto.integracao.servico_usuario.entity.Telefone;
import com.projeto.integracao.servico_usuario.entity.TipoTelefoneEnum;
import com.projeto.integracao.servico_usuario.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioMapper {

    public Usuario toEntityUsuario(NovoUsuarioRequest request) {
        return Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .endereco(addEndereco(request.endereco()))
                .telefones(addTelefones(request.telefones()))
                .build();
    }

    public List<Telefone> addTelefones(List<TelefoneDto> telefones) {
        return telefones.stream().map(this::toEntityTelefone).toList();
    }

    private Telefone toEntityTelefone(TelefoneDto telefone) {
        return Telefone.builder()
                .ddd(telefone.ddd())
                .numero(telefone.numero())
                .tipoTelefone(TipoTelefoneEnum.getTipoTelefone(telefone.tipoTelefone()))
                .build();
    }

    public Endereco addEndereco(EnderecoDto endereco) {
        return Endereco.builder()
                .rua(endereco.rua())
                .numero(endereco.numero())
                .complemento(endereco.complemento())
                .bairro(endereco.bairro())
                .cidade(endereco.cidade())
                .estado(endereco.estado())
                .cep(endereco.cep())
                .build();
    }

    public UsuarioResponse fromEntityUsuario(Usuario usuario) {
        Set<String> perfisOtimizado =  new HashSet<>();
        usuario.getPerfis()
                .stream()
                .map(perfilEnum -> perfilEnum.getRole().substring(5))
                .forEach(perfisOtimizado::add);

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .endereco(usuario.getEndereco())
                .telefones(usuario.getTelefones())
                .perfis(perfisOtimizado)
                .build();
    }

    public UsuarioCredentialsResponse toCredentialUsers(Usuario usuario) {
        return UsuarioCredentialsResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .build();
    }
}
