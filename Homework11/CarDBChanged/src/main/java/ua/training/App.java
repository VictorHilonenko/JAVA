package ua.training;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import ua.training.model.dao.CarDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ManufacturerDao;
import ua.training.model.dao.ShopDao;
import ua.training.model.dao.imp.JDBCManufacturerDao;

public class App 
{
	private static void infiniteRecursion() { //stackoverflow
		infiniteRecursion();
	}
	
    public static void main( String[] args )throws Exception
    {
    	//infiniteRecursion();
    	
        DaoFactory factory = DaoFactory.getInstance();
        ManufacturerDao manufacturerDao = factory.createManufacturerDao();
        CarDao carDao = factory.createCarDao();
        ShopDao shopDao = factory.createShopDao();
        
        //1
        /*List<Car> cars = new ArrayList<>();
        List<Shop> shops = new ArrayList<>();
        
        Manufacturer manufacturer1 = new Manufacturer(1, "MercedesBenz", 10, cars, shops);
        
        try {
        	manufacturerDao.create(manufacturer1);
            System.out.println("sucessfully added to DB: "+manufacturer1);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
        
        //2
        /*List<Manufacturer> manufacturers = new ArrayList<>(manufacturerDao.findAll());
        
        System.out.println(manufacturers);
        
        for(int i = 1; i <= 1000; i++) {
        	int m = i%3;
        	Car car = new Car(i, "New"+i, 95000, manufacturers.get(m));
          
			try {
				carDao.create(car);
				System.out.println("sucessfully added to DB: "+car);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        */
        
        //3
        /*List<Manufacturer> manufacturers = new ArrayList<>(manufacturerDao.findAll());
        
        System.out.println(manufacturers);
        
        for(int i = 6; i <= 50; i++) {
        	int m = i%3;
        	Shop shop = new Shop(i, "shop"+i, "+38067"+183457*i, manufacturers.get(m));
          
			try {
				shopDao.create(shop);
				System.out.println("sucessfully added to DB: "+shop);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        */

//4
    //this is a quick way to move connection for use it right here (only for learning purposes, by the way)):
    Connection con = (Connection) ((JDBCManufacturerDao) manufacturerDao).getConnection();        
	Statement query = (Statement) con.createStatement();
	//
	
	//ResultSet rs = query.executeQuery("SELECT * FROM city");
	ResultSet rs = query.executeQuery("SELECT * FROM car");
	while(rs.next()) {
		System.out.println(rs.getString("model"));
	}
	
//5
/*
queries tested in workbench:

SELECT * FROM `mycardb`.`manufacturer` LEFT JOIN `mycardb`.`shop` ON `mycardb`.`manufacturer`.`idmanufacturer` = `mycardb`.`shop`.`idmanufacturer`

*/
        }
    }

