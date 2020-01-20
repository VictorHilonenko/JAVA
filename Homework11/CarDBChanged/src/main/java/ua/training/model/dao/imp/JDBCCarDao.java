package ua.training.model.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.training.model.dao.CarDao;
import ua.training.model.entity.Car;
import ua.training.model.entity.Manufacturer;

public class JDBCCarDao implements CarDao {

    private Connection connection;

    public JDBCCarDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Car entity) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO `car` (`model`, `price`, `idmanufacturer`)" +
				" VALUES (?, ?, ?)");
			ps.setString(1, entity.getModel());
			ps.setLong(2, entity.getPrice());
			ps.setLong(3, entity.getManufacturer().getId());
	        ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException();
		}
    }

    @Override
    public Car findById(int id) {
        return null;
    }

    private Car extractFromResultSet(ResultSet rs)
            throws SQLException {
        Car result = new Car();

        result.setId(rs.getInt("idcar") );
        result.setModel( rs.getString("model") );
        result.setPrice( rs.getInt("price"));


        return result;
    }

    @Override
    public List<Car> findAll() {
        List<Car> resultList = new ArrayList<>();
        Map<String, Manufacturer> manufacturers = new HashMap<>();
        try (Statement ps = connection.createStatement()){
                ResultSet rs = ps.executeQuery(
                        "select * from car left join manufacturer " +
                                "using (idmanufacturer)");
            while ( rs.next() ){
                Car result = extractFromResultSet(rs);
                Manufacturer manufacturer =
                        JDBCManufacturerDao.extractFromResultSet(rs);

                makeUniqueManufacturer(manufacturers, result, manufacturer);

                resultList.add(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    private void makeUniqueManufacturer(Map<String, Manufacturer>
            manufacturers, Car result, Manufacturer manufacturer) {
        System.out.println("Before " + manufacturer);
        manufacturers.putIfAbsent(manufacturer.getManufacture(),
                manufacturer);
        manufacturer = manufacturers.get(manufacturer.getManufacture());
        System.out.println(manufacturer);

        result.setManufacturer(manufacturer);
        manufacturer.getCars().add(result);
    }

    @Override
    public void update(Car entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() throws Exception {

    }
}
