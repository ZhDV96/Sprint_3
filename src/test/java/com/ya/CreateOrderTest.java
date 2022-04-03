package com.ya;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertFalse;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    Order order;
    OrderClient orderClient;
    OrderGenerator orderGenerator = new OrderGenerator();
    int orderTrack;



    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void createOrderWithNonExistentColour() {
        order = orderGenerator.getRandom();
        ValidatableResponse loginResponse = orderClient.create(order);
        int statusCode = loginResponse.extract().statusCode();
        orderTrack = loginResponse.extract().path("track");
        System.out.println(orderTrack);
        assertThat("Can't create an order", statusCode, equalTo(201));
        assertThat(orderTrack, notNullValue());
    }

}
