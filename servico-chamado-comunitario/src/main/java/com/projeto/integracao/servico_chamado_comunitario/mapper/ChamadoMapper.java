package com.projeto.integracao.servico_chamado_comunitario.mapper;

import com.projeto.integracao.servico_chamado_comunitario.dto.response.ChamadoResponse;
import com.projeto.integracao.servico_chamado_comunitario.entities.Chamado;
import org.springframework.stereotype.Component;

@Component
public class ChamadoMapper {
    public ChamadoResponse toResponse(Chamado chamadoSalvo) {

        return ChamadoResponse.builder()
                .id(chamadoSalvo.getId())
                .titulo(chamadoSalvo.getTitulo())
                .descricao(chamadoSalvo.getDescricao())
                .usuarioId(chamadoSalvo.getUsuarioId())
                .status(chamadoSalvo.getStatus().name())
                .criadoEm(chamadoSalvo.getCriadoEm())
                .build();
    }
}
