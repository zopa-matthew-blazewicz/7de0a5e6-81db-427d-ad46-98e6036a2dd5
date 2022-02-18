package com.zopa.fraudrisk.infrastructure.cache;

import com.zopa.fraudrisk.core.FraudScore;
import com.zopa.fraudrisk.core.FraudScoreRepository;
import com.zopa.fraudrisk.core.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FraudScoreCache implements FraudScoreRepository {

    private static FraudScoreCache instance;

    private final Map<String, FraudScore> cachedScores = new HashMap<>();

    private FraudScoreCache() {}

    @Override
    public Optional<FraudScore> getScore(Person person) {
        return Optional.ofNullable(cachedScores.get(getCacheKey(person)));
    }

    @Override
    public void saveScore(FraudScore score) {
        cachedScores.put(getCacheKey(score.getPerson()), score);
    }

    private String getCacheKey(Person person) {
        return person.getEmail();
    }

    public static FraudScoreCache getInstance() {
        if (instance == null) {
            instance = new FraudScoreCache();
        }
        return instance;
    }
}
