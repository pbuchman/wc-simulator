package com.pbuchman.simulatorservice.api.resources;

import javax.validation.constraints.NotBlank;

public record GameResultResponse(@NotBlank String team1, @NotBlank String team2, boolean extraTimePlayed,
                                 @NotBlank String ninetyMinutesResult, @NotBlank String fullTimeResult) {
}
