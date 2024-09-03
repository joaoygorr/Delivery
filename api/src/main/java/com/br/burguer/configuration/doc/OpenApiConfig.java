package com.br.burguer.configuration.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Burguer Menu")
                        .description("Snack management system")
                        .version("v1")
                        .termsOfService("https://github.com/joaoygorr/Burguer-Menu")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/joaoygorr/Burguer-Menu")));
// TODO: TOKEN FOR REQUESTS
//                .components(new Components()
//                        .addSecuritySchemes("bearer-key",
//                                new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")
//                                        .in(SecurityScheme.In.HEADER)));
    }

}
