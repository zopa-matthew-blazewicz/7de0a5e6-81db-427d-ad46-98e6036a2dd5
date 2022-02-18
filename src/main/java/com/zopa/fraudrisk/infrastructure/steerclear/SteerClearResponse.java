package com.zopa.fraudrisk.infrastructure.steerclear;

public class SteerClearResponse {

    private String email;
    private String firstName;
    private String lastName;
    private String postCode;
    private RiskGroup riskGroup;

    public SteerClearResponse(String email, String firstName, String lastName, String postCode, RiskGroup riskGroup) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.riskGroup = riskGroup;
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

    public RiskGroup getRiskGroup() {
        return riskGroup;
    }

    public void setRiskGroup(RiskGroup riskGroup) {
        this.riskGroup = riskGroup;
    }

    public enum RiskGroup {
        A,
        B,
        C,
        D,
        E,
        F
    }
}
