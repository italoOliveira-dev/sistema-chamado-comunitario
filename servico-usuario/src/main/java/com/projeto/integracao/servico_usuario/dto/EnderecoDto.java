package com.projeto.integracao.servico_usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Objeto que representa o endereço do usuário")
public record EnderecoDto(@Schema(description = "Nome da rua", example = "Av. Ceara")
                          @NotBlank(message = "Rua é obrigatória")
                          @Size(max = 200, message = "Rua deve ter no máximo 200 caracteres")
                          String rua,

                          @Schema(description = "Número do endereço", example = "1000")
                          @NotBlank(message = "Número é obrigatório")
                          @Size(max = 10, message = "Número deve ter no máximo 10 caracteres")
                          String numero,

                          @Schema(description = "Complemento do endereço (ex.: apartamento, bloco)", example = "Apto 101")
                          @Size(max = 50, message = "Complemento deve ter no máximo 50 caracteres")
                          String complemento,

                          @Schema(description = "Bairro do endereço", example = "Bela Vista")
                          @NotBlank(message = "Bairro é obrigatório")
                          @Size(max = 100, message = "Bairro deve ter no máximo 100 caracteres")
                          String bairro,

                          @Schema(description = "Cidade do endereço", example = "Fortaleza")
                          @NotBlank(message = "Cidade é obrigatória")
                          @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
                          String cidade,

                          @Schema(description = "Estado do endereço (UF)", example = "CE")
                          @NotBlank(message = "Estado é obrigatório")
                          @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
                          @Pattern(regexp = "[A-Z]{2}", message = "Estado deve ser sigla (ex: SP, RJ)")
                          String estado,

                          @Schema(description = "CEP do endereço", example = "01310-100")
                          @NotBlank(message = "CEP é obrigatório")
                          @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP deve estar no formato 00000-000")
                          String cep) {
}
