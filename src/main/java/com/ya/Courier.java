package com.ya;

public class Courier {

    private String login;
    private int password;
    private String firstName;
    private int id;

    public Courier() {
    }

    public Courier(int id) {
        this.id = id;
    }

    public Courier(String login, int password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier(String login, int password) {
        this.login = login;
        this.password = password;
    }

    public Courier(int password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }

    public Courier(String login, String firstName) {
        this.login = login;
        this.firstName = firstName;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
