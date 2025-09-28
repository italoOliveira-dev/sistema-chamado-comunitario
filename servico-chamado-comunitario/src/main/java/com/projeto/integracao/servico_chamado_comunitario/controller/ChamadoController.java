package com.projeto.integracao.servico_chamado_comunitario.controller;

import com.projeto.integracao.servico_chamado_comunitario.dto.request.NovoChamadoRequest;
import com.projeto.integracao.servico_chamado_comunitario.dto.response.ChamadoResponse;
import com.projeto.integracao.servico_chamado_comunitario.exceptions.handlerException.MensagemPadraoErro;
import com.projeto.integracao.servico_chamado_comunitario.exceptions.handlerException.MensagemValidacaoErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/chamados")
@Tag(name = "ChamadoController", description = "Controller responsável por gerenciar chamados")
public interface ChamadoController {

    @Operation(summary = "Criar um novo chamado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ChamadoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemValidacaoErro.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemPadraoErro.class)
                    )
            )
    })
    @PostMapping
    ResponseEntity<ChamadoResponse> criar(@RequestBody @Valid NovoChamadoRequest chamadoRequest);

    @Operation(summary = "Listar chamados por usuários")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ChamadoResponse.class)
                    )
            )
    })
    @GetMapping("/{usuarioId}")
    ResponseEntity<List<ChamadoResponse>> listarTodos(@PathVariable String usuarioId);
}
