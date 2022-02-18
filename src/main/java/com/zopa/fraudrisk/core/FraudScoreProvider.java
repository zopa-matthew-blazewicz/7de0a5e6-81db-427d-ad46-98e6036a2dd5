package com.zopa.fraudrisk.core;

import java.util.Optional;

public interface FraudScoreProvider {

    Optional<FraudScore> getLatestScore(Person person);
}
