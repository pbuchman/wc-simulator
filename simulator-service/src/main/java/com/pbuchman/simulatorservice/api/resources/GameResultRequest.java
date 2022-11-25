package com.pbuchman.simulatorservice.api.resources;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record GameResultRequest(@NotBlank String team1, @NotBlank String team2, @JsonProperty("play_off") Boolean playOff) {
}
