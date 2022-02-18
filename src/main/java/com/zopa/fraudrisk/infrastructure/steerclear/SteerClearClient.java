package com.zopa.fraudrisk.infrastructure.steerclear;

public interface SteerClearClient {

    SteerClearResponse getScore(SteerClearRequest request);
}
