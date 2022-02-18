package com.zopa.fraudrisk.infrastructure.cache;

import com.zopa.fraudrisk.common.TestPersonFactory;
import com.zopa.fraudrisk.core.FraudScore;
import com.zopa.fraudrisk.core.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class FraudScoreCacheTest {

    @Test
    public void shouldReturnSavedScore() {
        FraudScoreCache fraudScoreCache = FraudScoreCache.getInstance();
        Person person = TestPersonFactory.getTestPerson();
        FraudScore fraudScore = FraudScore.builder()
            .person(person)
            .score(73).build();

        fraudScoreCache.saveScore(fraudScore);
        Optional<FraudScore> optionalScore = fraudScoreCache.getScore(person);

        Assertions.assertThat(optionalScore.isPresent()).isTrue();
        Assertions.assertThat(optionalScore.get().getScore()).isEqualTo(73);
    }
}
