package com.projeto.integracao.servico_autenticacao.utils;

import com.projeto.integracao.servico_autenticacao.security.dto.UsuarioDetailsDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String stringSecretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getUrlDecoder().decode(stringSecretKey);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    public String generateToken(final UsuarioDetailsDTO userDetails) {

        List<String> roles = userDetails.getAutorizacoes()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .claim("id", userDetails.getId())
                .claim("usuario", userDetails.getNome())
                .claim("autorizacoes", roles)
                .subject(userDetails.getEmail())
                .signWith(secretKey)
                .issuedAt(Date.from(Instant.now()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }
}
