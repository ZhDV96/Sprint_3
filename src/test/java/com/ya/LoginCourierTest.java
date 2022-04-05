package com.ya;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.apache.commons.lang3.RandomStringUtils;

public class LoginCourierTest {

    Courier courier;
    CourierClient courierClient;
    CourierGenerator courierGenerator = new CourierGenerator();
    int courierId;
    String randomLogin = RandomStringUtils.randomAlphabetic(10);
    int randomPassword = Integer.parseInt(RandomStringUtils.randomNumeric(3));

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = courierGenerator.getRandom();
        courierClient.create(courier);
    }

    @Test
    public void loginCourierWithValidateData() {

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
        String responseMessage = loginResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Недостаточно данных для входа"));
        assertThat("Courier can't login", statusCode, equalTo(400));


    }

    @Test
    public void loginCourierWithNonExistentData() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(randomLogin, randomPassword));
        int statusCode = loginResponse.extract().statusCode();
        String responseMessage = loginResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Учетная запись не найдена"));
        assertThat("Courier can't login", statusCode, equalTo(404));


    }

    @Test
    public void loginCourierWithoutPassword() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(courier.getLogin(), null));
        int statusCode = loginResponse.extract().statusCode();
        String responseMessage = loginResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Недостаточно данных для входа"));
        assertThat("Courier can't login", statusCode, equalTo(400));


    }


    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

}
