package com.projeto.integracao.servico_usuario.exception.handlerException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
public class MensagemValidacaoErro extends MensagemPadraoErro {

    public record CampoErro(String nomeCampo, String mensagem) { }

    @Getter
    private List<CampoErro> campoErros;

    public void addErros(String campoErro, String mensagem) {
        this.campoErros.add(new CampoErro(campoErro, mensagem));
    }
}
