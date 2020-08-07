package org.simon.pascal.service;

import org.simon.pascal.domain.Tweet;
import org.simon.pascal.repository.TweetRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@Primary
public class TweetRedisService implements TweetService {

    private final TweetRepository tweetRepository;

    public TweetRedisService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public Flux<Tweet> getByAuthor(String author) {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(5))
                .flatMap(i -> tweetRepository.getByAuthor(author));
    }
}
