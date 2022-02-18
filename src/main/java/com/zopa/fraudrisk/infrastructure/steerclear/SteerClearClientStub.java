package com.zopa.fraudrisk.infrastructure.steerclear;

import com.zopa.fraudrisk.infrastructure.steerclear.SteerClearResponse.RiskGroup;

import java.util.Random;

public class SteerClearClientStub implements SteerClearClient {

    private final Random random = new Random(System.currentTimeMillis());

    public SteerClearResponse getScore(SteerClearRequest request) {
        return new SteerClearResponse(
            request.getEmail(),
            request.getFirstName(),
            request.getLastName(),
            request.getPostCode(),
            randomRiskGroup());
    }

    private RiskGroup randomRiskGroup() {
        int randomRiskGroupIndex = random.nextInt(RiskGroup.values().length);
        return RiskGroup.values()[randomRiskGroupIndex];
    }
}
