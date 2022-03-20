import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Register {

    int courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void registerNewCourierValid() {
        String json = "{\"login\": \"test\", \"password\": \"Test123@\", \"firstName\": \"TestTest\"}";
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    public void registerNewCourierWithExitingLogin() {
        String json = "{\"login\": \"test\", \"password\": \"Test123@\", \"firstName\": \"TestTest\"}";
        Response response =
                given()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    public void registerNewCourierWithNoPassword() {
        String json = "{\"login\": \"test\", \"firstName\": \"TestTest\"}";
        Response response =
                given()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void registerNewCourierWithNoLogin() {
        String json = "{\"password\": \"Test123@\", \"firstName\": \"TestTest\"}";
        Response response =
                given()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void loginCourier() {

        String json = "{\"login\": \"test\", \"password\":\"Test123@\"}";
        Response response =
                given()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        courierId = response.then().extract().path("id");
    }

    @After
    public void deleteCourier() {
        String json = "{\"password\": \"Test123@\", \"firstName\": \"TestTest\"}";
        Response response =
                given()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/" + courierId);
        response.then().assertThat().statusCode(200);
    }


}
