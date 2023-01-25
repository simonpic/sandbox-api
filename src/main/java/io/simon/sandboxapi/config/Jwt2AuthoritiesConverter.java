package io.simon.sandboxapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Component
public class Jwt2AuthoritiesConverter implements Converter<Jwt, Collection<? extends GrantedAuthority>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Jwt2AuthoritiesConverter.class);

    @Override
    public Collection<? extends GrantedAuthority> convert(Jwt jwt) {
        var claims = jwt.getClaims();
        var realmAccess = (Map<String, Object>) claims.get("realm_access");
        var roles = (Collection<String>) realmAccess.get("roles");

        LOGGER.debug("Extracted roles {} from token for user {}", roles, claims.get("sub"));

        return roles.stream().map(role -> "ROLE_" + role).map(SimpleGrantedAuthority::new).toList();
    }
}
