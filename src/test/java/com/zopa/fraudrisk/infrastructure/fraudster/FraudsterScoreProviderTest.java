package com.zopa.fraudrisk.infrastructure.fraudster;

import com.zopa.fraudrisk.common.TestPersonFactory;
import com.zopa.fraudrisk.core.FraudScore;
import com.zopa.fraudrisk.core.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FraudsterScoreProviderTest {

    @Test
    @DisplayName("Should fetch latest score for a person")
    void shouldFetchLatestScore() {
        FraudsterClient fraudsterClientMock = mock(FraudsterClient.class);
        FraudsterScoreProvider fraudsterScoreProvider = new FraudsterScoreProvider(fraudsterClientMock);
        Person person = TestPersonFactory.getTestPerson();
        int expectedScore = 97;
        FraudsterResponse fraudsterResponse = new FraudsterResponse(person.getEmail(), person.getFirstName(),
            person.getLastName(), person.getPostCode(), expectedScore);
        when(fraudsterClientMock.getScore(any(FraudsterRequest.class))).thenReturn(fraudsterResponse);

        Optional<FraudScore> latestScore = fraudsterScoreProvider.getLatestScore(person);

        assertThat(latestScore.isPresent()).isTrue();
        FraudScore fraudScore = latestScore.get();
        assertThat(fraudScore.getPerson()).isEqualTo(person);
        assertThat(fraudScore.getScore()).isEqualTo(expectedScore);
    }
}
