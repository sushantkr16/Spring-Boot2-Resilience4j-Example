package com.sk.learn.client;

import com.sk.learn.exception.IgnoreException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.client.WebClient.builder;

@Slf4j
@Component
public class TeamFacade {

    private WebClient webClient;

    @Value("${idea.base.url:http://localhost:9081/sk/}")
    private String baseURL;

    public String apiSuccessResponse() {
        Mono<String> monoResponse = prepareWebClient().get()
                .uri("/idea/success")
                .retrieve()
                .bodyToMono(String.class);
        return monoResponse.block();
    }

    public String apiFailureResponse() {
        Mono<String> monoResponse = prepareWebClient().get()
                .uri("/idea/failure")
                .retrieve()
                .bodyToMono(String.class);
        return monoResponse.block();
    }

    public String apiFailureWithFallback() {
        Mono<String> monoResponse = prepareWebClient().get()
                .uri("/idea/fallback")
                .retrieve()
                .bodyToMono(String.class);
        return monoResponse.block();
    }

    public String apiSuccessWithException() {
        try {
            Mono<String> monoResponse = prepareWebClient().get()
                    .uri("/idea/successException")
                    .retrieve()
                    .bodyToMono(String.class);
            return monoResponse.block();
        } catch (Exception ex) {
            log.error(" Error while getting response from idea service");
            return "Api Success With Exception";
        }
    }

    public String apiIgnoreException() {
        Mono<String> monoResponse = prepareWebClient().get()
                .uri("/idea/ignore")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.error(new IgnoreException(HttpStatus.BAD_REQUEST, "Please ignore this exception as its configured in the CircuitBreaker : Team")));
        return monoResponse.block();
    }

    private WebClient prepareWebClient() {
        return builder().baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector())
                .build();
    }


}
