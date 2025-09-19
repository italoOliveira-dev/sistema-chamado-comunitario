package com.projeto.integracao.servico_usuario.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TipoTelefoneEnum {

    CELULAR("CELULAR"),
    WHATSAPP("WHATSAPP"),
    FIXO("FIXO");

    private final String tipoTelefone;

    public static TipoTelefoneEnum getTipoTelefone(String tipoTelefone) {
        return Arrays.stream(TipoTelefoneEnum.values())
                .filter(tipoEnum -> tipoEnum.getTipoTelefone().equals(tipoTelefone))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de telefone inválido: " + tipoTelefone));
    }

}
