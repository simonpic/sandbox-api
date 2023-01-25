package io.simon.sandboxapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientsConfig {

    private static final String REDDIT_BASE_URL = "https://www.reddit.com";

    @Bean("reddit_client")
    public WebClient redditClient() {
        return WebClient.builder()
                .baseUrl(REDDIT_BASE_URL)
                .build();
    }

}
