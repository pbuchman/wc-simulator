package com.pbuchman.simulatorservice.probabilities;

import com.pbuchman.simulatorservice.probabilities.client.ProbabilitiesResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
@Validated
class ProbabilitiesMapper {
    Probabilities toProbabilities(@Valid @NotNull ProbabilitiesResponse response) {
        var team1 = response.team1();
        return new Probabilities(team1.win(), team1.win90(), team1.draw90());
    }
}
