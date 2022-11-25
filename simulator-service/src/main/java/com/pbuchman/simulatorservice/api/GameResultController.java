package com.pbuchman.simulatorservice.api;

import com.pbuchman.simulatorservice.api.resources.GameResultRequest;
import com.pbuchman.simulatorservice.api.resources.GameResultResponse;
import com.pbuchman.simulatorservice.service.SingleGameResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Clock;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/game-result")
@RequiredArgsConstructor
public class GameResultController {

    private final SingleGameResultService gameResultService;
    private final GameResultMapper mapper;

    @PostMapping(value = "/simulate")
    @ResponseStatus(HttpStatus.OK)
    public Mono<GameResultResponse> simulate(@Valid @RequestBody GameResultRequest request) {

        return gameResultService.predict(request.team1(), request.team2(), Optional.ofNullable(request.playOff()).orElse(false))
                .map(result -> mapper.toResponse(result, Instant.now(Clock.systemUTC())));
    }
}
