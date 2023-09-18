package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	
	@Bean
	OpenAPI springBlogPesoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Projeto blog pessoal")
					.description("Projeto blog pessoal - Generation Brasil")
					.version ("v0.0.1")
					.license(new License()
							.name("Generation Brasil")
							.url("http://brazil.generation.org/"))
					.contact(new Contact()
							.name("Fagner Tranquilino dos Santos")
							.url("https://github.com/FagnerTds")
							.email("fagnertds@gamil.com")))
				.externalDocs(new ExternalDocumentation()
						.description("GitHub")
						.url("https://github.com/FagnerTds/"));
		}
	
	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenAPICustomizer() {
	    return openApi -> {
	        openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
	                .forEach(operation -> {
	                    ApiResponses apiResponses = operation.getResponses();

	                    apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
	                    apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
	                    apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
	                    apiResponses.addApiResponse("400", createApiResponse("Erro de Requisição!"));
	                    apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
	                    apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
	                    apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
	                    apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
	                }));
	    };
	}

	private ApiResponse createApiResponse(String message) {
	    return new ApiResponse().description(message);
	}

}
