package entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
    @NamedQuery(name = "Price.getByDay", query = "select p from Price p WHERE p.date between :date AND :date"),})
public class Price extends EntityModel {

    @NotNull
    private Double price;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    public Price() {
        super();
    }

    public Price(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @PrePersist
    public void prePersistent() {
        this.setDate(new Date());
    }
}
