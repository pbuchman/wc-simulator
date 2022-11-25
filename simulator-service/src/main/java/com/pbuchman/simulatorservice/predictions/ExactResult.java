package com.pbuchman.simulatorservice.predictions;

public record ExactResult(int team1Goals90, int team2Goals90, int team1GoalsFullTime, int team2GoalsFullTime) {
    public static ExactResult draw(int goals) {
        // @todo: support for penalties :)
        return new ExactResult(goals, goals, goals, goals);
    }

    public static ExactResult ninetyMinutesWin(int team1Goals90, int team2Goals90) {
        return new ExactResult(team1Goals90, team2Goals90, team1Goals90, team2Goals90);
    }

    public static ExactResult winAfterExtraTime(ExactResult draw, int team1GoalsFullTime, int team2GoalsFullTime) {
        return new ExactResult(draw.team1Goals90(), draw.team2Goals90(), team1GoalsFullTime, team2GoalsFullTime);
    }

    public boolean isDraw() {
        return team1Goals90 == team2Goals90;
    }
}
