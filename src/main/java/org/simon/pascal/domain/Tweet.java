package org.simon.pascal.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@NoArgsConstructor
public class Tweet {
    private String id;
    private String author;
    private String body;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    public Tweet(String author, String body) {
        this.id = UUID.randomUUID().toString();
        this.author = author;
        this.body = body;
        this.date = getRandomDate();
    }

    public static Tweet of(Tweet tweet) {
        return new Tweet(tweet.author, tweet.body);
    }

    private LocalDate getRandomDate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return LocalDate.of(random.nextInt(1990, 2020), random.nextInt(1, 13)
                , random.nextInt(1, 29));
    }
}
