package io.simon.sandboxapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class Jwt2AuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final Jwt2AuthoritiesConverter jwt2AuthoritiesConverter;

    public Jwt2AuthenticationConverter(Jwt2AuthoritiesConverter jwt2AuthoritiesConverter) {
        this.jwt2AuthoritiesConverter = jwt2AuthoritiesConverter;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        return new JwtAuthenticationToken(jwt, jwt2AuthoritiesConverter.convert(jwt));
    }
}
