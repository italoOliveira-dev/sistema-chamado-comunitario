package com.projeto.integracao.servico_chamado_comunitario.exceptions.handlerException;

import com.projeto.integracao.servico_chamado_comunitario.exceptions.GenericFeignException;
import com.projeto.integracao.servico_chamado_comunitario.exceptions.StatusChamadoNaoExisteException;
import com.projeto.integracao.servico_chamado_comunitario.exceptions.UsuarioNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(GenericFeignException.class)
    public ResponseEntity<Map> handleGenericFeignException(final GenericFeignException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getError());
    }

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

    @ExceptionHandler(StatusChamadoNaoExisteException.class)
    public ResponseEntity<MensagemPadraoErro> handlerStatusChamadoNaoExisteException(StatusChamadoNaoExisteException ex,
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

}
