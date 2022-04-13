package com.ya;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient extends ScooterRestClient {

    private static final String BASE_URI = "/api/v1/orders";

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(BASE_URI)
                .then();
    }

    @Step("Отмена заказа")
    public ValidatableResponse cancel(String orderTrack) {
        return given()
                .spec(getBaseSpec())
                .body(orderTrack)
                .when()
                .post(BASE_URI + "cancel")
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse check(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .get(BASE_URI)
                .then();
    }

}
