package ua.training.model.entity;

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
