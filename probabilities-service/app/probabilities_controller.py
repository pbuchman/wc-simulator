from pathlib import Path

from flask import request, Blueprint, json

from exceptions import InvalidRequestParams
from probabilities_provider import ProbabilitiesProvider

probabilities: Blueprint = Blueprint('probabilities', __name__, url_prefix='/probabilities/')
current_dir: Path = Path(__file__).parent.resolve()

teams: list = json.load(open("{}/teams.json".format(current_dir))).get("teams")


@probabilities.route('get', methods=['GET'])
def get_probabilities():
    query_params = request.args.keys()
    if "team1" not in query_params or "team2" not in query_params:
        raise InvalidRequestParams("Request must contain both 'team1' and 'team2' arguments", 422)

    if request.args["team1"] == request.args["team2"]:
        raise InvalidRequestParams("'team1' must be different than 'team2'", 422)

    if request.args["team1"] not in teams:
        raise InvalidRequestParams("'team1' is invalid. Valid values: " + str(teams), 422)

    if request.args["team2"] not in teams:
        raise InvalidRequestParams("'team2' is invalid. Valid values: " + str(teams), 422)

    return ProbabilitiesProvider.get_probability(request.args["team1"], request.args["team2"])
