package com.tankclean.TankClean.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
            var cache = new org.springframework.web.cors.CorsConfiguration();
            cache.setAllowedOrigins(java.util.List.of("*")); // Or your specific frontend URL
            cache.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            cache.setAllowedHeaders(java.util.List.of("*"));
            return cache;
        }))
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // 1. Public Endpoints
                        .requestMatchers("/auth/**").permitAll()

                        // 2. User Profile
                        .requestMatchers("/users/me").hasAnyAuthority("ADMIN", "CUSTOMER")

                        // 3. Service Management
                        .requestMatchers(HttpMethod.GET, "/service/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers("/service/**").hasAuthority("ADMIN")

                        // 4. Booking Management (FIXED: Added POST for /booking)
                        .requestMatchers("/booking/my").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/booking", "/booking/**").hasAnyAuthority("ADMIN", "CUSTOMER")
//                        .requestMatchers(HttpMethod.POST, "/booking/**").hasAnyAuthority("ADMIN", "CUSTOMER")
//                        .requestMatchers(HttpMethod.POST, "/booking").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/booking/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/booking/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/booking").hasAuthority("ADMIN")

                        //  Address Management
                        .requestMatchers("/address/my").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/address").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/address/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/address").hasAuthority("ADMIN")
                        .requestMatchers("/address/user/**").hasAuthority("ADMIN")

                        // 6. Admin Only Sections
                        .requestMatchers("/users/**").hasAuthority("ADMIN")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/workers/**").hasAuthority("ADMIN")

                        // 7. General Features
                        .requestMatchers("/payment/**").hasAnyAuthority("ADMIN", "CUSTOMER")

                        // FeedBack mangement
                                .requestMatchers("/feedback/**").hasAnyAuthority("ADMIN", "CUSTOMER")
//                                .requestMatchers(HttpMethod.DELETE, "/feedback").hasAnyAuthority("ADMIN", "CUSTOMER")
//                                .requestMatchers(HttpMethod.PUT, "/feedback").hasAnyAuthority("ADMIN", "CUSTOMER")

                        // 8. Catch-all
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}