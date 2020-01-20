package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.training.model.dao.NoteBookDao;
import ua.training.model.entity.NoteBook;

public class JDBCNotebookDao implements NoteBookDao {

    private Connection connection;

    JDBCNotebookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(NoteBook noteBook) {
        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO notebook (firstName , loginData)" +
                        " VALUES (? ,?)")) {
            ps.setString(1 , noteBook.getFirstName());
            ps.setString(2 ,noteBook.getLoginData());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NoteBook findById(int id) {
        try (PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM notebook WHERE id = ?")){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                NoteBook result = extractFromResultSet(rs);
                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private NoteBook extractFromResultSet(ResultSet rs)
            throws SQLException {
        NoteBook result = new NoteBook();
        result.setId(rs.getInt("id") );
        result.setFirstName( rs.getString("firstName"));
        result.setLoginData( rs.getString("loginData"));
        return result;
    }

    @Override
    public List<NoteBook> findAll() {
        List<NoteBook> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery("SELECT * FROM notebook");

            while ( rs.next() ){
                NoteBook result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(NoteBook noteBook) {
        try (PreparedStatement ps = connection.prepareStatement(
            "UPDATE notebook SET firstName = ? , loginData = ?  " +
                    "WHERE id = ?")){
            ps.setString(1 , noteBook.getFirstName());
            ps.setString(2 ,noteBook.getLoginData());
            ps.setInt(3, noteBook.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM notebook  WHERE id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
