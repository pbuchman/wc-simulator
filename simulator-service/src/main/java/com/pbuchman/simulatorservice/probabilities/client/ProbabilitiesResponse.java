package com.pbuchman.simulatorservice.probabilities.client;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record ProbabilitiesResponse(@NotNull SingleProbabilityResponse team1,
                                    @NotNull SingleProbabilityResponse team2) implements Serializable {
}

