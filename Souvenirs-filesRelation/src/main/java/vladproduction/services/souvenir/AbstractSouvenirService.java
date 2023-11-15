package vladproduction.services.souvenir;

import vladproduction.models.Producer;
import vladproduction.models.Souvenir;
import vladproduction.services.producer.FileType;
import vladproduction.services.producer.ProducerService;

import java.io.File;
import java.util.Map;
import java.util.Set;

public abstract class AbstractSouvenirService implements SouvenirService{

    private ProducerService producerService;

    public AbstractSouvenirService(ProducerService producerService) {
        this.producerService = producerService;
    }

    protected abstract void saveToFile(Set<Souvenir> souvenirs, File file);
    protected abstract Set<Souvenir> loadFromFile(File file);

    @Override
    public void add(Souvenir souvenir) {
        String filePath = findDataSourceForSouvenirByProducer(souvenir);
        File file = new File(filePath);
        Set<Souvenir> souvenirsSet = loadFromFile(file);
        souvenirsSet.add(souvenir);
        saveToFile(souvenirsSet, file);
    }

    @Override
    public void remove(Souvenir souvenir) {
        String filePath = findDataSourceForSouvenirByProducer(souvenir);
        File file = new File(filePath);
        Set<Souvenir> souvenirsSet = loadFromFile(file);
        souvenirsSet.remove(souvenir);
        saveToFile(souvenirsSet, file);
    }

    @Override
    public void removeAll(Producer producer) {
        Map<Producer, FileType> allProducers = producerService.findAllProducers();
        FileType fileType = allProducers.get(producer);
        String fileName = producer.getName()+"-"+producer.getCountry()+"."+fileType.name().toLowerCase();
        File file = new File(fileName);
        file.delete();
    }

    @Override
    public void update(Souvenir currentSouvenir, Souvenir candidateSouvenir) {
        String filePath = findDataSourceForSouvenirByProducer(currentSouvenir);
        File file = new File(filePath);
        Set<Souvenir> souvenirsSet = loadFromFile(file);
        souvenirsSet.remove(currentSouvenir);
        souvenirsSet.add(candidateSouvenir);
        saveToFile(souvenirsSet, file);
    }


    @Override
    public Set<Souvenir> findAllSouvenirsByProducer(Producer producer) {
        Map<Producer, FileType> allProducers = producerService.findAllProducers();
        FileType fileType = allProducers.get(producer);
        String fileName = producer.getName()+"-"+producer.getCountry()+"."+fileType.name().toLowerCase();
        return loadFromFile(new File(fileName));
    }

    private String findDataSourceForSouvenirByProducer(Souvenir souvenir){
        Producer producer = souvenir.getProducer();
        Map<Producer, FileType> allProducers = producerService.findAllProducers();
        FileType fileType = allProducers.get(producer);
        return producer.getName()+"-"+producer.getCountry()+"."+fileType.name().toLowerCase();

    }
}
