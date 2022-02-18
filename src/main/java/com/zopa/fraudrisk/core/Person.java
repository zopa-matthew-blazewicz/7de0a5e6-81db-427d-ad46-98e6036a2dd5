package com.zopa.fraudrisk.core;

import java.util.Objects;
import java.util.regex.Pattern;

public class Person {

    private static final Pattern VALID_EMAIL_PATTERN = Pattern.compile(".+@.+");

    private final String email;
    private final String firstName;
    private final String lastName;
    private final String postCode;

    private Person(String email, String firstName, String lastName, String postCode) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return email.equals(person.email) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName,
            person.lastName) && Objects.equals(postCode, person.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, postCode);
    }

    public static class Builder {
        private String email;
        private String firstName;
        private String lastName;
        private String postCode;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Person build() {
            validateEmail();
            return new Person(email, firstName, lastName, postCode);
        }

        private void validateEmail() {
            if (email == null) {
                throw new IllegalArgumentException("Email cannot be null.");
            } else {
                if (!VALID_EMAIL_PATTERN.matcher(email).matches()) {
                    throw new IllegalArgumentException("Invalid email format.");
                }
            }
        }
    }

    public static Person.Builder builder() {
        return new Person.Builder();
    }
}
