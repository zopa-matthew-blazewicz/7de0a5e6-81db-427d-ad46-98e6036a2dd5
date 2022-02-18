package com.zopa.fraudrisk.core;

import java.util.Optional;

public interface FraudScoreRepository {

    Optional<FraudScore> getScore(Person person);
    void saveScore(FraudScore score);
}
