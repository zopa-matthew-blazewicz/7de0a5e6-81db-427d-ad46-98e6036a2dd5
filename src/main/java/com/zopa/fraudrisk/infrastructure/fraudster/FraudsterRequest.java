package com.zopa.fraudrisk.infrastructure.fraudster;

import com.zopa.fraudrisk.core.Person;

public class FraudsterRequest {

    private String emailAddress;
    private String firstName;
    private String surname;
    private String postCode;

    public FraudsterRequest() {}

    public FraudsterRequest(String emailAddress, String firstName, String surname, String postCode) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.surname = surname;
        this.postCode = postCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public static FraudsterRequest fromPerson(Person person) {
        return new FraudsterRequest(person.getEmail(), person.getFirstName(),
            person.getLastName(), person.getPostCode());
    }
}
