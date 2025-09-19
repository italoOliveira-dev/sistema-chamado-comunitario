package com.projeto.integracao.servico_usuario.exception;

public class SenhaDiferenteException extends RuntimeException {
    public SenhaDiferenteException(String message) {
        super(message);
    }
}
