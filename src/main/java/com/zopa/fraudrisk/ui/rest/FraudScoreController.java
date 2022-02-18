package com.zopa.fraudrisk.ui.rest;

import com.zopa.fraudrisk.core.FraudScore;
import com.zopa.fraudrisk.core.FraudScoreService;
import com.zopa.fraudrisk.core.Person;
import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/api/scores")
public class FraudScoreController {

    private FraudScoreService fraudScoreService;

    @Inject
    public FraudScoreController(FraudScoreService fraudScoreService) {
        this.fraudScoreService = fraudScoreService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@BeanParam FraudScoreQuery fraudScoreQuery) {
        Person person = fraudScoreQuery.asPerson();
        Optional<FraudScore> fraudScore = fraudScoreService.calculateScoreForPerson(person);
        if (fraudScore.isPresent()) {
            return Response.ok(FraudScoreResponse.fromFraudScore(fraudScore.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
