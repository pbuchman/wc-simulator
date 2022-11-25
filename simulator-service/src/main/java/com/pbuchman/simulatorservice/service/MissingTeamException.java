package com.pbuchman.simulatorservice.service;

public class MissingTeamException extends RuntimeException {
    public MissingTeamException(String teamName) {
        super("Team '%s' is not supported.".formatted(teamName));
    }
}
