package ua.training;

import java.util.List;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.NoteBookDao;
import ua.training.model.entity.NotUniqueLoginException;
import ua.training.model.entity.NoteBook;

public class Runner {
    public static void main(String[] args) {
        DaoFactory factory = DaoFactory.getInstance();
        NoteBookDao dao = factory.createNoteBookDao();

        System.out.println(dao.findById(1));

        System.out.println(dao.findAll());

        try {
            dao.create(new NoteBook("NewUser1","NewLogin1"));
        } catch (NotUniqueLoginException e) {
            e.printStackTrace();
        }

        List<NoteBook> noteBookList = dao.findAll();
        int maxId = Integer.MIN_VALUE;
        int currentId;
        for (NoteBook currentBook : noteBookList){
            if ((currentId = currentBook.getId()) > maxId){
                maxId = currentId;
            }
        }
        System.out.println(dao.findById(maxId));

        dao.update(new NoteBook(maxId,"NewUser2","NewLogin2"));
        System.out.println(dao.findById(maxId));

        dao.delete(maxId);

    }

}
