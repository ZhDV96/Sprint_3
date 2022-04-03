package com.ya;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator extends ScooterRestClient {

    public Courier getRandom() {
        String login = RandomStringUtils.randomAlphabetic(10, 15);
        int password = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        String firstName = RandomStringUtils.randomAlphabetic(3, 12);

        return new Courier(login, password, firstName);
    }

    public Courier getRandomWithoutFirstName() {
        String login = RandomStringUtils.randomAlphabetic(10, 15);
        int password = Integer.parseInt(RandomStringUtils.randomNumeric(4));

        return new Courier(login, password);
    }

    public Courier getRandomWithoutLogin() {
        int password = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        String firstName = RandomStringUtils.randomAlphabetic(3, 12);

        return new Courier(password, firstName);
    }

    public Courier getRandomWithoutPassword() {
        String login = RandomStringUtils.randomAlphabetic(10, 15);
        String firstName = RandomStringUtils.randomAlphabetic(3, 12);

        return new Courier(login, firstName);
    }

}
