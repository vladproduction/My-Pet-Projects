package vladproduction.models;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Set;

@XmlRootElement
public class Souvenirs implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<Souvenir> souvenirs;

    public Set<Souvenir> getSouvenirs() {
        return souvenirs;
    }

    public void setSouvenirs(Set<Souvenir> souvenirs) {
        this.souvenirs = souvenirs;
    }

}
