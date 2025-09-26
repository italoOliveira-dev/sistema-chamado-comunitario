package com.projeto.integracao.servico_usuario.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document()
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;

    private Set<RolePerfilEnum> perfis = new HashSet<>();;
    private Endereco endereco;
    private List<Telefone> telefones;
}
