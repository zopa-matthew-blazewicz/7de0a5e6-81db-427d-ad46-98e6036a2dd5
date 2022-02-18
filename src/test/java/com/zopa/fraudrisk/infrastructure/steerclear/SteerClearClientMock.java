package com.zopa.fraudrisk.infrastructure.steerclear;

import com.zopa.fraudrisk.infrastructure.steerclear.SteerClearResponse.RiskGroup;

public class SteerClearClientMock implements SteerClearClient {

    private boolean nextResponseEmpty = false;
    private RiskGroup nextRiskGroup = RiskGroup.C;

    public void setNextResponseEmpty(boolean nextResponseEmpty) {
        this.nextResponseEmpty = nextResponseEmpty;
    }

    public void setNextRiskGroup(RiskGroup nextRiskGroup) {
        this.nextRiskGroup = nextRiskGroup;
    }

    @Override
    public SteerClearResponse getScore(SteerClearRequest request) {
        if (nextResponseEmpty) {
            return null;
        } else {
            return new SteerClearResponse(
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                request.getPostCode(),
                nextRiskGroup);
        }
    }
}
