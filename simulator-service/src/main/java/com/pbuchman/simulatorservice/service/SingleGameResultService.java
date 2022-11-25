package com.pbuchman.simulatorservice.service;

import com.pbuchman.simulatorservice.model.TeamRepository;
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

    private final TeamRepository teamRepository;

    private final ProbabilitiesProvider probabilitiesProvider;

    private final ResultPredictor resultPredictor;

    private final ExactResultMapper exactResultMapper;

    public Mono<SingleGameResult> predict(@NotBlank String team1Name, @NotBlank String team2Name, boolean playOff) {
        validateTeams(team1Name, team2Name);

        return probabilitiesProvider.get(team1Name, team2Name)
                .map(probabilities -> resultPredictor.predictSingleGameResult(probabilities, playOff))
                .map(res -> exactResultMapper.toSingleGameResult(team1Name, team2Name, playOff, res));
    }

    private void validateTeams(String team1Name, String team2Name) {
        teamRepository.findOneByName(team1Name)
                .orElseThrow(() -> new MissingTeamException(team1Name));
        teamRepository.findOneByName(team2Name)
                .orElseThrow(() -> new MissingTeamException(team2Name));
    }
}
