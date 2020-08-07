package org.simon.pascal.service;

import org.simon.pascal.domain.Tweet;
import reactor.core.publisher.Flux;

public interface TweetService {

    Flux<Tweet> getByAuthor(String author);
}
