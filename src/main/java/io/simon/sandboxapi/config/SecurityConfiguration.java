package io.simon.sandboxapi.config;

import io.simon.sandboxapi.properties.CorsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
@ConditionalOnProperty(value = "sandbox-api.security.enabled", havingValue = "true", matchIfMissing = true)
public class SecurityConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CorsConfigurationSource corsConfiguration,
                                           Jwt2AuthenticationConverter jwt2AuthenticationConverter) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwt2AuthenticationConverter);
        http.cors().configurationSource(corsConfiguration);
        return http.build();
    }

    @Bean
    @Primary
    public CorsConfigurationSource corsConfigurationSource(CorsProperties corsProperties) {
        LOGGER.debug("Configuring cors with properties: {}", corsProperties);

        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(corsProperties.getAllowedOrigins());
        configuration.setAllowedMethods(corsProperties.getAllowedMethods());
        configuration.setAllowedHeaders(corsProperties.getAllowedHeaders());

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
