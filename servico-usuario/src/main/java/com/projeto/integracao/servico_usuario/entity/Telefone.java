package com.projeto.integracao.servico_usuario.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Telefone {

    private String ddd;
    private String numero;
    private TipoTelefoneEnum tipoTelefone;
}
