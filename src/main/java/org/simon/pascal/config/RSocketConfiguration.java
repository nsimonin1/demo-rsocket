package org.simon.pascal.config;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketConnectorConfigurer;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
public class RSocketConfiguration {
    @LocalServerPort
    private int port;

    @Bean
    Mono<RSocketRequester> rSocketRequester(RSocketStrategies socketStrategies,
                                            RSocketProperties socketProperties) {
        return RSocketRequester.builder()
                .rsocketStrategies(socketStrategies)
                .connectWebSocket(getURI(socketProperties));
    }

    private URI getURI(RSocketProperties properties) {
        return URI.create(String.format("ws://localhost:%d%s", port, properties.getServer().getMappingPath()));
    }
}
