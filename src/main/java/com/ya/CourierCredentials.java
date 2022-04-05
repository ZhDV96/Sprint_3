package com.ya;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourierCredentials  {

    private String login;
    private Integer password;

    public CourierCredentials(String login, Integer password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials() {

    }

    public CourierCredentials(String login) {
        this.login = login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public int getPassword() {
        return password;
    }
}
