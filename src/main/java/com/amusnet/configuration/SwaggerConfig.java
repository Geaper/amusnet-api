package com.amusnet.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(
        info = @Info(
                title = "Amusnet Gaming Portal API",
                version = "1.0.0",
                description = "REST API for Amusnet Gaming Portal"
        ),
        servers = {
                @Server(url = "http://localhost:8081/amusnet", description = "Dev Server")
        }
)
@Configuration
public class SwaggerConfig {

}
