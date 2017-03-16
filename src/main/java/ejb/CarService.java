package ejb;

import entity.Car;
import entity.Price;
import jpa.CarDAO;
import jpa.PriceDAO;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CarService {

    @EJB
    private CarDAO carDAO;
    @EJB
    private PriceDAO priceDAO;

    public CarService() {
    }

    public Car getCarById(Long id) {
        return carDAO.get(id);
    }

    public Car getCarByNumber(String number) {
        return carDAO.getByNumber(number);
    }

    public Long getCount() {
        return carDAO.getCount();
    }

    public Car putCar(Car car) {
        if (carDAO.getCount() < 10 && (carDAO.checkNumber(car.getNumber()) == 0)) {
            carDAO.add(car);
            return car;
        } else {
            return null;
        }
    }

    public void deleteCar(Car car) {
        priceDAO.add(new Price(calculatePrice(car)));
        carDAO.delete(car);
    }

    public Long getFree() {
        return 10 - carDAO.getCount();
    }

    public Double getPriceForDay(Date date) {
        Double price = 0.0;
        for (Price p : priceDAO.getPriceByDay(date)) {
            price += p.getPrice();
        }
        return price;
    }

    private Double calculatePrice(Car car) {
        Calendar putTime = Calendar.getInstance();
        Calendar getTime = Calendar.getInstance();
        putTime.setTime(car.getTime());
        getTime.setTime(new Date());
        return Math.ceil((getTime.getTimeInMillis() - putTime.getTimeInMillis() / 1000) * (100.0/3600)));
    }

}
