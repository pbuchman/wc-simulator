package com.pbuchman.simulatorservice.probabilities.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.Map;

import static reactor.core.publisher.Mono.defer;

@Slf4j
@Component
public class ProbabilitiesApiClient {

    private final WebClient webClient;

    private final ProbabilitiesResponseRepository cache;

    @Autowired
    public ProbabilitiesApiClient(@Value("${probabilities.endpoint}") String probabilitiesEndpoint,
                                  WebClient.Builder webClientBuilder,
                                  ProbabilitiesResponseRepository cache) {

        this.cache = cache;
        this.webClient = webClientBuilder
                .baseUrl(probabilitiesEndpoint)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<ProbabilitiesResponse> getProbabilities(@NotBlank String team1, @NotBlank String team2) {
        String responseCacheKey = "probabilities-%s-%s".formatted(team1, team2);
        return cache
                .findById(responseCacheKey)
                .switchIfEmpty(defer(() -> webClient.get()
                                .uri(uriBuilder -> {
                                            var uri = uriBuilder
                                                    .path("probabilities/get")
                                                    .queryParam("team1", team1)
                                                    .queryParam("team2", team2)
                                                    .build();
                                            log.debug("Executing GET request to URI: {}", uri);
                                            return uri;
                                        }
                                )
                                .retrieve()
                                .bodyToMono(new ParameterizedTypeReference<Map<String, Map<String, Double>>>() {
                                })
                                .map(map -> new ProbabilitiesResponse(
                                                new SingleProbabilityResponse(
                                                        map.get(team1).get("win"), map.get(team1).get("win_90"),
                                                        map.get(team1).get("draw_90"), map.get(team1).get("loss_90")
                                                ),
                                                new SingleProbabilityResponse(
                                                        map.get(team2).get("win"), map.get(team2).get("win_90"),
                                                        map.get(team2).get("draw_90"), map.get(team2).get("loss_90")
                                                )
                                        )
                                )
                        )
                                .flatMap(response -> cache.save(responseCacheKey, response).map(any -> response))
                );
    }
}
