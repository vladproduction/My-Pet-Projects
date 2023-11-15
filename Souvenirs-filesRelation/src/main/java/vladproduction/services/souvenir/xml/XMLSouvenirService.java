package vladproduction.services.souvenir.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import vladproduction.models.Souvenir;
import vladproduction.models.Souvenirs;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.souvenir.AbstractSouvenirService;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class XMLSouvenirService extends AbstractSouvenirService {

    public XMLSouvenirService(ProducerService producerService) {
        super(producerService);
    }

    @Override
    protected void saveToFile(Set<Souvenir> souvenirs, File file) {
        Souvenirs souvenirsSet = new Souvenirs();
        souvenirsSet.setSouvenirs(souvenirs);
        try {
                JAXBContext context = JAXBContext.newInstance(Souvenirs.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.marshal(souvenirsSet, file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected Set<Souvenir> loadFromFile(File file) {

        try {
            if (!file.exists()) {
                return new HashSet<>();
            }
            JAXBContext context = JAXBContext.newInstance(Souvenirs.class);
            Unmarshaller um = context.createUnmarshaller();
            Souvenirs souvenirs = (Souvenirs) um.unmarshal(file);
            return souvenirs.getSouvenirs();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}

