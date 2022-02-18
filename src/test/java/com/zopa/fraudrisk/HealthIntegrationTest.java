package com.zopa.fraudrisk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static jakarta.ws.rs.core.Response.Status.OK;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static org.assertj.core.api.Assertions.assertThat;

public class HealthIntegrationTest extends RestEndpointIntegrationTest {

    @Test
    @DisplayName("Should return OK (200) on liveness check")
    public void shouldReturnOkOnLiveness() throws Exception {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
            .uri(URI.create(TEST_URL + "/health/liveness"))
            .build();

        var response = client.send(request, ofString());

        assertThat(response.statusCode()).isEqualTo(OK.getStatusCode());
    }
}
