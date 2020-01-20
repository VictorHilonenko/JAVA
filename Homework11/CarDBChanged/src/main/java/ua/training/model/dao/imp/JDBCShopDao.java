package ua.training.model.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.training.model.dao.ShopDao;
import ua.training.model.entity.Shop;

public class JDBCShopDao implements ShopDao {
    private Connection connection;

    public JDBCShopDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Shop entity) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO `shop` (`name`, `telephone`, `idmanufacturer`)" +
				" VALUES (?, ?, ?)");
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getTelephone());
			ps.setLong(3, entity.getManufacturer().getId());
	        ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException();
		}
    }

    @Override
    public Shop findById(int id) {
        return null;
    }

    @Override
    public List<Shop> findAll() {
        return null;
    }

    @Override
    public void update(Shop entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() throws Exception {

    }
}
