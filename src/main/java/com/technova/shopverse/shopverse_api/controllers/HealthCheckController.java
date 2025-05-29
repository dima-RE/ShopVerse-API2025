package com.technova.shopverse.shopverse_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Health Check", description = "Comprueba funcionamiento del servidor")
public class HealthCheckController {
    @Operation(
            summary = "Consultar estado del servidor",
            description = "Este endpoint devuelve una respuesta si el servidor esta funcionando"
    )
    @ApiResponse(responseCode = "200", description = "Servidor funciona correctamente")
    @ApiResponse(responseCode = "NONE", description = "El servidor esta caido o no se encuentra funcionando los mapeos.")
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
