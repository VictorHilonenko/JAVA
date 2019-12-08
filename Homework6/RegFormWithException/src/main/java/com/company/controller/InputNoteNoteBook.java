package com.company.controller;

import com.company.view.View;

import java.util.Scanner;

import static com.company.controller.RegexContainer.*;
import static com.company.controller.RegexContainer.REGEX_LOGIN;
import static com.company.view.TextConstant.*;
import static com.company.view.TextConstant.LOGIN_DATA;

public class InputNoteNoteBook {
    private UtilityController utilityController;

    private String firstName;
    private String login;

    InputNoteNoteBook(View view, Scanner sc) {
        this.utilityController = new UtilityController(sc, view);
        this.firstName = "";
        this.login = "";
    }

    void inputNote() {

        if("".equals(firstName)) {
            String regexName = (String.valueOf(View.bundle.getLocale()).equals("ua")) ? REGEX_NAME_UKR : REGEX_NAME_LAT;
            firstName = utilityController.inputStringValueWithScanner(FIRST_NAME, regexName);
        }

        if("".equals(login)) {
            login = utilityController.inputStringValueWithScanner(LOGIN_DATA, REGEX_LOGIN);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
