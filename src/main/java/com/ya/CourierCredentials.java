package com.ya;

public class CourierCredentials  {

    private String login;
    private int password;

    public CourierCredentials(String login, int password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials(int password) {
        this.password = password;
    }

    public CourierCredentials(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public int getPassword() {
        return password;
    }
}
