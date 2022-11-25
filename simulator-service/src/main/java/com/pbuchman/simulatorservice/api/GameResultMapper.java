package com.pbuchman.simulatorservice.api;

import com.pbuchman.simulatorservice.api.resources.GameResultResponse;
import com.pbuchman.simulatorservice.service.SingleGameResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Component
@Validated
class GameResultMapper {
    GameResultResponse toResponse(@Valid SingleGameResult result, @NotNull Instant simulatedAt) {
        return new GameResultResponse(
                result.team1(), result.team2(),
                result.playOff(), result.ninetyMinutesResult(), result.fullTimeResult(),
                simulatedAt
        );
    }
}
