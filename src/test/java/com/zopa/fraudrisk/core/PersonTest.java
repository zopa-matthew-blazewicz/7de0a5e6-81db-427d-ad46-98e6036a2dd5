package com.zopa.fraudrisk.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PersonTest {

    @Test
    @DisplayName("Should ensure a Person cannot have a null email")
    public void shouldValidateEmailNotNull() {
        assertThatThrownBy(Person.builder().email(null)::build)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Email cannot be null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "@", "jackie@", "@zest.zopa.com", "not an email"})
    @DisplayName("Should ensure a Person cannot have an invalid email")
    public void shouldValidateEmailMatchesRegex(String email) {
        assertThatThrownBy(Person.builder().email(email)::build)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid email format.");
    }

    @Test
    @DisplayName("Should build a Person with a valid email")
    public void shouldBuildAPerson() {
        Person person = Person.builder()
            .email("rubi.dubi@zest.zopa.com")
            .firstName("Rubi")
            .lastName("Dubi")
            .postCode("Z0P4 333")
            .build();

        assertThat(person.getEmail()).isEqualTo("rubi.dubi@zest.zopa.com");
        assertThat(person.getFirstName()).isEqualTo("Rubi");
        assertThat(person.getLastName()).isEqualTo("Dubi");
        assertThat(person.getPostCode()).isEqualTo("Z0P4 333");
    }
}
