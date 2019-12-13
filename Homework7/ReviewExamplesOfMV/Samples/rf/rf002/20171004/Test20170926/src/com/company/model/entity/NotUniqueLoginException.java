package com.company.model.entity;

/**
 * Created by student on 04.10.2017.
 */
public class NotUniqueLoginException extends Exception {
    private String loginData;

    public String getLoginData() {
        return loginData;
    }

    public NotUniqueLoginException(String message, String loginData) {
        super(message);
        this.loginData = loginData;
    }
}
