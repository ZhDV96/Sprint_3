package com.ya;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.apache.commons.lang3.RandomStringUtils;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class LoginCourierTest {

    Courier courier;
    CourierClient courierClient;
    CourierGenerator courierGenerator = new CourierGenerator();
    int courierId;
    String randomLogin = RandomStringUtils.randomAlphabetic(10);
    String randomPassword = RandomStringUtils.randomAlphabetic(4);

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = courierGenerator.getRandom();
        courierClient.create(courier);
    }

    @DisplayName("Использование курьером учетной записи с валидными данными")
    @Description("Тест, проверяющий возможность логина курьера с валидными данными")
    @Test
    public void loginCourierWithValidateData() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(courier.getLogin(), courier.getPassword()));
        int statusCode = loginResponse.extract().statusCode();
        assertThat("Courier can't login", statusCode, equalTo(200));
        courierId = loginResponse.extract().path("id");
        System.out.println(courierId);
        assertThat("Can't login courier", courierId, is(not(0)));

    }

    @DisplayName("Использование курьером учетной записи без логина")
    @Description("Тест, проверяющий возможность логина курьера при отсутствии части данных")
    @Test
    public void loginCourierWithoutLogin() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(null, courier.getPassword()));
        int statusCode = loginResponse.extract().statusCode();
        assertThat("Courier can't login", statusCode, equalTo(400));
        String responseMessage = loginResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Недостаточно данных для входа"));

    }

    @DisplayName("Использование курьером учетной записи с несуществующими данными")
    @Description("Тест, проверяющий возможность логина курьера при использовании несуществующих данных")
    @Test
    public void loginCourierWithNonExistentData() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(randomLogin, randomPassword));
        int statusCode = loginResponse.extract().statusCode();
        assertThat("Courier can't login", statusCode, equalTo(404));
        String responseMessage = loginResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Учетная запись не найдена"));


    }

    @DisplayName("Использование курьером учетной записи без пароля")
    @Description("Тест, проверяющий возможность логина курьера при отсутствии части данных")
    @Test
    public void loginCourierWithoutPassword() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(courier.getLogin(), null));
        int statusCode = loginResponse.extract().statusCode();
        assertThat("Courier can't login", statusCode, equalTo(400));
        String responseMessage = loginResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Недостаточно данных для входа"));


    }


    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

}
