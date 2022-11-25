package com.pbuchman.simulatorservice.probabilities;

import com.pbuchman.simulatorservice.probabilities.client.ProbabilitiesApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

@Validated
@Service
@RequiredArgsConstructor
public class ProbabilitiesProvider {

    private final ProbabilitiesApiClient apiClient;

    private final ProbabilitiesMapper mapper;

    public Mono<Probabilities> get(@NotBlank String team1, @NotBlank String team2) {
        return apiClient.getProbabilities(team1, team2)
                .map(mapper::toProbabilities);
    }
}