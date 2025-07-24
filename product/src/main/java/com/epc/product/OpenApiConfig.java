package com.epc.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Productos",
                version = "1.0.0",
                description = "API para gestión de productos"
        )
)
public class OpenApiConfig {}
