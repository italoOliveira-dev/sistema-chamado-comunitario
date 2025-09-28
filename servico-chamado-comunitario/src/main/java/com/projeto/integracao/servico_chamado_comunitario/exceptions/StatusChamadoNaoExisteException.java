package com.projeto.integracao.servico_chamado_comunitario.exceptions;

public class StatusChamadoNaoExisteException extends RuntimeException {
    public StatusChamadoNaoExisteException(String message) {
        super(message);
    }
}
