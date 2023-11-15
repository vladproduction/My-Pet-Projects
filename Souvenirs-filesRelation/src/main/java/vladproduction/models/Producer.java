package vladproduction.models;

import java.io.Serializable;
import java.util.Objects;


public class Producer implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String name;
    private  String country;

    public Producer(ProducerBuilder producerBuilder) {
        this.name = producerBuilder.name;
        this.country = producerBuilder.country;
    }

    public Producer() {
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(name, producer.name) && Objects.equals(country, producer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static class ProducerBuilder{

        private String name;
        private String country;

        public ProducerBuilder name(String name){
            this.name = name;
            return this;
        }
        public ProducerBuilder country(String country){
            this.country = country;
            return this;
        }

        public Producer build(){
            return new Producer(this);
        }

    }
}
