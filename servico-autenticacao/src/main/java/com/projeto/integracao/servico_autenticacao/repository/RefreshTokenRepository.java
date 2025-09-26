package com.projeto.integracao.servico_autenticacao.repository;

import com.projeto.integracao.servico_autenticacao.models.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
}
