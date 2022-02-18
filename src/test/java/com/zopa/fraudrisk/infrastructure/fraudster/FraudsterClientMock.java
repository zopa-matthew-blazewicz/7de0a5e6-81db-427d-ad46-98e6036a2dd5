package com.zopa.fraudrisk.infrastructure.fraudster;

public class FraudsterClientMock implements FraudsterClient {

    private boolean nextResponseEmpty = false;
    private int nextScore = 42;

    public void setNextResponseEmpty(boolean nextResponseEmpty) {
        this.nextResponseEmpty = nextResponseEmpty;
    }

    public void setNextScore(int nextScore) {
        this.nextScore = nextScore;
    }

    public FraudsterResponse getScore(FraudsterRequest request) {
        if (nextResponseEmpty) {
            return null;
        } else {
            return new FraudsterResponse(
                request.getEmailAddress(),
                request.getFirstName(),
                request.getSurname(),
                request.getPostCode(),
                nextScore
            );
        }
    }
}
