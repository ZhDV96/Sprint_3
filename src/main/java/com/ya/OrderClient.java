package com.ya;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import org.apache.commons.lang3.RandomStringUtils;

public class OrderClient extends ScooterRestClient {

    private static final String BASE_URI = "/api/v1/orders";

    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(BASE_URI)
                .then();
    }

    public ValidatableResponse cancel(String orderTrack) {
        return given()
                .spec(getBaseSpec())
                .body(orderTrack)
                .when()
                .post(BASE_URI + "cancel")
                .then();
    }

    public ValidatableResponse check(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .get(BASE_URI)
                .then();
    }

}
