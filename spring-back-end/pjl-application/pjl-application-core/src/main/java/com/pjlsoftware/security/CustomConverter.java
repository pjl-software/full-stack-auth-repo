package com.pjlsoftware.security;

import com.pjlsoftware.entity.User;
import com.pjlsoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Autowired
    private UserRepository userRepository;

    private String principalClaimName = JwtClaimNames.SUB;

    @Override
    public final AbstractAuthenticationToken convert(Jwt jwt) {
        System.out.println("processing JWT...");

        User user = ValidateGoogleAuthToken.verifyGoogleAuthToken(jwt.getTokenValue())
                .orElseThrow(() -> new RuntimeException("Failed to validate JWT."));
        Optional<User> isAlreadyEnabledUser = userRepository.findByUsernameAndEnabledIsTrue(user.getUsername());

        List<GrantedAuthority> authorities = List.of();

        if (isAlreadyEnabledUser.isPresent()) {
            System.out.println("Is a user: " + isAlreadyEnabledUser.get().toString());
            System.out.println("Roles: " + isAlreadyEnabledUser.get().getRoles());
            authorities = isAlreadyEnabledUser.get().getRoles().stream().map(role ->
                    new SimpleGrantedAuthority(role.getName().name())
            ).collect(Collectors.toList());
        }
        System.out.println("Authorities count: " + (long) authorities.size());

//        Collection<GrantedAuthority> authorities = this.jwtGrantedAuthoritiesConverter.convert(jwt);

        String principalClaimValue = jwt.getClaimAsString(this.principalClaimName);
        System.out.println("principalClaimValue: " + principalClaimValue);
        return new JwtAuthenticationToken(jwt, authorities, principalClaimValue);
    }

    /**
     * Sets the {@link Converter Converter&lt;Jwt, Collection&lt;GrantedAuthority&gt;&gt;}
     * to use. Defaults to {@link JwtGrantedAuthoritiesConverter}.
     *
     * @param jwtGrantedAuthoritiesConverter The converter
     * @see JwtGrantedAuthoritiesConverter
     * @since 5.2
     */
    public void setJwtGrantedAuthoritiesConverter(
            Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
        Assert.notNull(jwtGrantedAuthoritiesConverter, "jwtGrantedAuthoritiesConverter cannot be null");
    }

    /**
     * Sets the principal claim name. Defaults to {@link JwtClaimNames#SUB}.
     *
     * @param principalClaimName The principal claim name
     * @since 5.4
     */
    public void setPrincipalClaimName(String principalClaimName) {
        Assert.hasText(principalClaimName, "principalClaimName cannot be empty");
        this.principalClaimName = principalClaimName;
    }

}