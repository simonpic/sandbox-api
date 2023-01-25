package io.simon.sandboxapi.client.reddit;

import io.simon.sandboxapi.client.reddit.model.Listing;
import io.simon.sandboxapi.client.reddit.model.RedditObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RedditClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedditClient.class);


    private static final String SUBREDDIT_PATH = "/r/%s";

    private final WebClient webClient;

    public RedditClient(@Qualifier("reddit_client") WebClient webClient) {
        this.webClient = webClient;
    }

    public RedditObject<Listing> getSubredditTopLastMonth(String subreddit) {
        String path = String.format(SUBREDDIT_PATH + "/top.json", subreddit);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("sort", "top")
                        .queryParam("t", "month")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<RedditObject<Listing>>() {})
                .block();
    }
}
