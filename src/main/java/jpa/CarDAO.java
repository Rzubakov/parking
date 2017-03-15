package jpa;

import entity.Car;
import javax.ejb.Stateless;

@Stateless
public class CarDAO extends AbstractService<Car> {

    public CarDAO() {
        super();
    }

    @Override
    public void delete(Car t) {
        manager.remove(t);
    }

    @Override
    public void add(Car t) {
        manager.persist(t);
    }

    @Override
    public void update(Car t) {
        manager.merge(t);
    }

    @Override
    public Car get(Long t) {
        return manager.find(Car.class, t);
    }

    public Car getByNumber(String number) {
        return (Car) manager.createNamedQuery("Car.getByNumber").setParameter("number", number).getSingleResult();
    }

    public Long getCount() {
        return (Long) manager.createNamedQuery("Car.getAll").getSingleResult();
    }

    public Long checkNumber(String number) {
        System.out.println(number);
        return (Long) manager.createNamedQuery("Car.checkNumber").setParameter("number", number).getSingleResult();
    }

}
