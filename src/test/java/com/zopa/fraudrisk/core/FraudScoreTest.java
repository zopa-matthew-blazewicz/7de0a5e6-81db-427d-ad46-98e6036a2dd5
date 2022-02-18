package com.zopa.fraudrisk.core;

import com.zopa.fraudrisk.common.TestPersonFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FraudScoreTest {

    @Test
    @DisplayName("Should ensure a FraudScore cannot have a null person")
    public void shouldValidatePersonNotNull() {
        assertThatThrownBy(FraudScore.builder().person(null)::build)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Person cannot be null.");
    }

    @Test
    @DisplayName("Should build a FraudScore given a Person and a score")
    public void shouldBuildAPerson() {
        Person expectedPerson = TestPersonFactory.getTestPerson();
        int expectedScore = 37;

        FraudScore fraudScore = FraudScore.builder()
            .person(expectedPerson)
            .score(expectedScore)
            .build();

        assertThat(fraudScore.getPerson()).isEqualTo(expectedPerson);
        assertThat(fraudScore.getScore()).isEqualTo(expectedScore);
    }
}
