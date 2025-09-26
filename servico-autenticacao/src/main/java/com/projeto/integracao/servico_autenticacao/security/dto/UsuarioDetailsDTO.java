package com.projeto.integracao.servico_autenticacao.security.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Builder
public class UsuarioDetailsDTO implements UserDetails {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> autorizacoes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.autorizacoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
