package com.ya;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class GetOrdersListTest {

    Order order = new Order();
    OrderClient orderClient;
    ArrayList<String> ordersList = new ArrayList<>();

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @DisplayName("Получение списка заказов")
    @Description("Тест, проверяющий возможность получения списка заказов")
    @Test
    public void getOrdersListWithoutOptionalData() {
        ValidatableResponse loginResponse = orderClient.check(order);
        int statusCode = loginResponse.extract().statusCode();
        assertThat("Can't check a list of orders", statusCode, equalTo(200));
        ordersList = loginResponse.extract().path("orders");
        System.out.println(ordersList);
        assertThat("List of orders is empty", ordersList, notNullValue());
    }

}
