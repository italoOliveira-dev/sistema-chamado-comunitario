package com.projeto.integracao.servico_chamado_comunitario.clients;

import com.projeto.integracao.servico_chamado_comunitario.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "servico-usuario-api",
        path = "/api/usuarios"
)
public interface UsuarioServiceFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<Usuario> obterUsuario(@PathVariable String id);
}
