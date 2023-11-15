package vladproduction.services.souvenir.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import vladproduction.models.Producer;
import vladproduction.models.Souvenir;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.souvenir.AbstractSouvenirService;
import vladproduction.services.souvenir.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CsvSouvenirService extends AbstractSouvenirService {



    public CsvSouvenirService(ProducerService producerService) {
        super(producerService);

    }

    @Override
    protected void saveToFile(Set<Souvenir> souvenirs, File file) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            List<String []> content = souvenirs.stream()
                    .map(souvenir -> makeContentFromSouvenir(souvenir))
                    .collect(Collectors.toList());
            writer.writeAll(content);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    @Override
    protected Set<Souvenir> loadFromFile(File file) {
        if(!file.exists()){
            return new HashSet<>();
        }
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            return reader.readAll().stream().map((String [] content)-> makeSouvenir(content))
                    .collect(Collectors.toSet());
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }

    private Souvenir makeSouvenir(String [] content){
        Souvenir souvenir = new Souvenir();
        souvenir.setName(content[0]);
        souvenir.setPrice(Double.parseDouble(content[1]));
        souvenir.setCreationDate(Utils.createDateFromString(content[2]));
        Producer producer = new Producer.ProducerBuilder()
                .name(content[3]).country(content[4]).build();

        souvenir.setProducer(producer);
        return souvenir;
    }

    private String[] makeContentFromSouvenir(Souvenir souvenir){
        String[] content = new String[5];
        content[0] = souvenir.getName();
        content[1] = souvenir.getPrice()+"";
        content[2] = Utils.createStringFromDate(souvenir.getCreationDate());
        Producer souvenirProducer = souvenir.getProducer();
        if(souvenirProducer!=null){
            content[3] = souvenirProducer.getName();
            content[4] = souvenirProducer.getCountry();
        }
        return content;
    }
}
