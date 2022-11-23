from flask import make_response, json, Blueprint
from werkzeug.exceptions import NotFound

from exceptions import InvalidRequestParams

error = Blueprint("error", __name__)


@error.app_errorhandler(InvalidRequestParams)
def handle_exception(ex):
    return create_error_response(ex.status_code, ex.message)


@error.app_errorhandler(Exception)
def handle_exception(ex):
    return create_error_response(500, str(ex))


@error.app_errorhandler(NotFound)
def handle_exception(ex):
    return create_error_response(404, ex.description)


def create_error_response(status_code, message):
    response = make_response()
    response.data = json.dumps({
        "status_code": status_code,
        "message": message
    })
    response.content_type = "application/json"
    response.status_code = status_code
    return response
