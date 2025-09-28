package com.projeto.integracao.servico_chamado_comunitario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicoChamadoComunitarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoChamadoComunitarioApplication.class, args);
	}

}
