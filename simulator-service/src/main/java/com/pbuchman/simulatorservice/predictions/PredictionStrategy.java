package com.pbuchman.simulatorservice.predictions;

import com.pbuchman.simulatorservice.probabilities.Probabilities;

interface PredictionStrategy {
    ExactResult execute(Probabilities probabilities);
}
