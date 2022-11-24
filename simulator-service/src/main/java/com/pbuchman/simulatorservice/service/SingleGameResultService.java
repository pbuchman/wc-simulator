package com.pbuchman.simulatorservice.service;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
public class SingleGameResultService {
    public SingleGameResult predict(@NotBlank String team1, @NotBlank String team2, boolean playOff) {
        return new SingleGameResult(team1, team2, true, "2:2", "4:2");
    }
}
