package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Manufacturer {
    private int id;
    private String manufacture;
    private int discount;

    private List<Car> cars = new ArrayList<>();
    private List<Shop> shops = new ArrayList<>();

    public Manufacturer() {
    }

    public Manufacturer(int id, String manufacture, int discount, List<Car> cars, List<Shop> shops) {
        this.id = id;
        this.manufacture = manufacture;
        this.discount = discount;
        this.cars = cars;
        this.shops = shops;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getManufacture() {
        return manufacture;
    }
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public List<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    public List<Shop> getShops() {
        return shops;
    }
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                System.identityHashCode(this)+
                ", id=" + id +
                ", manufacture='" + manufacture + '\'' +
                ", discount=" + discount +
                '}';
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manufacturer)) return false;

        Manufacturer that = (Manufacturer) o;

        return getManufacture() != null ?
               getManufacture().equals(that.getManufacture())
               : that.getManufacture() == null;
    }

    @Override
    public int hashCode() {
        return getManufacture() != null ?
               getManufacture().hashCode() : 0;
    }*/
}
