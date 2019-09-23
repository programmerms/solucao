package com.solucao.integracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class IntegracaoApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return  application.sources(IntegracaoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(IntegracaoApplication.class, args);
	}

}
