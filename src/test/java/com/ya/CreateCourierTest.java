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

public class CreateCourierTest {

    Courier courier;
    CourierClient courierClient;
    CourierGenerator courierGenerator = new CourierGenerator();
    int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = courierGenerator.getRandom();
    }

    @Test
    public void createCourierWithValidatableData() {

        ValidatableResponse createResponse = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(courier.getLogin(), courier.getPassword()));
        int statusCode = createResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");
        System.out.println(courierId);
        assertThat("Can't create an courier", statusCode, equalTo(201));
        assertThat("Can't login courier", courierId, is(not(0)));

    }

    @Test
    public void createCouriersCopy() {

        ValidatableResponse createResponse = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(courier.getLogin(), courier.getPassword()));
        int statusCode = createResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");
        assertThat("Error: You can`t create a copy of courier", statusCode, equalTo(409));

    }

    @Test
    public void createCourierWithNoLogin() {

        courier = courierGenerator.getRandomWithoutLogin();
        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();
        assertThat("Error: You can`t create a courier without login", statusCode, equalTo(400));

    }

    @Test
    public void createCourierWithNoPassword() {

        courier = courierGenerator.getRandomWithoutPassword();
        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();
        assertThat("Error: You can`t create a courier without login", statusCode, equalTo(400));

    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }




}
