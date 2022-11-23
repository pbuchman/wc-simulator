from pathlib import Path

from flask import json

current_dir = Path(__file__).parent.resolve()

teams: list = json.load(open("{}/teams.json".format(current_dir))).get("teams")
probabilities: list = json.load(open("{}/probabilities.json".format(current_dir))).get("probabilities")

WIN: int = 0
WIN_90: int = 1
DRAW_90: int = 2
LOSS_90: int = 3


class ProbabilitiesProvider:

    @staticmethod
    def get_probability(team1, team2):
        t1_index = teams.index(team1)
        t2_index = teams.index(team2)

        t1_vs_t2 = probabilities[t1_index][t2_index]
        t2_vs_t1 = probabilities[t2_index][t1_index]

        return {
            team1: {
                "win": t1_vs_t2[WIN],
                "win_90": t1_vs_t2[WIN_90],
                "draw_90": t1_vs_t2[DRAW_90],
                "loss_90": t1_vs_t2[LOSS_90]
            },
            team2: {
                "win": t2_vs_t1[WIN],
                "win_90": t2_vs_t1[WIN_90],
                "draw_90": t2_vs_t1[DRAW_90],
                "loss_90": t2_vs_t1[LOSS_90]
            }
        }
