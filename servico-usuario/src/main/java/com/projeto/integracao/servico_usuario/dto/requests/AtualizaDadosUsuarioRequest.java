package com.projeto.integracao.servico_usuario.dto.requests;

import com.projeto.integracao.servico_usuario.dto.EnderecoDto;
import com.projeto.integracao.servico_usuario.dto.TelefoneDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record AtualizaDadosUsuarioRequest(@Schema(description = "Nome completo do usuário", example = "Maria Oliveira")
                                          String nome,

                                          @Schema(description = "E-mail do usuário", example = "maria.oliveira@email.com")
                                          String email,

                                          @Schema(description = "Endereço atualizado do usuário",
                                                  example = "{ \"rua\": \"Rua das Flores\", \"numero\": \"200\", \"complemento\": \"Casa B\", " +
                                                          "\"bairro\": \"Centro\", \"cidade\": \"São Paulo\", \"estado\": \"SP\", \"cep\": \"01000-000\" }")
                                          EnderecoDto endereco,

                                          @Schema(description = "Lista de telefones atualizados do usuário",
                                                  example = "[{ \"ddd\": \"11\", \"numero\": \"988887777\", \"tipoTelefone\": \"RESIDENCIAL\" }]")
                                          List<TelefoneDto> telefones) {
}
