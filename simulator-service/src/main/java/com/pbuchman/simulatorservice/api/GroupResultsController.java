package com.pbuchman.simulatorservice.api;

import com.pbuchman.simulatorservice.api.resources.GameResultResponse;
import com.pbuchman.simulatorservice.api.resources.GroupResultsRequest;
import com.pbuchman.simulatorservice.service.GroupResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.time.Clock;
import java.time.Instant;

@RestController
@RequestMapping("/group-results")
@RequiredArgsConstructor
public class GroupResultsController {

    private final GroupResultsService groupResultsService;
    private final GameResultMapper mapper;

    @PostMapping(value = "/simulate")
    @ResponseStatus(HttpStatus.OK)
    public Flux<GameResultResponse> simulate(@Valid @RequestBody GroupResultsRequest request) {

        return groupResultsService.predict(request.group())
                .map(result -> mapper.toResponse(result, Instant.now(Clock.systemUTC())));
    }
}
