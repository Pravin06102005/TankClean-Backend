package com.tankclean.TankClean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        // ✅ Allow your frontend
        config.setAllowedOrigins(List.of(
            "https://tankcleans.netlify.app/admin/login.html",
            "https://tankcleans.netlify.app/user/register.html"
        ));

        // ✅ Allowed HTTP methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));

        // ✅ Allow headers (JWT token etc.)
        config.setAllowedHeaders(List.of("*"));

        // ✅ Allow credentials (important for auth)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
