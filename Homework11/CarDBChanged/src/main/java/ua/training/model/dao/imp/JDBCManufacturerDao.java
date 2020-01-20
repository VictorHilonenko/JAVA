package ua.training.model.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.training.model.dao.ManufacturerDao;
import ua.training.model.entity.Manufacturer;

public class JDBCManufacturerDao implements ManufacturerDao {
    private Connection connection;

    public JDBCManufacturerDao(Connection connection) {
        this.connection = connection;
    }
    
    public Connection getConnection() {
		return connection;
	}
    
    @Override
    public void create(Manufacturer entity) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO `manufacturer` (`manufacture`, `discount`)" +
				" VALUES (?, ?)");
			ps.setString(1, entity.getManufacture());
			ps.setLong(2, entity.getDiscount());
	        ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException();
		}
    }

    @Override
    public Manufacturer findById(int id) {
        return null;
    }

    @Override
    public List<Manufacturer> findAll() {
        List<Manufacturer> resultList = new ArrayList<>();
        try {
        	Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("select * from manufacturer");
            while (rs.next()) {
            	Manufacturer result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    static Manufacturer extractFromResultSet(ResultSet rs)throws Exception{
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(rs.getInt("idmanufacturer"));
        manufacturer.setManufacture(rs.getString("manufacture"));
        manufacturer.setDiscount(rs.getInt("discount"));
        return manufacturer;
    }

    @Override
    public void update(Manufacturer entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() throws Exception {

    }
}
