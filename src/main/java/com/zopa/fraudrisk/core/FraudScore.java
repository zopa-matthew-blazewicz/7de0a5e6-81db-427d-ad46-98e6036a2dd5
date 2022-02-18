package com.zopa.fraudrisk.core;

import java.util.Objects;

public class FraudScore {

    private final Person person;

    private final int score;

    private FraudScore(Person person, int score) {
        this.person = person;
        this.score = score;
    }

    public Person getPerson() {
        return person;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FraudScore that = (FraudScore) o;
        return score == that.score && person.equals(that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, score);
    }

    public static class Builder {
        private Person person;
        private int score;

        public Builder person(Person person) {
            this.person = person;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public FraudScore build() {
            validatePerson();
            return new FraudScore(person, score);
        }

        private void validatePerson() {
            if (person == null) {
                throw new IllegalArgumentException("Person cannot be null.");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
