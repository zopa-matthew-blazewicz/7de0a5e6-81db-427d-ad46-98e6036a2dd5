package com.zopa.fraudrisk.ui.rest;

import com.zopa.fraudrisk.core.FraudScore;

public class FraudScoreResponse {

    private String email;
    private String firstName;
    private String lastName;
    private String postCode;
    private int score;

    public FraudScoreResponse() {}

    public FraudScoreResponse(String email, String firstName, String lastName, String postCode, int score) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static FraudScoreResponse fromFraudScore(FraudScore fraudScore) {
        return new FraudScoreResponse(
            fraudScore.getPerson().getEmail(),
            fraudScore.getPerson().getFirstName(),
            fraudScore.getPerson().getLastName(),
            fraudScore.getPerson().getPostCode(),
            fraudScore.getScore());
    }
}
