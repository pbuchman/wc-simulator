package com.pbuchman.simulatorservice.predictions;

import java.security.SecureRandom;
import java.util.Random;

class ExactResultPredictor {

    public static final Random RANDOM = new SecureRandom();

    static ExactResult predict90(double team1WinProbability90, double draw) {
        var random = RANDOM.nextDouble() * 100;
        if (random < team1WinProbability90) {
            return createWin90(true);
        }
        if (random >= team1WinProbability90 && random < (team1WinProbability90 + draw)) {
            return createDrawResult();
        }
        return createWin90(false);
    }

    static ExactResult predictFullTime(double team1WinProbabilityFullTime, double team1WinProbability90, double draw) {
        var ninetyMinutesResult = predict90(team1WinProbability90, draw);
        if (!ninetyMinutesResult.isDraw()) {
            return ninetyMinutesResult;
        }

        // this is not the perfect usage of probability data
        // (but it's good enough for this case)
        var random = RANDOM.nextDouble() * 100;
        if (random < team1WinProbabilityFullTime) {
            return createFullTimeWin(true);
        }
        return createFullTimeWin(false);
    }

    private static ExactResult createDrawResult() {
        int goals = RANDOM.nextInt(getMaxGoals());
        return ExactResult.draw(goals);
    }

    private static ExactResult createWin90(boolean team1Wins) {
        // +1 to ensure that when nextInt generates 0 then result is at least 1
        var winnerGoals = RANDOM.nextInt(getMaxGoals()) + 1;
        // range of [0, winnerGoals)
        var looserGoals = RANDOM.nextInt(winnerGoals);
        return team1Wins
                ? ExactResult.ninetyMinutesWin(winnerGoals, looserGoals)
                : ExactResult.ninetyMinutesWin(looserGoals, winnerGoals);
    }

    private static ExactResult createFullTimeWin(boolean team1Wins) {
        // +1 to ensure that when nextInt generates 0 then result is at least 1
        var winnerExtraTimeGoals = RANDOM.nextInt(getMaxExtraTimeGoals()) + 1;
        // range of [0, winnerExtraTimeGoals)
        var looserExtraTimeGoals = RANDOM.nextInt(winnerExtraTimeGoals);

        var draw = createDrawResult();

        return team1Wins
                ? ExactResult.winAfterExtraTime(draw, draw.team1Goals90() + winnerExtraTimeGoals, draw.team2Goals90() + looserExtraTimeGoals)
                : ExactResult.winAfterExtraTime(draw, draw.team1Goals90() + looserExtraTimeGoals, draw.team2Goals90() + winnerExtraTimeGoals);
    }

    /**
     * Let's introduce some "real life randomness" for results
     */
    private static int getMaxGoals() {
        int maxGoalsNumberProbability = RANDOM.nextInt(20);

        if (maxGoalsNumberProbability == 0) {
            return 8;
        }
        if (maxGoalsNumberProbability < 8) {
            return 4;
        }
        return 3;
    }

    private static int getMaxExtraTimeGoals() {
        int maxGoalsNumberProbability = RANDOM.nextInt(10);

        if (maxGoalsNumberProbability == 0) {
            return 4;
        }
        if (maxGoalsNumberProbability < 4) {
            return 3;
        }
        return 2;
    }
}
