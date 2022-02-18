package com.zopa.fraudrisk.ui.rest;

import com.zopa.fraudrisk.core.Person;
import jakarta.ws.rs.QueryParam;

public class FraudScoreQuery {

    @QueryParam("email")
    private String email;

    @QueryParam("firstName")
    private String firstName;

    @QueryParam("lastName")
    private String lastName;

    @QueryParam("postCode")
    private String postCode;

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

    public Person asPerson() {
        return Person.builder()
            .email(email)
            .firstName(firstName)
            .lastName(lastName)
            .postCode(postCode)
            .build();
    }
}
