package com.projeto.integracao.servico_chamado_comunitario.repository;

import com.projeto.integracao.servico_chamado_comunitario.entities.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    List<Chamado> findByUsuarioId(String usuarioId);
}
