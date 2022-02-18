package com.zopa.fraudrisk.core;

import java.util.Optional;

public class FraudScoreService {

    private final FraudScoreRepository fraudScoreRepository;
    private final FraudScoreProvider fraudScoreProvider;

    public FraudScoreService(FraudScoreRepository fraudScoreRepository, FraudScoreProvider fraudScoreProvider) {
        this.fraudScoreRepository = fraudScoreRepository;
        this.fraudScoreProvider = fraudScoreProvider;
    }

    public Optional<FraudScore> calculateScoreForPerson(Person person) {
        return fraudScoreRepository.getScore(person)
            .or(() -> updatedFraudScore(person));
    }

    private Optional<FraudScore> updatedFraudScore(Person person) {
        Optional<FraudScore> latestScore = fraudScoreProvider.getLatestScore(person);
        latestScore.ifPresent(fraudScoreRepository::saveScore);
        return latestScore;
    }
}
