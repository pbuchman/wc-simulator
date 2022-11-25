package com.pbuchman.simulatorservice.service;

import com.pbuchman.simulatorservice.model.Team;
import com.pbuchman.simulatorservice.model.TeamsRepository;
import com.pbuchman.simulatorservice.predictions.ResultPredictor;
import com.pbuchman.simulatorservice.probabilities.ProbabilitiesProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

@Service
@Validated
@RequiredArgsConstructor
public class SingleGameResultService {

    private final TeamsRepository teamsRepository;

    private final ProbabilitiesProvider probabilitiesProvider;

    private final ResultPredictor resultPredictor;

    private final ExactResultMapper exactResultMapper;

    public Mono<SingleGameResult> predict(@NotBlank String team1Name, @NotBlank String team2Name, boolean playOff) {
        return validateTeams(team1Name, team2Name)
                .flatMap(any -> probabilitiesProvider.get(team1Name, team2Name))
                .map(probabilities -> resultPredictor.predictSingleGameResult(probabilities, playOff))
                .map(res -> exactResultMapper.toSingleGameResult(team1Name, team2Name, playOff, res));
    }

    private Mono<Team> validateTeams(String team1Name, String team2Name) {
        return teamsRepository.findOneByName(team1Name)
                .switchIfEmpty(Mono.error(() -> new MissingTeamException(team1Name)))
                .then(teamsRepository.findOneByName(team2Name))
                .switchIfEmpty(Mono.error(() -> new MissingTeamException(team1Name)));
    }
}
