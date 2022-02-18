package com.zopa.fraudrisk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zopa.fraudrisk.ui.rest.FraudScoreResponse;
import jakarta.ws.rs.core.UriBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.OK;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static org.assertj.core.api.Assertions.assertThat;

public class ScoresEndpointIntegrationTest extends RestEndpointIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Should respond to fraud score requests")
    public void shouldReturnFraudScore() throws Exception {
        var client = HttpClient.newHttpClient();
        var email = "bobby.tables@zest.zopa.com";
        var firstName = "Bobby";
        var lastName = "Tables";
        var postCode = "Z0P4 943";
        var request = HttpRequest.newBuilder()
            .uri(UriBuilder.fromPath(TEST_URL + "/api/scores")
                .queryParam("email", email)
                .queryParam("firstName", firstName)
                .queryParam("lastName", lastName)
                .queryParam("postCode", postCode)
                .build())
            .build();
        var expectedScore = 42;
        fraudsterClientMock.setNextScore(expectedScore);

        var response = client.send(request, ofString());

        assertThat(response.statusCode()).isEqualTo(OK.getStatusCode());
        FraudScoreResponse fraudScoreResponse = objectMapper.readValue(response.body(), FraudScoreResponse.class);
        assertThat(fraudScoreResponse.getEmail()).isEqualTo(email);
        assertThat(fraudScoreResponse.getScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("Should return a cached result when called twice for the same person")
    public void shouldReturnCachedFraudScore() throws Exception {
        var client = HttpClient.newHttpClient();
        var email = "alex.green@zest.zopa.com";
        var firstName = "Alex";
        var lastName = "Green";
        var postCode = "Z0P4 467";
        var request = HttpRequest.newBuilder()
            .uri(UriBuilder.fromPath(TEST_URL + "/api/scores")
                .queryParam("email", email)
                .queryParam("firstName", firstName)
                .queryParam("lastName", lastName)
                .queryParam("postCode", postCode)
                .build())
            .build();
        var expectedScore = 98;
        fraudsterClientMock.setNextScore(expectedScore);

        var firstResponse = client.send(request, ofString());
        fraudsterClientMock.setNextScore(89);
        var secondResponse = client.send(request, ofString());

        assertThat(firstResponse.statusCode()).isEqualTo(OK.getStatusCode());
        FraudScoreResponse firstScore = objectMapper.readValue(firstResponse.body(), FraudScoreResponse.class);
        assertThat(firstScore.getEmail()).isEqualTo(email);
        assertThat(firstScore.getScore()).isEqualTo(expectedScore);
        FraudScoreResponse secondScore = objectMapper.readValue(secondResponse.body(), FraudScoreResponse.class);
        assertThat(secondScore.getEmail()).isEqualTo(email);
        assertThat(secondScore.getScore()).isEqualTo(firstScore.getScore());
    }

    @Test
    @DisplayName("Should return 400 when a valid email address is not provided")
    public void shouldReturn400() throws Exception {
        var client = HttpClient.newHttpClient();
        var email = "not a valid email address";
        var request = HttpRequest.newBuilder()
            .uri(UriBuilder.fromPath(TEST_URL + "/api/scores")
                .queryParam("email", email)
                .build())
            .build();

        var response = client.send(request, ofString());

        assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.getStatusCode());
    }

    @Test
    @DisplayName("Should return 404 when no score found")
    public void shouldReturn404() throws Exception {
        var client = HttpClient.newHttpClient();
        var email = "no.score@zest.zopa.com";
        var request = HttpRequest.newBuilder()
            .uri(UriBuilder.fromPath(TEST_URL + "/api/scores")
                .queryParam("email", email)
                .build())
            .build();
        fraudsterClientMock.setNextResponseEmpty(true);

        var response = client.send(request, ofString());

        assertThat(response.statusCode()).isEqualTo(NOT_FOUND.getStatusCode());
    }
}
