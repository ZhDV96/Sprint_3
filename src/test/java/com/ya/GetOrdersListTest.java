package com.ya;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertFalse;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;

public class GetOrdersListTest {

    Order order = new Order();
    OrderClient orderClient;
    ArrayList<String> ordersList = new ArrayList<>();


    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void getOrdersListWithoutOptionalData() {
        ValidatableResponse loginResponse = orderClient.check(order);
        int statusCode = loginResponse.extract().statusCode();
        ordersList = loginResponse.extract().path("orders");
        System.out.println(ordersList);
        assertThat("Can't check a list of orders", statusCode, equalTo(200));
        assertThat(ordersList, notNullValue());
    }

}
