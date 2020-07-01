package com.victorpereira.mymarketplace.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final ResponseMessage m201 = customMessage1();
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201)) 
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.victorpereira.mymarketplace.resources"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("MyMarketplace API",
				"Esta API é utilizada no curso de Spring Boot do prof. Nelio Alves", 
				"Versão 1.0",
				"https://www.udemy.com/terms",
				new Contact("Victor Pereira Cordeiro", null, "victor.cordeiro2@gmail.com"),
				null, null, 
				Collections.emptyList()
																										// Extensions
		);
	}
	
	private ResponseMessage customMessage1() {
		Map<String, Header> map = new HashMap<>();
		map.put("location", new Header("location", "new resource URI", new ModelRef("string")));
		return new ResponseMessageBuilder()
				.code(201)
				.message("Recurso criado")
				.headersWithDescription(map)
				.build();
		}
}