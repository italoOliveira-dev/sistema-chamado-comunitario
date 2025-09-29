package com.projeto.integracao.servico_chamado_comunitario.decoders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.integracao.servico_chamado_comunitario.exceptions.GenericFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.InputStream;
import java.util.Map;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        try(InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();

            final var error = mapper.readValue(bodyIs, Map.class);
            final var status = (Integer) error.get("status");

            return new GenericFeignException(status, error);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
