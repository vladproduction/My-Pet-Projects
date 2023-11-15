package vladproduction.services.souvenir.serial;

import vladproduction.models.Souvenir;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.souvenir.AbstractSouvenirService;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SerialSouvenirService extends AbstractSouvenirService {

    private ProducerService producerService;

    public SerialSouvenirService(ProducerService producerService) {
        super(producerService);
    }

    @Override
    protected void saveToFile(Set<Souvenir> souvenirs, File file) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(souvenirs);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Set<Souvenir> loadFromFile(File file) {
        if(!file.exists()){
            return new HashSet<>();
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            Object o = in.readObject();
            return (Set<Souvenir>) o;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
