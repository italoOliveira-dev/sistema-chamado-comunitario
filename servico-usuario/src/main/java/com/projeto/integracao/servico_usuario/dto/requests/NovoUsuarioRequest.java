package com.projeto.integracao.servico_usuario.dto.requests;

import com.projeto.integracao.servico_usuario.dto.EnderecoDto;
import com.projeto.integracao.servico_usuario.dto.TelefoneDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.With;

import java.util.List;

@With
public record NovoUsuarioRequest(@Schema(description = "Nome completo do usuário", example = "João Silva")
                                 @NotBlank(message = "Nome é obrigatório")
                                 @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
                                 String nome,

                                 @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
                                 @NotBlank(message = "Email é obrigatório")
                                 @Email(message = "Email deve ser válido")
                                 @Size(max = 150, message = "Email deve ter no máximo 150 caracteres")
                                 String email,

                                 @Schema(description = "Senha de acesso do usuário", example = "Senha123!")
                                 @NotBlank(message = "Senha é obrigatória")
                                 @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
                                 @Pattern(
                                         regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$",
                                         message = "Senha deve conter pelo menos 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial"
                                 )
                                 String senha,

                                 @Schema(description = "Confirmação da senha", example = "Senha123!")
                                 @NotBlank(message = "Confirmação de senha é obrigatória")
                                 String confirmarSenha,

                                 @Schema(description = "Endereço do usuário")
                                 @NotNull(message = "Endereço é obrigatório")
                                 @Valid
                                 EnderecoDto endereco,

                                 @Schema(description = "Lista de telefones do usuário")
                                 @NotEmpty(message = "Pelo menos um telefone é obrigatório")
                                 @Valid
                                 List<@Valid TelefoneDto> telefones) {
}
