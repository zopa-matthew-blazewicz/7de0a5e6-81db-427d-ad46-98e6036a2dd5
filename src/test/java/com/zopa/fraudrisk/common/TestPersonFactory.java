package com.zopa.fraudrisk.common;

import com.zopa.fraudrisk.core.Person;

import java.util.Random;

public class TestPersonFactory {

    private static final Random random = new Random(System.currentTimeMillis());

    private static final String[] firstNames = {"Alex", "Jamie", "Jackie", "Bobbie", "Noel", "Casie", "Jude"};
    private static final String[] lastNames = {"Smith", "Jones", "Williams", "Taylor", "Davies", "Brown", "Evans"};

    public static Person getTestPerson() {
        String randomFirstName = firstNames[random.nextInt(firstNames.length)];
        String randomLastName = lastNames[random.nextInt(lastNames.length)];
        return Person.builder()
            .firstName(randomFirstName)
            .lastName(randomLastName)
            .email(randomFirstName.toLowerCase() + "." + randomLastName.toLowerCase() + "@zest.zopa.com")
            .postCode("Z0P4 " + random.nextInt(1000))
            .build();
    }
}
