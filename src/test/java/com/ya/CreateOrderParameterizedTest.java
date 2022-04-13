package com.ya;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    Order order;
    OrderClient orderClient;
    OrderGenerator orderGenerator = new OrderGenerator();
    int orderTrack;
    List<String> color;

    @Parameterized.Parameters(name = "Test scooter color: {0}")
    public static Object[][] getColorData() {
        return new Object[][] {
                {List.of("BLACK", "GREY")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {null},
        };
    }

    public CreateOrderParameterizedTest(List<String> color) {
        this.color= color;
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @DisplayName("Создание заказа с использованием разного цвета")
    @Description("Параметризированный тест на создание заказа с использованием необязательных данных - цвета скутера")
    @Test
    public void createOrderWithNonExistentColour() {
        order = orderGenerator.getRandom(color);
        ValidatableResponse loginResponse = orderClient.create(order);
        int statusCode = loginResponse.extract().statusCode();
        assertThat("Can't create an order", statusCode, equalTo(201));
        orderTrack = loginResponse.extract().path("track");
        System.out.println(orderTrack);
        assertThat("Can't create an order or get an order track", orderTrack, notNullValue());
    }

}
