package com.projeto.integracao.servico_usuario.dto.responses;

import com.projeto.integracao.servico_usuario.entity.Endereco;
import com.projeto.integracao.servico_usuario.entity.Telefone;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record UsuarioResponse(String id,
                              String nome,
                              String email,
                              Endereco endereco,
                              List<Telefone> telefones,
                              Set<String> perfis) {
}
