package com.example.FinPlanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://back-end-hack.up.railway.app") // Specify the allowed origin domains
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify the allowed HTTP methods
                        .allowedHeaders("*"); // Specify the allowed headers
            }
        };
    }
}
