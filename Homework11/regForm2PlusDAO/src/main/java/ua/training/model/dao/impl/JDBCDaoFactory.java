package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.NoteBookDao;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public NoteBookDao createNoteBookDao() {
        return new JDBCNotebookDao(getConnection());
    }

    private Connection getConnection(){
    	String url = "jdbc:mysql://localhost:3306/notebookdao?useSSL=false&characterEncoding=utf8";
    	try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
            throw new RuntimeException(e1);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
            throw new RuntimeException(e1);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
            throw new RuntimeException(e1);
		}
    	try {
    		return DriverManager.getConnection (url, "root", "RootPa$$4here");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
            throw new RuntimeException(e1);
		}
    }

}
