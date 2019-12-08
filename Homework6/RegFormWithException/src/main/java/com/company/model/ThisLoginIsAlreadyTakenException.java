package com.company.model;

public class ThisLoginIsAlreadyTakenException extends Exception {
    private String specialMessageOfThisClass = "";
    private String takenLogin = "";

    public String getSpecialMessageOfThisClass() {
        return specialMessageOfThisClass;
    }

    public void setSpecialMessageOfThisClass(String specialMessageOfThisClass) {
        this.specialMessageOfThisClass = specialMessageOfThisClass;
    }

    public String getTakenLogin() {
        return takenLogin;
    }

    public void setTakenLogin(String takenLogin) {
        this.takenLogin = takenLogin;
    }
}
