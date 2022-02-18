package com.zopa.fraudrisk.infrastructure.fraudster;

public interface FraudsterClient {

    FraudsterResponse getScore(FraudsterRequest request);
}
