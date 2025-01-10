package com.example.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite toate rutele
                        .allowedOrigins("http://localhost:5173") // Permite cereri de pe acest frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite metodele HTTP
                        .allowedHeaders("*") // Permite toate antetele
                        .allowCredentials(true); // Permite trimiterea de cookie-uri
            }
        };
    }
}