package com.ya;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.*;
import java.util.List;
import io.qameta.allure.Step;

public class OrderGenerator extends ScooterRestClient {

    // create a clock
    ZoneId zid = ZoneId.of("Europe/Paris");

    // create an LocalDate object using now(zoneId)
    LocalDate lt = LocalDate.now(zid);
    private List<String> color;

    @Step("Создание набора случайных данных для создания заказа")
    public Order getRandom(List<String> color) {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphabetic(10);
        String metroStation = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomAlphanumeric(11);
        int rentTime = Integer.parseInt(RandomStringUtils.randomNumeric(3));
        String deliveryDate = String.valueOf(lt);
        String comment = RandomStringUtils.randomAlphabetic(10);
        this.color = color;
        
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    @Step("Создание набора случайных данных для создания заказа")
    public Order getRandom() {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphabetic(10);
        String metroStation = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomAlphanumeric(11);
        int rentTime = Integer.parseInt(RandomStringUtils.randomNumeric(3));
        String deliveryDate = String.valueOf(lt);
        String comment = RandomStringUtils.randomAlphabetic(10);

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
    }

}
