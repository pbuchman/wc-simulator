package com.pbuchman.simulatorservice.probabilities;

import javax.validation.constraints.NotNull;

public record Probabilities(@NotNull Double team1Win, Double team1Win90, Double draw) {

}
