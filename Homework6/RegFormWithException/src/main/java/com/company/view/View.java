package com.company.view;

import com.company.controller.InputNoteNoteBook;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.company.view.TextConstant.*;

public class View {

    // Resource Bundle Installation's
    static String MESSAGES_BUNDLE_NAME = "messages";
    public static final ResourceBundle bundle =
            ResourceBundle.getBundle(
                    MESSAGES_BUNDLE_NAME,
                    new Locale("ua", "UA"));  // Ukrainian
                    //new Locale("en"));        // English

    //Utilities methods
    /**
     *
     * @param message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     *
     * @param message
     * @return
     */
    public String concatenationString(String... message){
            StringBuilder concatString = new StringBuilder();
            for(String v : message) {
                concatString = concatString.append(v);
            }
            return new String(concatString);
    }

    public void printStringInput(String message) {
        printMessage(concatenationString(
                bundle.getString(INPUT_STRING_DATA),
                bundle.getString(message)));
    }

    public void printWrongStringInput(String message) {
        printMessage(concatenationString(
                bundle.getString(WRONG_INPUT_DATA),
                bundle.getString(INPUT_STRING_DATA),
                bundle.getString(message)));
    }

    public void printTryAnotherLogin(String takenLogin, String specialMessageOfThisClass) {
        printMessage(concatenationString(
                bundle.getString(THE_LOGIN_YOU_ENTERED),
                " \""+takenLogin+"\" ",
                bundle.getString(IS_ALREADY_TAKEN)));

        printMessage(bundle.getString(ALSO_MODEL_SAYS));

        printMessage(concatenationString(
                "e.getSpecialMessageOfThisClass = ",
                specialMessageOfThisClass));
    }

    public void printCongratulations(InputNoteNoteBook inputNoteNoteBook) {
        printMessage(bundle.getString(CONGRATULATIONS));

        printMessage(bundle.getString(FIELDS_ARE));

        printMessage(concatenationString(
                "    firstName = ",
                inputNoteNoteBook.getFirstName()));

        printMessage(concatenationString(
                "    login = ",
                inputNoteNoteBook.getLogin()));
    }
}
