package com.example.mortgage_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Esto le indica a Spring que es una clase de configuración
public class WebConfig implements WebMvcConfigurer {

    // Este método configura CORS para toda la aplicación
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a todas las rutas
                .allowedOrigins("http://138.68.5.200:8070")  // Permite solicitudes desde estos orígenes (frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Los métodos HTTP permitidos
                .allowedHeaders("*")  // Permite cualquier cabecera
                .allowCredentials(true);  // Permite que se envíen cookies o credenciales si es necesario
    }
}
