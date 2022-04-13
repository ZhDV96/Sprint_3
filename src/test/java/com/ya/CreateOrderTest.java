package com.ya;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class CreateOrderTest {

    Order order;
    OrderClient orderClient;
    OrderGenerator orderGenerator = new OrderGenerator();
    int orderTrack;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @DisplayName("Создание заказа без использования цвета")
    @Description("Непараметризированный тест на создание заказа без использования необязательных данных - цвета скутера")
    @Test
    public void createOrderWithNonExistentColour() {
        order = orderGenerator.getRandom();
        ValidatableResponse loginResponse = orderClient.create(order);
        int statusCode = loginResponse.extract().statusCode();
        assertThat("Can't create an order", statusCode, equalTo(201));
        orderTrack = loginResponse.extract().path("track");
        System.out.println(orderTrack);
        assertThat("Can't create an order or get an order track", orderTrack, notNullValue());
    }

}
