package com.pbuchman.simulatorservice.service;

import com.pbuchman.simulatorservice.predictions.ExactResult;
import org.springframework.stereotype.Component;

@Component
class ExactResultMapper {
    SingleGameResult toSingleGameResult(String team1, String team2, boolean playOff, ExactResult exactResult) {
        return new SingleGameResult(
                team1, team2, playOff,
                playOff ? ninetyMinutesResult(exactResult) : fullTimeResult(exactResult),
                fullTimeResult(exactResult)
        );
    }

    private String ninetyMinutesResult(ExactResult exactResult) {
        return "%d:%d".formatted(exactResult.team1Goals90(), exactResult.team2Goals90());
    }

    private String fullTimeResult(ExactResult exactResult) {
        return "%d:%d".formatted(exactResult.team1GoalsFullTime(), exactResult.team2GoalsFullTime());
    }
}
