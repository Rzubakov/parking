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

    public List<Price> getPriceByDay(Date date) {
        return manager.createNamedQuery("Price.getByDay").setParameter("date", date, TemporalType.DATE).getResultList();
    }
}
