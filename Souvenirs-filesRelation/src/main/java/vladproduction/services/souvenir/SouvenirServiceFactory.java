package vladproduction.services.souvenir;

import vladproduction.models.Producer;
import vladproduction.services.producer.FileType;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.souvenir.csv.CsvSouvenirService;
import vladproduction.services.souvenir.json.JSONSouvenirService;
import vladproduction.services.souvenir.serial.SerialSouvenirService;
import vladproduction.services.souvenir.xls.XlsSouvenirService;
import vladproduction.services.souvenir.xml.XMLSouvenirService;

public class SouvenirServiceFactory {

    public static SouvenirService getSouvenirService(Producer producer, ProducerService producerService){
        FileType fileType = producerService.findAllProducers().get(producer);

        switch (fileType){
            case JSON : return new JSONSouvenirService(producerService);
            case XML : return new XMLSouvenirService(producerService);
            case JAVA_TXT_SERIALIZE : return new SerialSouvenirService(producerService);
            case CSV : return new CsvSouvenirService(producerService);
            case XLS : return new XlsSouvenirService(producerService);
        }
        return null;
    }
}
