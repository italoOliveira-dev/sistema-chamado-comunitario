package com.projeto.integracao.servico_usuario.exception.handlerException;

import com.projeto.integracao.servico_usuario.exception.EmailExistenteException;
import com.projeto.integracao.servico_usuario.exception.SenhaDiferenteException;
import com.projeto.integracao.servico_usuario.exception.UsuarioNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemValidacaoErro> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                       HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        var erro = MensagemValidacaoErro.builder()
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .status(status.value())
                .erro(status.getReasonPhrase())
                .mensagem("Erro na validação dos campo(s)")
                .campoErros(new ArrayList<>())
                .build();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erro.addErros(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(EmailExistenteException.class)
    public ResponseEntity<MensagemPadraoErro> handlerEmailExistenteException(EmailExistenteException ex,
                                                                             HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        var erro = MensagemPadraoErro.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .erro(status.getReasonPhrase())
                .path(request.getRequestURI())
                .mensagem(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(SenhaDiferenteException.class)
    public ResponseEntity<MensagemPadraoErro> handlerSenhaDiferenteException(SenhaDiferenteException ex,
                                                                             HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        var erro = MensagemPadraoErro.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .erro(status.getReasonPhrase())
                .path(request.getRequestURI())
                .mensagem(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<MensagemPadraoErro> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex,
                                                                             HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        var erro = MensagemPadraoErro.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .erro(status.getReasonPhrase())
                .path(request.getRequestURI())
                .mensagem(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(erro);
    }
}
