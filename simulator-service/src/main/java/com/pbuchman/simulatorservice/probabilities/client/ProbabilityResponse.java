package com.pbuchman.simulatorservice.probabilities.client;

import javax.validation.constraints.NotNull;

public record ProbabilityResponse(
        @NotNull Double win, @NotNull Double win90, @NotNull Double draw90, @NotNull Double loss90) {
}
