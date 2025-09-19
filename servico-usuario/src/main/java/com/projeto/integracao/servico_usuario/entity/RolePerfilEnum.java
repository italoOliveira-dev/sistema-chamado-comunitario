package com.projeto.integracao.servico_usuario.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum RolePerfilEnum {

    ADMIN("ROLE_ADMIN"),
    CIDADAO("ROLE_CIDADAO"),
    AGENTE("ROLE_AGENTE");

    private final String role;

    public static RolePerfilEnum getEnum(String role) {
        return Arrays.stream(RolePerfilEnum.values())
                .filter(roleEnum -> roleEnum.getRole().equals(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Role inválida. Valores aceitos: " + Arrays.toString(RolePerfilEnum.values())
                ));
    }

    @JsonValue
    public String toJson() {
        return role;
    }
}
