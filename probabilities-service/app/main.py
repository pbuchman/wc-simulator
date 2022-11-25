from flask import Flask

from error_handler import error
from probabilities_controller import probabilities

app = Flask(__name__)

if __name__ == '__main__':
    app.run()

app.register_blueprint(error)
app.register_blueprint(probabilities)
