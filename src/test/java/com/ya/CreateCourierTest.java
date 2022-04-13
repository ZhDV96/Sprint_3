package com.ya;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class CreateCourierTest {

    CourierClient courierClient = new CourierClient();
    CourierGenerator courierGenerator = new CourierGenerator();
    int courierId;
    Courier courier = courierGenerator.getRandom();
    ValidatableResponse createResponse = courierClient.create(courier);

    @Before
    public void setUp() {

    }

    @DisplayName("Создание курьера с валидными данными")
    @Description("Первый тест, проверяющий корректную работу api/v1/courier/")
    @Test
    public void createCourierWithValidatableData() {

        ValidatableResponse loginResponse = courierClient.login(new CourierCredentials(courier.getLogin(), courier.getPassword()));
        int statusCode = createResponse.extract().statusCode();
        assertThat("Can't create an courier", statusCode, equalTo(201));
        courierId = loginResponse.extract().path("id");
        Boolean responseMessage = createResponse.extract().path("ok");
        System.out.println(courierId);
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo(true));
        assertThat("Can't login courier", courierId, is(not(0)));

    }

    @DisplayName("Создание копии курьера с ранее использованными данными")
    @Description("Тест, проверяющий возможность создания в api/v1/courier/ второго курьера с ранее использованными данными")
    @Test
    public void createCouriersCopy() {

        ValidatableResponse createCopyResponse = courierClient.create(courier);
        int CopyStatusCode = createCopyResponse.extract().statusCode();
        assertThat("Error: You can`t create a copy of courier", CopyStatusCode, equalTo(409));
        String responseMessage = createCopyResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    @DisplayName("Создание курьера без использования логина")
    @Description("Тест, проверяющий возможность создания в api/v1/courier/ курьера без логина")
    @Test
    public void createCourierWithNoLogin() {

        courier = courierGenerator.getRandomWithoutLogin();
        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();
        assertThat("Error: You can`t create a courier without login", statusCode, equalTo(400));
        String responseMessage = createResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Недостаточно данных для создания учетной записи"));

    }

    @DisplayName("Создание курьера без использования пароля")
    @Description("Тест, проверяющий возможность создания в api/v1/courier/ курьера без пароля")
    @Test
    public void createCourierWithNoPassword() {

        courier = courierGenerator.getRandomWithoutPassword();
        ValidatableResponse createResponse = courierClient.create(courier);
        int statusCode = createResponse.extract().statusCode();
        assertThat("Error: You can`t create a courier without login", statusCode, equalTo(400));
        String responseMessage = createResponse.extract().path("message");
        System.out.println(responseMessage);
        assertThat("Response is incorrect", responseMessage, equalTo("Недостаточно данных для создания учетной записи"));

    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }




}
