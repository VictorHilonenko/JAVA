package ua.training.model.dao;

import ua.training.model.entity.NoteBook;

import java.util.List;

public interface NoteBookDao extends AutoCloseable {
        void create(NoteBook noteBook);
        NoteBook findById(int id);
        List<NoteBook> findAll();
        void update(NoteBook entity);
        void delete(int id);
}
