package com.pjlsoftware.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    /**
     * Default, out-of-the-box security for all APIs. Will get a 401 unauthorized unless you attach the
     * Authorization: Basic header in the curl request with the default username 'user' and the randomly generated
     * password when the spring-boot starter starts (don't ever do in production). The username and password must be
     * formatted as base64 encoded username:password. E.g.
     * <p>
     * curl -X GET -H "Authorization: Basic $(echo -n 'user:83b6a674-5f62-419e-8068-8bfc04231b4a' | base64)" https://localhost:8443/api/v1/users/ -i
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()) // Ref: https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/
                .csrf(AbstractHttpConfigurer::disable) // Ref: https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#servlet-csrf-configure-disable
        ;
        return http.build();
    }
}
