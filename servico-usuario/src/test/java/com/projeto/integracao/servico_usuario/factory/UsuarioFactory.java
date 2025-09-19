package com.projeto.integracao.servico_usuario.factory;

import com.projeto.integracao.servico_usuario.dto.EnderecoDto;
import com.projeto.integracao.servico_usuario.dto.TelefoneDto;
import com.projeto.integracao.servico_usuario.dto.requests.AtualizaDadosUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.requests.NovoUsuarioRequest;

import java.util.List;
import java.util.UUID;

public class UsuarioFactory {

    public static NovoUsuarioRequest novoUsuarioRequest() {
        return new NovoUsuarioRequest(
                "João Silva+" + UUID.randomUUID(),
                "joao.silva+" + UUID.randomUUID() + "@email.com",
                "Senha123!",
                "Senha123!",
                new EnderecoDto("Av. Ceara", "1000", "Apto 101", "Bela Vista", "Fortaleza", "CE", "01310-100"),
                List.of(new TelefoneDto("85", "999999999", "CELULAR"))
        );
    }

    public static AtualizaDadosUsuarioRequest atualizaDadosUsuarioRequest() {
        return new AtualizaDadosUsuarioRequest(
                "Maria Oliveira",
                "maria.oliveira@email.com",
                new EnderecoDto(
                        "Rua das Flores",
                        "200",
                        "Casa B",
                        "Centro",
                        "São Paulo",
                        "SP",
                        "01000-000"
                ),
                List.of(
                        new TelefoneDto("11", "988887777", "WHATSAPP")
                )
        );
    }
}
