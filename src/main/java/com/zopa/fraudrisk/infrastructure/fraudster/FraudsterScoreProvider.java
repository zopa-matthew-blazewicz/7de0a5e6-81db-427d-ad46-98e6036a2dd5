package com.zopa.fraudrisk.infrastructure.fraudster;

import com.zopa.fraudrisk.core.FraudScore;
import com.zopa.fraudrisk.core.FraudScoreProvider;
import com.zopa.fraudrisk.core.Person;

import java.util.Optional;

public class FraudsterScoreProvider implements FraudScoreProvider {

    private final FraudsterClient fraudsterClient;

    public FraudsterScoreProvider(FraudsterClient fraudsterClient) {
        this.fraudsterClient = fraudsterClient;
    }

    @Override
    public Optional<FraudScore> getLatestScore(Person person) {
        FraudsterRequest fraudsterRequest = FraudsterRequest.fromPerson(person);

        FraudsterResponse fraudsterResponse = fraudsterClient.getScore(fraudsterRequest);
        if (fraudsterResponse == null) {
            return Optional.empty();
        }

        return Optional.of(FraudScore.builder()
            .person(Person.builder()
                .email(fraudsterResponse.getEmailAddress())
                .firstName(fraudsterResponse.getFirstName())
                .lastName(fraudsterResponse.getSurname())
                .postCode(fraudsterResponse.getPostCode())
                .build())
            .score(mapToScore(fraudsterResponse.getFraudRating()))
            .build());
    }

    private int mapToScore(int fraudRating) {
        return fraudRating;
    }
}
