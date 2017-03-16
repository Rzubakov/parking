package jpa;

import entity.Car;
import javax.ejb.Stateless;

@Stateless
public class CarDAO extends AbstractService<Car> {

    public CarDAO() {
        super();
    }

    public Car getByNumber(String number) {
        return (Car) manager.createNamedQuery("Car.getByNumber").setParameter("number", number).getSingleResult();
    }

    public Long getCount() {
        return (Long) manager.createNamedQuery("Car.getAll").getSingleResult();
    }

    public Long checkNumber(String number) {
        return (Long) manager.createNamedQuery("Car.checkNumber").setParameter("number", number).getSingleResult();
    }

}
