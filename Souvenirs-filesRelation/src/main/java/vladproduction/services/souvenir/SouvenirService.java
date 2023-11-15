package vladproduction.services.souvenir;

import vladproduction.models.Producer;
import vladproduction.models.Souvenir;

import java.util.Set;

public interface SouvenirService {

    public void add(Souvenir souvenir);
    public void remove(Souvenir souvenir);
    public void removeAll(Producer producer);
    public void update(Souvenir currentSouvenir, Souvenir candidateSouvenir);
    public Set<Souvenir> findAllSouvenirsByProducer(Producer producer);
}
