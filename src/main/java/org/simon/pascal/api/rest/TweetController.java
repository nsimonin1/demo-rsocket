package org.simon.pascal.api.rest;

import org.simon.pascal.domain.Tweet;
import org.simon.pascal.domain.TweetRequest;
import org.simon.pascal.service.TweetService;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class TweetController {

    private final TweetService tweetService;
    private final Mono<RSocketRequester> requester;

    public TweetController(TweetService tweetService, Mono<RSocketRequester> requester) {
        this.tweetService = tweetService;
        this.requester = requester;
    }

    @GetMapping(value = "/tweets/{author}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> getByAuthor(@PathVariable String author) {
        return tweetService.getByAuthor(author);
    }

    @GetMapping(value = "/socket/{author}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> getByAuthorViaSocket(@PathVariable String author) {
        return requester.flatMapMany(r->r.route("tweets.by.author")
                .data(new TweetRequest(author)).retrieveFlux(Tweet.class));
    }
}
