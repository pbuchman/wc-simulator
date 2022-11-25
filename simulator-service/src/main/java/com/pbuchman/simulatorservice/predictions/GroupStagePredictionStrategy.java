package com.pbuchman.simulatorservice.predictions;

import com.pbuchman.simulatorservice.probabilities.Probabilities;

class GroupStagePredictionStrategy implements PredictionStrategy {

    @Override
    public ExactResult execute(Probabilities probabilities) {
        return ExactResultPredictor.predict90(
                probabilities.team1Win90(), probabilities.draw()
        );
    }
}
