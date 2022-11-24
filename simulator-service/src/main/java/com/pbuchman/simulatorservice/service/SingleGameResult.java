package com.pbuchman.simulatorservice.service;

import javax.validation.constraints.NotBlank;

public record SingleGameResult(
        @NotBlank String team1, @NotBlank String team2, boolean extraTimePlayed,
        @NotBlank String ninetyMinutesResult, @NotBlank String fullTimeResult) {
}
