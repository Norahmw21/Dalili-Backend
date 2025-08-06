package com.company.docreview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // allow all endpoints
                .allowedOriginPatterns("http://localhost:*") // ✅ wildcard for all local ports
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}
