package org.simon.pascal.service;

import org.simon.pascal.domain.Tweet;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class TweetInMemoryService implements TweetService{

    public static final Map<String, Tweet> tweets = new HashMap<String, Tweet>() {
        {
            put("linustorvalds", new Tweet("Linus Torvalds", "Talk is cheap. Show me the code."));
            put("robertmartin", new Tweet("Robert Martin", "Truth can only be found in one place: the code."));
            put("martinfowler", new Tweet("Martin Fowler", "Any fool can write code that a computer can unders"));
        }
    };

    public Flux<Tweet> getByAuthor(String author) {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1))
                .map(i->Tweet.of(tweets.get(author)));
    }
}
