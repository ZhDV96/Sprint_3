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
        Boolean responseMessage = createResponse.extract().path("ok");
        System.out.println(courierId);
        System.out.println(responseMessage);
        assertThat("Can't create an courier", statusCode, equalTo(201));
        assertThat("Response is incorrect", responseMessage, equalTo(true));
        assertThat("Can't login courier", courierId, is(not(0)));

    }

    @Test
    public void createCouriersCopy() {

        Courier courierCopy = new Courier(courier.getLogin(), courier.getPassword(), courier.getFirstName());
        ValidatableResponse createCopyResponse = courierClient.create(courierCopy);
        int statusCode = createCopyResponse.extract().statusCode();
        String responseMessage = createCopyResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Этот логин уже используется"));
        assertThat("Error: You can`t create a copy of courier", statusCode, equalTo(409));


    }

    @Test
    public void createCourierWithNoLogin() {

        courier = courierGenerator.getRandomWithoutLogin();
        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();
        String responseMessage = createResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Недостаточно данных для создания учетной записи"));
        assertThat("Error: You can`t create a courier without login", statusCode, equalTo(400));

    }

    @Test
    public void createCourierWithNoPassword() {

        courier = courierGenerator.getRandomWithoutPassword();
        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();
        String responseMessage = createResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Недостаточно данных для создания учетной записи"));
        assertThat("Error: You can`t create a courier without login", statusCode, equalTo(400));

    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }




}
