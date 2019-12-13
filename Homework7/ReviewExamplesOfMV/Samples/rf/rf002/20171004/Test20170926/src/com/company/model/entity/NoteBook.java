package com.company.model.entity;

/**
 * Created by student on 04.10.2017.
 */
public class NoteBook {
    private String firstName;
    private String loginData;

    public NoteBook(String firstName, String loginData)
                            throws NotUniqueLoginException{
        if (DBNoteBook.checkLogin(loginData)){
                throw new NotUniqueLoginException("Not Unique Login",
                        loginData);
        }
        this.firstName = firstName;
        this.loginData = loginData;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLoginData() {
        return loginData;
    }
    public void setLoginData(String loginData) {
        this.loginData = loginData;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "firstName='" + firstName + '\'' +
                ", loginData='" + loginData + '\'' +
                '}';
    }
}
