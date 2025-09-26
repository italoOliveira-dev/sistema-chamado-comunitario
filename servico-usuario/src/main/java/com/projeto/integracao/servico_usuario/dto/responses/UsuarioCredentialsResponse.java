package com.projeto.integracao.servico_usuario.dto.responses;

import com.projeto.integracao.servico_usuario.entity.RolePerfilEnum;
import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioCredentialsResponse(String id, String nome, String email, String senha, List<RolePerfilEnum> roles) {
}
