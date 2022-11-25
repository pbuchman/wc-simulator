package com.pbuchman.simulatorservice.predictions;

import com.pbuchman.simulatorservice.probabilities.Probabilities;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public class ResultPredictor {
    public ExactResult predictSingleGameResult(@Valid Probabilities probabilities, boolean playOff) {
        return getStrategy(playOff).execute(probabilities);
    }

    private PredictionStrategy getStrategy(boolean playOff) {
        return playOff ? new PlayOffPredictionStrategy() : new GroupStagePredictionStrategy();
    }
}
