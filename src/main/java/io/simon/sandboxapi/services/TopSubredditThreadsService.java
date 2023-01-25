package io.simon.sandboxapi.services;

import io.simon.sandboxapi.client.reddit.RedditClient;
import io.simon.sandboxapi.client.reddit.model.Listing;
import io.simon.sandboxapi.client.reddit.model.RedditObject;
import io.simon.sandboxapi.client.reddit.model.RedditThread;
import io.simon.sandboxapi.repository.redis.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TopSubredditThreadsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopSubredditThreadsService.class);

    private final RedditClient redditClient;
    private final RedisClient redisClient;

    public TopSubredditThreadsService(RedditClient redditClient, RedisClient redisClient) {
        this.redditClient = redditClient;
        this.redisClient = redisClient;
    }

    public List<RedditThread> getSubredditTopThreads(String subreddit) throws IOException {
        Listing listing;

        if (redisClient.keyExists(subreddit)) {
            LOGGER.info("Looking up {} subreddit top into cache.", subreddit);
            listing = redisClient.getValue(subreddit, Listing.class);
        } else {
            LOGGER.info("Fetching {} subreddit top from reddit.", subreddit);
            listing = redditClient.getSubredditTopLastMonth(subreddit).getData();
            redisClient.putValue(subreddit, listing);
        }

        return listing.getChildren().stream().map(RedditObject::getData).toList();
    }

}
