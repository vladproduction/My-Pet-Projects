package vladproduction.services.producer;

import vladproduction.models.Producer;

import java.util.Map;

public interface ProducerService {

    public boolean add(Producer producer, FileType fileType);
    public void remove(Producer producer);
    public void update(Producer currentProducer, Producer candidateProducer, FileType fileType);
    public Map<Producer, FileType> findAllProducers();
}
