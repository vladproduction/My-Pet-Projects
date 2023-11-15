package vladproduction.services.souvenir.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import vladproduction.models.Souvenir;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.souvenir.AbstractSouvenirService;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JSONSouvenirService extends AbstractSouvenirService {
    private ObjectMapper mapper = new ObjectMapper();

    public JSONSouvenirService(ProducerService producerService) {
        super(producerService);
    }

    @Override
    protected void saveToFile(Set<Souvenir> souvenirs, File file) {
        try {
            mapper.writeValue(file, souvenirs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Set<Souvenir> loadFromFile(File file) {
        try {
            if(!file.exists()){
                return new HashSet<>();
            }
            Souvenir[] souvenirsArray = mapper.readValue(file, Souvenir[].class);
            Set<Souvenir> souvenirsSet = new HashSet<>();
            for (Souvenir souvenir : souvenirsArray) {
                souvenirsSet.add(souvenir);
            }
            return souvenirsSet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
