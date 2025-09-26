package com.projeto.integracao.servico_autenticacao.models;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Builder
public class RefreshToken {

    @Id
    private String id;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}
