package com.remises.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author GUMARTINEZ
 *
 * Configuracion de Swagger
 * para documentar y probar los servicios REST
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Levanta Swagger, 
	 * se le define el paquete donde esta el controller de los servicios
	 * 
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(remisesApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.remises.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	/**
	 * Devuelve informacion sobre documentacion de la API
	 * 
	 * @return
	 */
	private ApiInfo remisesApiInfo() {
		return new ApiInfoBuilder()
				.title("API para REMISES")
				.version("1.0")
				.contact(new Contact(
						"Gustavo Martínez", 
						"https://github.com/gustavo-9189", 
						"gustavo-9189@hotmail.com"))
				.license("")
				.build();
	}

}
