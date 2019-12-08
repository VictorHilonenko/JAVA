package com.company.controller;

import com.company.model.Model;
import com.company.model.ThisLoginIsAlreadyTakenException;
import com.company.view.View;

import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        Scanner sc = new Scanner(System.in);

        InputNoteNoteBook inputNoteNoteBook = new InputNoteNoteBook(view, sc);

        while (true) {
            inputNoteNoteBook.inputNote();

            try {
                model.processNewNote(inputNoteNoteBook);
                break;
            } catch (ThisLoginIsAlreadyTakenException e) {
                view.printTryAnotherLogin(e.getTakenLogin(), e.getSpecialMessageOfThisClass());
            }
        }

        view.printCongratulations(inputNoteNoteBook);
    }
}
