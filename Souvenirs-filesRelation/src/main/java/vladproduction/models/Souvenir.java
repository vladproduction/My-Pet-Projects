package vladproduction.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Souvenir implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Date creationDate;
    private double price;
    private Producer producer;

    public Souvenir() {
    }

    public Souvenir(String name, Date creationDate, double price, Producer producer) {
        this.name = name;
        this.creationDate = creationDate;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Souvenir souvenir = (Souvenir) o;
        return Double.compare(souvenir.price, price) == 0 && Objects.equals(name, souvenir.name) && Objects.equals(creationDate, souvenir.creationDate) && Objects.equals(producer, souvenir.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creationDate, price, producer);
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", producer=" + producer +
                '}';
    }
}
