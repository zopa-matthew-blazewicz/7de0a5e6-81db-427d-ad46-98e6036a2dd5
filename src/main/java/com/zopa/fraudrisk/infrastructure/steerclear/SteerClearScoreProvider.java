package com.zopa.fraudrisk.infrastructure.steerclear;

import com.zopa.fraudrisk.core.FraudScore;
import com.zopa.fraudrisk.core.FraudScoreProvider;
import com.zopa.fraudrisk.core.Person;
import com.zopa.fraudrisk.infrastructure.steerclear.SteerClearResponse.RiskGroup;

import java.util.Optional;

public class SteerClearScoreProvider implements FraudScoreProvider {

    private final SteerClearClient steerClearClient;

    public SteerClearScoreProvider(SteerClearClient steerClearClient) {
        this.steerClearClient = steerClearClient;
    }

    @Override
    public Optional<FraudScore> getLatestScore(Person person) {
        SteerClearRequest steerClearRequest = new SteerClearRequest(person.getEmail(), person.getFirstName(),
            person.getLastName(), person.getPostCode());

        SteerClearResponse steerClearResponse = steerClearClient.getScore(steerClearRequest);
        if (steerClearResponse == null) {
            return Optional.empty();
        }

        return Optional.of(mapToFraudScore(steerClearResponse));
    }

    private FraudScore mapToFraudScore(SteerClearResponse steerClearResponse) {
        return FraudScore.builder()
            .person(Person.builder()
                .email(steerClearResponse.getEmail())
                .firstName(steerClearResponse.getFirstName())
                .lastName(steerClearResponse.getLastName())
                .postCode(steerClearResponse.getPostCode())
                .build())
            .score(mapToScore(steerClearResponse.getRiskGroup()))
            .build();
    }

    private int mapToScore(RiskGroup riskGroup) {
        return riskGroup.ordinal();
    }
}
