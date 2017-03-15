/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @Override
    public void delete(Price t) {
        manager.remove(t);
    }

    @Override
    public void add(Price t) {
        manager.persist(t);
    }

    @Override
    public void update(Price t) {
        manager.merge(t);
    }

    @Override
    public Price get(Long t) {
        return manager.find(Price.class, t);
    }

    public List<Price> getPriceByDay(Date date) {
        return manager.createNamedQuery("Price.getByDay").setParameter("date", date, TemporalType.DATE).getResultList();
    }
}
