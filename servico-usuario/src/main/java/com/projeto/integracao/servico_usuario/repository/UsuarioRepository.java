package com.projeto.integracao.servico_usuario.repository;

import com.projeto.integracao.servico_usuario.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
