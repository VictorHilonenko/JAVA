package ua.training.controller;

import java.util.Scanner;

import ua.training.model.Model;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.NoteBookDao;
import ua.training.model.entity.NotUniqueLoginException;
import ua.training.model.entity.NoteBook;
import ua.training.view.View;

/**
 * Created by student on 26.09.2017.
 */
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
        inputNoteNoteBook.inputNote();

        NoteBook noteBook = null;
        while(true) {
            try {
                noteBook = new NoteBook(inputNoteNoteBook.getFirstName(),
                        inputNoteNoteBook.getLoginData());
                break;
            } catch (NotUniqueLoginException e) {
                System.out.println("Not Unique Login " + e.getLoginData());
                e.printStackTrace();
                inputNoteNoteBook.getLoginData();
            }
        }
        System.out.println(noteBook);
        
        DaoFactory factory = DaoFactory.getInstance();
        NoteBookDao dao = factory.createNoteBookDao();

        try {
            dao.create(new NoteBook(noteBook.getFirstName(), noteBook.getLoginData()));
            System.out.println("sucessfully added to DB: "+noteBook);
        } catch (NotUniqueLoginException e) {
            e.printStackTrace();
        }
        
        
        
    }
}
