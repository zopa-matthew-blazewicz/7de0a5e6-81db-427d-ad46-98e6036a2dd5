package com.zopa.fraudrisk.infrastructure.fraudster;

public class FraudsterResponse {

    private String emailAddress;
    private String firstName;
    private String surname;
    private String postCode;
    private int fraudRating;

    public FraudsterResponse() {}

    public FraudsterResponse(String emailAddress, String firstName, String surname, String postCode, int fraudRating) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.surname = surname;
        this.postCode = postCode;
        this.fraudRating = fraudRating;
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

    public int getFraudRating() {
        return fraudRating;
    }

    public void setFraudRating(int fraudRating) {
        this.fraudRating = fraudRating;
    }
}
