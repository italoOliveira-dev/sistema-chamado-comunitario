package com.projeto.integracao.servico_chamado_comunitario.service.impl;

import com.projeto.integracao.servico_chamado_comunitario.clients.UsuarioServiceFeignClient;
import com.projeto.integracao.servico_chamado_comunitario.dto.request.NovoChamadoRequest;
import com.projeto.integracao.servico_chamado_comunitario.dto.response.ChamadoResponse;
import com.projeto.integracao.servico_chamado_comunitario.entities.Chamado;
import com.projeto.integracao.servico_chamado_comunitario.entities.StatusChamado;
import com.projeto.integracao.servico_chamado_comunitario.exceptions.UsuarioNaoEncontradoException;
import com.projeto.integracao.servico_chamado_comunitario.mapper.ChamadoMapper;
import com.projeto.integracao.servico_chamado_comunitario.models.Usuario;
import com.projeto.integracao.servico_chamado_comunitario.repository.ChamadoRepository;
import com.projeto.integracao.servico_chamado_comunitario.service.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChamadoServiceImpl implements ChamadoService {

    private final UsuarioServiceFeignClient usuarioFeign;
    private final ChamadoMapper chamadoMapper;
    private final ChamadoRepository chamadoRepository;

    @Override
    public ChamadoResponse criarChamado(final NovoChamadoRequest chamadoRequest) {
        Usuario usuario = usuarioFeign.obterUsuario(chamadoRequest.usuarioId()).getBody();

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException(String.format("Usuario com id: %s não foi encontrado", chamadoRequest.usuarioId()));
        }

        Chamado chamadoSalvo = chamadoRepository.save(construirChamado(chamadoRequest, usuario));

        return chamadoMapper.toResponse(chamadoSalvo);
    }

    @Override
    public List<ChamadoResponse> listarPorUsuario(final String usuarioId) {
        List<Chamado> chamadosPorUsuario = chamadoRepository.findByUsuarioId(usuarioId);
        return chamadosPorUsuario.stream().map(chamadoMapper::toResponse).toList();
    }

    private Chamado construirChamado(NovoChamadoRequest chamadoRequest, Usuario usuario) {
        return Chamado.builder()
                .titulo(chamadoRequest.titulo())
                .descricao(chamadoRequest.descricao())
                .status(StatusChamado.ABERTO)
                .usuarioId(usuario.getId())
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }
}
