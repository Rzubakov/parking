package jpa;

import entity.Price;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;

@Stateless
public class PriceDAO extends AbstractService<Price> {

    public PriceDAO() {
        super();
    }

    public List<Price> getPriceByDay(Date date) {
        return manager.createNamedQuery("Price.getByDay").setParameter("date", date, TemporalType.DATE).getResultList();
    }
}
