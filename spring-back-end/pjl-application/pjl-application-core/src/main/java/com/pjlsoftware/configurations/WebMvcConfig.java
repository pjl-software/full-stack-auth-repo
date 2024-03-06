package com.pjlsoftware.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        long MAX_AGE_SECS = 3600;
        registry
                .addMapping("/**")
                .allowedOrigins("https://localhost:4200", "https://demo.pjlindustries.com")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type")
                .maxAge(MAX_AGE_SECS)
        ;
    }
}
