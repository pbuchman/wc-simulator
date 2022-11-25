# World Cup 2022 Simulator

You don't have to watch the World Cup Games, just simulate all of them!

**Note**: all the probability data downloaded and parsed from [zeileis.org](https://www.zeileis.org/news/fifa2022/)

### Running stuff locally
**Required setup**: `docker` and `docker-compose`, `java 17`
* `❯ cd ~/repo/wc-simulator && docker-compose up -d --build probabilities postgres redis`
* `❯ cd ~/repo/wc-simulator/simulator-service && ./gradlew clean build`

If you want to start all services in the Docker containers, then run:
* `❯ cd ~/repo/wc-simulator && docker-compose up --build`

### Tech details
* `probabilities-service` - small Python (Flask) application, exposing simple API with the probabilities of the certain result in a partucular World Cup game
* `simulator-service` - fully reactive Spring Boot application, exposing API for predicting game results 