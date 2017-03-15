package entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
    @NamedQuery(name = "Car.getByNumber", query = "select c from Car c WHERE c.number=:number"),  
    @NamedQuery(name = "Car.getAll", query = "select count(*) from Car c"),
    @NamedQuery(name = "Car.checkNumber", query = "select count(*) from Car c where c.number=:number")
})
public class Car extends EntityModel {

    public Car() {
        super();
    }
    @NotNull
    @Pattern(regexp="([а-я]{1})([0-9]{3})([а-я]{2})") 
    private String number;
    @NotNull
    private String model;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date time;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    
    @PrePersist
    public void prePersistent(){
        this.setTime(new Date());
    }
}
