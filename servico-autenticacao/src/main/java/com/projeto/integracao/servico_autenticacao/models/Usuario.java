package com.projeto.integracao.servico_autenticacao.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private List<RolePerfilEnum> perfis;
}
