package com.pbuchman.simulatorservice.api.resources;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Validated
public record GameResultResponse(@NotBlank String team1, @NotBlank String team2, boolean playOff,
                                 @NotBlank String ninetyMinutesResult, @NotBlank String fullTimeResult,
                                 @NotNull Instant simulatedAt) {
}
