package dev.bank.api.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI api() {
        var api = new OpenAPI();

        var information = new Info();
        information.title("Bank's API");
        information.description("A portfolio's project to simulate a real bank");

        return api.info(information);
    }
}
