package com.example.pruebantt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openApiInformation() {
		Server localServer = new Server().url("http://localhost:8080")
				.description("Assessment test to java semi senior developer");

		Contact contact = new Contact().email("kergrau@hotmail.com").name("Kerley Manuel");

		Info info = new Info().contact(contact).description("This is an assessment test")
				.summary("This assessment test was developed in Sprin boot with a MariDB database and Redis cache.")
				.title("Semi senior assessment").version("V1.0.0")
				.license(new License().name("Apache 2.0").url("http://springdoc.org"));

		return new OpenAPI().info(info).addServersItem(localServer);
	}
}
