package com.zopa.fraudrisk.core;

import com.zopa.fraudrisk.common.TestPersonFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FraudScoreServiceTest {

    @Test
    @DisplayName("Should provide a stored score when it matches the request")
    public void shouldProvideExistingScore() {
        FraudScoreRepository fraudScoreRepositoryMock = mock(FraudScoreRepository.class);
        Person person = TestPersonFactory.getTestPerson();
        var expectedScore = 42;
        FraudScore expectedFraudScore = FraudScore.builder()
            .person(person)
            .score(expectedScore)
            .build();
        when(fraudScoreRepositoryMock.getScore(person)).thenReturn(Optional.of(expectedFraudScore));
        FraudScoreProvider fraudScoreProviderMock = mock(FraudScoreProvider.class);
        FraudScoreService fraudScoreService = new FraudScoreService(fraudScoreRepositoryMock, fraudScoreProviderMock);

        Optional<FraudScore> optionalFraudScore = fraudScoreService.calculateScoreForPerson(person);

        assertThat(optionalFraudScore.isPresent()).isTrue();
        FraudScore fraudScore = optionalFraudScore.get();
        assertThat(fraudScore.getPerson()).isNotNull();
        assertThat(fraudScore.getPerson().getEmail()).isEqualTo(person.getEmail());
        assertThat(fraudScore.getScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("Should get and store a new score when no existing score matches the request")
    public void shouldGetAndStoreNewScore() {
        Person person = TestPersonFactory.getTestPerson();
        var expectedScore = 37;
        FraudScore expectedFraudScore = FraudScore.builder()
            .person(person)
            .score(expectedScore)
            .build();
        FraudScoreRepository fraudScoreRepositoryMock = mock(FraudScoreRepository.class);
        when(fraudScoreRepositoryMock.getScore(person)).thenReturn(Optional.empty());
        FraudScoreProvider fraudScoreProviderMock = mock(FraudScoreProvider.class);
        when(fraudScoreProviderMock.getLatestScore(person)).thenReturn(Optional.of(expectedFraudScore));
        FraudScoreService fraudScoreService = new FraudScoreService(fraudScoreRepositoryMock, fraudScoreProviderMock);

        Optional<FraudScore> optionalFraudScore = fraudScoreService.calculateScoreForPerson(person);

        assertThat(optionalFraudScore.isPresent()).isTrue();
        FraudScore retrievedFraudScore = optionalFraudScore.get();
        assertThat(retrievedFraudScore.getPerson()).isNotNull();
        assertThat(retrievedFraudScore.getPerson().getEmail()).isEqualTo(person.getEmail());
        assertThat(retrievedFraudScore.getScore()).isEqualTo(expectedScore);
        verify(fraudScoreRepositoryMock, times(1)).saveScore(retrievedFraudScore);
    }

    @Test
    @DisplayName("Should not provide a score when it's unavailable")
    public void shouldNotProvideScoreWhenUnavailable() {
        Person person = TestPersonFactory.getTestPerson();
        FraudScoreRepository fraudScoreRepositoryMock = mock(FraudScoreRepository.class);
        when(fraudScoreRepositoryMock.getScore(person)).thenReturn(Optional.empty());
        FraudScoreProvider fraudScoreProviderMock = mock(FraudScoreProvider.class);
        when(fraudScoreProviderMock.getLatestScore(person)).thenReturn(Optional.empty());
        FraudScoreService fraudScoreService = new FraudScoreService(fraudScoreRepositoryMock, fraudScoreProviderMock);

        Optional<FraudScore> optionalFraudScore = fraudScoreService.calculateScoreForPerson(person);

        assertThat(optionalFraudScore.isPresent()).isFalse();
    }
}
