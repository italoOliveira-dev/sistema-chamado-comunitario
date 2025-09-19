package com.projeto.integracao.servico_usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Schema(description = "Objeto que representa o telefone do usuário")
public record TelefoneDto(@Schema(description = "Código de área (DDD)", example = "85")
                          @NotBlank(message = "DDD é obrigatório")
                          @Size(min = 2, max = 3, message = "DDD deve ter entre 2 e 3 dígitos")
                          @Pattern(regexp = "\\d{2,3}", message = "DDD deve conter apenas números")
                          String ddd,

                          @Schema(description = "Número de telefone", example = "999999999")
                          @NotBlank(message = "Telefone é obrigatório")
                          @Size(min = 8, max = 9, message = "Telefone deve ter entre 8 e 9 dígitos")
                          @Pattern(regexp = "\\d{8,9}", message = "Telefone deve conter apenas números")
                          String numero,

                          @Schema(description = "Tipo de telefone", example = "CELULAR")
                          @NotBlank(message = "Tipo de telefone é obrigatório")
                          @Pattern(regexp = "CELULAR|WHATSAPP|FIXO",
                                  message = "Tipo de telefone deve ser: CELULAR, WHATSAPP, ou FIXO")
                          String tipoTelefone) {
}
