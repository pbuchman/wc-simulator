package com.pbuchman.simulatorservice.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameResultControllerTest {

    @Autowired
    WebTestClient webClient;

    @Test
    void shouldReturnResultForSingleGameWithoutPlayOff() {
        executeRequest("Poland", "Mexico", false)
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").value(equalTo(6))
                .jsonPath("$.team1").value(equalTo("Poland"))
                .jsonPath("$.team2").value(equalTo("Mexico"))
                .jsonPath("$.play_off").value(equalTo(false))
                .jsonPath("$.ninety_minutes_result").value(matchesPattern("^\\d:\\d$"))
                .jsonPath("$.full_time_result").value(matchesPattern("^\\d:\\d$"))
                .jsonPath("$.simulated_at").isNotEmpty();
    }

    @Test
    void shouldReturnResultForSingleGameWithPlayOff() {
        executeRequest("Poland", "Argentina", true)
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").value(equalTo(6))
                .jsonPath("$.team1").value(equalTo("Poland"))
                .jsonPath("$.team2").value(equalTo("Argentina"))
                .jsonPath("$.play_off").value(equalTo(true))
                .jsonPath("$.ninety_minutes_result").value(matchesPattern("^\\d:\\d$"))
                .jsonPath("$.full_time_result").value(matchesPattern("^\\d:\\d$"))
                .jsonPath("$.simulated_at").isNotEmpty();
    }

    @Test
    void shouldReturnErrorOnBlankTeams() {
        executeRequest(" ", "", false)
                .expectStatus().isEqualTo(400) // @todo: handle that nicely on the app side
                .expectBody()
                .jsonPath("$.length()").value(equalTo(6)); // @todo: more assertions
    }

    @Test
    void shouldReturnErrorOnNotSupportedTeam() {
        executeRequest("Italy", "Poland", false)
                .expectStatus().isEqualTo(500) // @todo: handle that nicely on the app side
                .expectBody()
                .jsonPath("$.length()").value(equalTo(6)); // @todo: more assertions
    }

    @Test
    void shouldReturnErrorOnTheSameTeamBothAsT1AndT2() {
        executeRequest("Poland", "Poland", false)
                .expectStatus().isEqualTo(500) // @todo: handle that nicely on the app side
                .expectBody()
                .jsonPath("$.length()").value(equalTo(6)); // @todo: more assertions
    }
    private WebTestClient.ResponseSpec executeRequest(String team1, String team2, boolean playOff) {
        return webClient
                .post()
                .uri("/game-result/simulate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("""
                        {
                            "team1": "%s",
                            "team2": "%s",
                            "play_off": %b
                        }
                        """.formatted(team1, team2, playOff))
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

}