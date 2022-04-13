package com.ya;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator extends ScooterRestClient {

    @Step("Создание набора случайных данных для создания курьера")
    public Courier getRandom() {
        String login = RandomStringUtils.randomAlphabetic(10, 15);
        String password = RandomStringUtils.randomAlphabetic(4);
        String firstName = RandomStringUtils.randomAlphabetic(3, 12);

        return new Courier(login, password, firstName);
    }

    @Step("Создание набора случайных данных для создания курьера без имени")
    public Courier getRandomWithoutFirstName() {
        String login = RandomStringUtils.randomAlphabetic(10, 15);
        String password = RandomStringUtils.randomAlphabetic(4);

        return new Courier(login, password);
    }

    @Step("Создание набора случайных данных для создания курьера без логина")
    public Courier getRandomWithoutLogin() {
        String password = RandomStringUtils.randomAlphabetic(4);
        String firstName = RandomStringUtils.randomAlphabetic(3, 12);

        return new Courier(null, password, firstName);
    }

    @Step("Создание набора случайных данных для создания курьера без пароля")
    public Courier getRandomWithoutPassword() {
        String login = RandomStringUtils.randomAlphabetic(10, 15);
        String firstName = RandomStringUtils.randomAlphabetic(3, 12);

        return new Courier(login, null, firstName);
    }

}
