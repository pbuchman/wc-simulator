package com.pbuchman.simulatorservice.probabilities.client;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record SingleProbabilityResponse(
        @NotNull Double win, @NotNull Double win90, @NotNull Double draw90,
        @NotNull Double loss90) implements Serializable {
}
