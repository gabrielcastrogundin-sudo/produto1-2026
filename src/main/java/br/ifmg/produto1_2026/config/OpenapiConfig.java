package br.ifmg.produto1_2026.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenapiConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Produto 1")
                    .description("Api fornece dados para as vendas dos produtos")
                    .version("1.0")
                    .summary("Loja produtos"));
    }
}
