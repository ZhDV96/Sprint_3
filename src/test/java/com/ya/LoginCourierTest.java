package com.ya;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginCourierTest {

    Courier courier;
    CourierClient courierClient;
    CourierGenerator courierGenerator = new CourierGenerator();
    int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = courierGenerator.getRandom();
        courierClient.create(courier);
    }

    @Test
    public void loginCourierWithValidatableData() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(courier.getLogin(), courier.getPassword()));
        int statusCode = loginResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");
        System.out.println(courierId);
        assertThat("Courier can't login", statusCode, equalTo(200));
        assertThat("Can't login courier", courierId, is(not(0)));

    }

    @Test
    public void loginCourierWithoutLogin() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(null, courier.getPassword()));
        int statusCode = loginResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");
        assertThat("Courier can't login", statusCode, equalTo(400));

    }


    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

}
