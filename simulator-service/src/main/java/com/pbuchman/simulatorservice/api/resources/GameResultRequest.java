package com.pbuchman.simulatorservice.api.resources;


import javax.validation.constraints.NotBlank;

public record GameResultRequest(@NotBlank String team1, @NotBlank String team2, boolean playOff) {
}
