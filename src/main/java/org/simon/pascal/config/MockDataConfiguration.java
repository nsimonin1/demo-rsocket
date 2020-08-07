package org.simon.pascal.config;

import org.simon.pascal.domain.Tweet;
import org.simon.pascal.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Configuration
public class MockDataConfiguration {

    @Autowired
    private TweetRepository tweetRepository;

    private static final Tweet[] tweets = new Tweet[] {
            new Tweet("Linus Torvalds", "Talk is cheap. Show me the code."),
            new Tweet("Robert Martin", "Truth can only be found in one place: the code."),
            new Tweet("Martin Fowler", "Any fool can write code that a computer can understand." +
                    " Good programmers write code that humans can understand.")
    };

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        Flux.interval(Duration.ofSeconds(1))
                .map(i -> tweets[(int) (i % tweets.length)])
                .flatMap(tweet -> tweetRepository.save(Tweet.of(tweet)))
                .subscribe();
    }
}
