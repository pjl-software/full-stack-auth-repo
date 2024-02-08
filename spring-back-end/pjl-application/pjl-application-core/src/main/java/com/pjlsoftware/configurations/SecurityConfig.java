package com.pjlsoftware.configurations;

import com.pjlsoftware.repository.UserRepository;
import com.pjlsoftware.security.CustomJwtAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// OAuth 2.0 Resource Server JWT - https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html#oauth2resourceserver-jwt-authorization-extraction
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Enables @PreAuthorize
public class SecurityConfig {

    private final UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    Converter<Jwt, AbstractAuthenticationToken> customJwtAuthenticationConverter() {
        return new CustomJwtAuthenticationConverter(userRepository);
    }

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
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").authenticated()
                        //
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").authenticated()
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(customJwtAuthenticationConverter())
                        )

                )
        ;
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

    /**
     * Temporary override to better display and test the changes we are making to JWT Authentication and to validate
     * that our changes are producing the output we expect. This can be deleted in the future as it has no functional
     * purpose.
     *
     * @return - Prints a message to the console to manually validate authentication.
     */
    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successfulEvent() {
        return event -> {
            System.out.println("Successful login: " + event.getAuthentication().getClass().getSimpleName() + " " +
                    (long) event.getAuthentication().getAuthorities().size());
        };
    }
}
