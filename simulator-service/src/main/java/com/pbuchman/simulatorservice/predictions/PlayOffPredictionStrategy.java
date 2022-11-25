package com.pbuchman.simulatorservice.predictions;

import com.pbuchman.simulatorservice.probabilities.Probabilities;

class PlayOffPredictionStrategy implements PredictionStrategy {

    @Override
    public ExactResult execute(Probabilities probabilities) {
        return ExactResultPredictor.predictFullTime(
                probabilities.team1Win(),
                probabilities.team1Win90(), probabilities.draw()
        );
    }
}
