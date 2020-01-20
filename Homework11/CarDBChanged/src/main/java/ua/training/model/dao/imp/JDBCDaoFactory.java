package ua.training.model.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.training.model.dao.CarDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ManufacturerDao;
import ua.training.model.dao.ShopDao;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public CarDao createCarDao() {
        return new JDBCCarDao(getConnection());
    }
    @Override
    public ManufacturerDao createManufacturerDao() {
        return new JDBCManufacturerDao(getConnection());
    }
    @Override
    public ShopDao createShopDao() {
        return new JDBCShopDao(getConnection());
    }

    private Connection getConnection(){
    	String url = "jdbc:mysql://localhost:3306/mycardb?useSSL=false&characterEncoding=utf8";
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
