package com.projeto.integracao.servico_usuario.controller;

import com.projeto.integracao.servico_usuario.dto.requests.AtualizaDadosUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.requests.NovoUsuarioRequest;
import com.projeto.integracao.servico_usuario.dto.responses.UsuarioResponse;
import com.projeto.integracao.servico_usuario.exception.handlerException.MensagemPadraoErro;
import com.projeto.integracao.servico_usuario.exception.handlerException.MensagemValidacaoErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "UsuarioController", description = "Controller responsável pela operações de usuários")
@RequestMapping("/api/usuarios")
public interface UsuarioController {

    @Operation(summary = "Salvar novo usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioResponse.class)
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
                    responseCode = "409",
                    description = "Conflict",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemPadraoErro.class)
                    )
            ),
    })
    @PostMapping
    ResponseEntity<UsuarioResponse> novoUsuario(@RequestBody @Valid final NovoUsuarioRequest request);

    @Operation(summary = "Listar todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários listados com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioResponse.class)
                    )
            )
    })
    @GetMapping
    ResponseEntity<List<UsuarioResponse>> listarTodos();

    @Operation(summary = "Obter usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemPadraoErro.class)
                    )
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<UsuarioResponse> obterUsuario(@Parameter(description = "Usuario id", required = true, example = "68c4cdc1e0b991697e5c2e9d")
                                                 @PathVariable String id);

    @Operation(summary = "Atualizar dados do usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário atualizado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida (ex.: campos obrigatórios inválidos)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemValidacaoErro.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemPadraoErro.class)
                    )
            )
    })
    @PutMapping("/{id}")
    ResponseEntity<UsuarioResponse> atualizarUsuario(@Parameter(description = "Usuario id", required = true, example = "68c4cdc1e0b991697e5c2e9d")
                                                     @PathVariable String id,
                                                     @RequestBody AtualizaDadosUsuarioRequest request);

    @Operation(summary = "Deletar usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário deletado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemPadraoErro.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarUsuario(@Parameter(description = "Usuario id", required = true, example = "68c4cdc1e0b991697e5c2e9d")
                                        @PathVariable String id);
}
