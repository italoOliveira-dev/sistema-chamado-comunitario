package com.projeto.integracao.servico_chamado_comunitario.config;

import com.projeto.integracao.servico_chamado_comunitario.decoders.RetrieveMessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder defaultErrorDecoder() {
        return new RetrieveMessageErrorDecoder();
    }
}
