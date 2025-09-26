package com.projeto.integracao.servico_autenticacao.exceptions;

public class AuthenticationServiceException extends RuntimeException {
    public AuthenticationServiceException(String message) {
        super(message);
    }
}
