package com.pbuchman.simulatorservice.service;

import com.pbuchman.simulatorservice.model.Team;
import com.pbuchman.simulatorservice.model.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@Validated
@RequiredArgsConstructor
public class GroupResultsService {

    private final SingleGameResultService singleGameResultService;

    private final TeamsRepository teamsRepository;

    @Transactional
    public Flux<SingleGameResult> predict(
            @NotBlank @Pattern(regexp = "^[A-H]$", message = "Group should be a letter A-H") String group) {

        return teamsRepository.findAllByGroupOrderBySeed(group)
                .map(Team::getName)
                .collect(collectingAndThen(
                        toList(),
                        teams -> List.of(
                                Tuples.of(teams.get(0), teams.get(3)),
                                Tuples.of(teams.get(1), teams.get(2)),

                                Tuples.of(teams.get(2), teams.get(3)),
                                Tuples.of(teams.get(0), teams.get(1)),

                                Tuples.of(teams.get(3), teams.get(1)),
                                Tuples.of(teams.get(2), teams.get(0))
                        )

                ))
                .flatMapMany(Flux::fromIterable)
                .flatMap(game -> singleGameResultService.predict(game.getT1(), game.getT2(), false));
    }
}
