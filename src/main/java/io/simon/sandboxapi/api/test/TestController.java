package io.simon.sandboxapi.api.test;

import io.simon.sandboxapi.api.dto.TestDto;
import io.simon.sandboxapi.client.reddit.model.RedditThread;
import io.simon.sandboxapi.services.TopSubredditThreadsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TopSubredditThreadsService topSubredditThreadsService;

    public TestController(TopSubredditThreadsService topSubredditThreadsService) {
        this.topSubredditThreadsService = topSubredditThreadsService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_default_user')")
    public TestDto test() {
        return new TestDto(Instant.now().toEpochMilli() + " - API says ¡Hola!");
    }

    @GetMapping("/reddit")
    public List<RedditThread> testReddit() throws IOException {
        return topSubredditThreadsService.getSubredditTopThreads("/askHistorians");
    }

}
