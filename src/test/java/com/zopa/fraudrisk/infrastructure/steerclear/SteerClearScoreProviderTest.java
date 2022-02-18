package com.zopa.fraudrisk.infrastructure.steerclear;

import com.zopa.fraudrisk.common.TestPersonFactory;
import com.zopa.fraudrisk.core.FraudScore;
import com.zopa.fraudrisk.core.Person;
import com.zopa.fraudrisk.infrastructure.steerclear.SteerClearResponse.RiskGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SteerClearScoreProviderTest {

    @Test
    @DisplayName("Should fetch risk group for a person")
    void shouldFetchLatestScore() {
        SteerClearClient steerClearClientMock = mock(SteerClearClient.class);
        SteerClearScoreProvider steerClearScoreProvider = new SteerClearScoreProvider(steerClearClientMock);
        Person person = TestPersonFactory.getTestPerson();
        SteerClearResponse steerClearResponse = new SteerClearResponse(person.getEmail(), person.getFirstName(),
            person.getLastName(), person.getPostCode(), RiskGroup.A);
        when(steerClearClientMock.getScore(any(SteerClearRequest.class))).thenReturn(steerClearResponse);

        Optional<FraudScore> latestScore = steerClearScoreProvider.getLatestScore(person);

        assertThat(latestScore.isPresent()).isTrue();
        FraudScore fraudScore = latestScore.get();
        assertThat(fraudScore.getPerson()).isEqualTo(person);
        assertThat(fraudScore.getScore()).isEqualTo(0);
    }
}
