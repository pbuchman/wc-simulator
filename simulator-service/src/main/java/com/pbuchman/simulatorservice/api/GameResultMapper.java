package com.pbuchman.simulatorservice.api;

import com.pbuchman.simulatorservice.api.resources.GameResultResponse;
import com.pbuchman.simulatorservice.service.SingleGameResult;
import org.springframework.stereotype.Component;

@Component
class GameResultMapper {
    GameResultResponse toResponse(SingleGameResult result) {
        return new GameResultResponse(
                result.team1(), result.team2(),
                result.extraTimePlayed(), result.ninetyMinutesResult(), result.fullTimeResult()
        );
    }
}
