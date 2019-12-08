package com.company.model;

import com.company.controller.InputNoteNoteBook;

public class Model {
    public void processNewNote(InputNoteNoteBook inputNoteNoteBook) throws ThisLoginIsAlreadyTakenException {
        if (System.currentTimeMillis() % 2 == 0) {

            ThisLoginIsAlreadyTakenException e = new ThisLoginIsAlreadyTakenException();
            e.setSpecialMessageOfThisClass("<<<some special message here>>>");
            e.setTakenLogin(inputNoteNoteBook.getLogin());

            inputNoteNoteBook.setLogin("");

            throw e;
        }

        //TODO add a record to DB:
        //...this action is out of the task in this Homework...
    }
}
