package com.projeto.integracao.servico_autenticacao.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(@NotBlank(message = "Email é obrigatório")
                           @Email(message = "Email deve ser válido")
                           @Size(max = 150, message = "Email deve ter no máximo 150 caracteres")
                           String email,
                           @NotBlank(message = "Senha é obrigatória")
                           @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
                           @Pattern(
                                   regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$",
                                   message = "Senha deve conter pelo menos 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial"
                           )
                           String senha) {
}
