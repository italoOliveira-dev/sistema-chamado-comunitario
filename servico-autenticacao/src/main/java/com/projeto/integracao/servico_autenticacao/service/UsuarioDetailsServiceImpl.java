package com.projeto.integracao.servico_autenticacao.service;

import com.projeto.integracao.servico_autenticacao.repository.UsuarioRepository;
import com.projeto.integracao.servico_autenticacao.security.dto.UsuarioDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final var entity = usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return UsuarioDetailsDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .autorizacoes(entity.getPerfis()
                        .stream()
                        .map(perfil -> new SimpleGrantedAuthority(perfil.getRole())).collect(Collectors.toSet()))
                .build();
    }
}
