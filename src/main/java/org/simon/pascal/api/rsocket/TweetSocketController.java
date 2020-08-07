package org.simon.pascal.api.rsocket;

import org.simon.pascal.domain.Tweet;
import org.simon.pascal.domain.TweetRequest;
import org.simon.pascal.service.TweetService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class TweetSocketController {

    private final TweetService tweetService;

    public TweetSocketController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @MessageMapping("tweets.by.author")
    public Flux<Tweet> getByAuthor(TweetRequest tweetRequest) {
        return tweetService.getByAuthor(tweetRequest.getAuthor());
    }
}
