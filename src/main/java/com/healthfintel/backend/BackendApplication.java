package com.healthfintel.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "HeAlthFintel Backend With Spring OpenAPI",
				version = "1.0",
				description = "API documentation for HeAlthFintel Backend"
		)
)
@EnableFeignClients(basePackages = "com.healthfintel.backend")
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}



}
