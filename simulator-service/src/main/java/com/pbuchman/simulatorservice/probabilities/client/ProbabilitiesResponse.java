package com.pbuchman.simulatorservice.probabilities.client;

import javax.validation.constraints.NotNull;

public record ProbabilitiesResponse(@NotNull ProbabilityResponse team1, @NotNull ProbabilityResponse team2) {
}

