package com.projeto.integracao.servico_chamado_comunitario.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericFeignException extends RuntimeException {

    private Integer status;
    private Map error;
}
