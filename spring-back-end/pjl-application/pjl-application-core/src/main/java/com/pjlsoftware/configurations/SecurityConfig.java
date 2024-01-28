package com.pjlsoftware.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Implementing OAuth2 authentication via JSON Web Tokens (JWT). Setting up the SecurityFilterChain with:
     * - CORS: May not be needed anymore, but was in previous versions of Spring
     * - CSRF: Disabled to allow default disable HTTP Methods like POST to work with valid authentication
     * - Authorization: Deny all request by default while allowing specific path patterns
     * - The actuator health check is publicly accessible without authentication
     * - The Custom Controller requires authentication and supports GET/POST/PUT requests only.
     * <p>
     * Example Authorization Usage:
     * curl -X GET -H "Authorization: Bearer {JWT}"  https://localhost:8443/api/v1/users/
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        //
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").authenticated()
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }

    /**
     * For now, using the single decoder registration with Google since that's the only authentication I'm supporting
     * in this branch. Maybe you can add more.
     * <p>
     * Ref: <a href="https://github.com/spring-projects/spring-security/issues/10943">...</a>
     *
     * @return - JwtDecoder with issuerLocation matching "iss" from decoded Google JWT Access Token here:
     * <a href="https://jwt.io/">...</a>
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("https://accounts.google.com");
    }
}