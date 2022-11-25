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
class GroupResultsControllerTest {

    @Autowired
    WebTestClient webClient;

    @Test
    void shouldReturnResultsForTheGroupSimulation() {
        executeRequest("A")
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").value(equalTo(6))
                .jsonPath("$[0].length()").value(equalTo(6))
                .jsonPath("$[1].length()").value(equalTo(6))
                .jsonPath("$[2].length()").value(equalTo(6))
                .jsonPath("$[3].length()").value(equalTo(6))
                .jsonPath("$[4].length()").value(equalTo(6))
                .jsonPath("$[5].length()").value(equalTo(6))
                .jsonPath("$[0].team1").isNotEmpty()
                .jsonPath("$[0].team2").isNotEmpty()
                .jsonPath("$[0].play_off").value(equalTo(false))
                .jsonPath("$[0].ninety_minutes_result").value(matchesPattern("^\\d:\\d$"))
                .jsonPath("$[0].full_time_result").value(matchesPattern("^\\d:\\d$"))
                .jsonPath("$[0].simulated_at").isNotEmpty();
    }

    @Test
    void shouldReturnErrorOnBlank() {
        executeRequest("   ")
                .expectStatus().isEqualTo(400) // @todo: handle that nicely on the app side
                .expectBody()
                .jsonPath("$.length()").value(equalTo(6)); // @todo: more assertions
    }

    @Test
    void shouldReturnErrorOnInvalidGroup() {
        executeRequest("X")
                .expectStatus().isEqualTo(400) // @todo: handle that nicely on the app side
                .expectBody()
                .jsonPath("$.length()").value(equalTo(6)); // @todo: more assertions
    }

    private WebTestClient.ResponseSpec executeRequest(String group) {
        return webClient
                .post()
                .uri("/group-results/simulate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("""
                        {
                            "group": "%s"
                        }
                        """.formatted(group))
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

}