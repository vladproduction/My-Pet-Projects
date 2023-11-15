package vladproduction.services.producer;

import vladproduction.models.Producer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ProducerServiceImpl implements ProducerService {

    private final File PRODUCER_DATA_SOURCE_FILE = new File("producers.properties");
    @Override
    public boolean add(Producer producer, FileType fileType) {

        Properties properties = load(PRODUCER_DATA_SOURCE_FILE);

        String producersAmountValue = properties.getProperty("Producers.amount", "0");
        int producersAmount = Integer.parseInt(producersAmountValue);
        for (int i = 1; i <= producersAmount; i++) {
            String name = properties.getProperty("producer." + i + ".name");
            String country = properties.getProperty("producer." + i + ".country");


            Producer existingProducer = new Producer.ProducerBuilder()
                    .name(name).country(country).build();


            if(existingProducer.equals(producer)){
                System.out.println("Producer already exist!");
                return false;
            }

        }
        producersAmount++;
        properties.put("producer." + producersAmount + ".name", producer.getName());
        properties.put("producer." + producersAmount + ".country", producer.getCountry());
        properties.put("producer." + producersAmount + ".fileType", fileType.name());
        properties.put("Producers.amount", producersAmount+"");

        save(properties,PRODUCER_DATA_SOURCE_FILE);
        return true;

    }

    @Override
    public void remove(Producer producer) {
        Properties properties = load(PRODUCER_DATA_SOURCE_FILE);

        String producersAmountValue = properties.getProperty("Producers.amount", "0");
        int producersAmount = Integer.parseInt(producersAmountValue);
        for (int i = 1; i <= producersAmount; i++) {
            String name = properties.getProperty("producer." + i + ".name");
            String country = properties.getProperty("producer." + i + ".country");


            Producer existingProducer = new Producer.ProducerBuilder()
                    .name(name).country(country).build();


            if(existingProducer.equals(producer)){

                String nameOfLast = properties.getProperty("producer." + producersAmount + ".name");
                String countryOfLast = properties.getProperty("producer." + producersAmount + ".country");
                String fileTypeOfLast = properties.getProperty("producer." + producersAmount + ".fileType");

                properties.put("producer." +i+ ".name", nameOfLast);
                properties.put("producer." +i+ ".country", countryOfLast);
                properties.put("producer." +i+ ".fileType", fileTypeOfLast);

                properties.remove("producer." + producersAmount + ".name");
                properties.remove("producer." + producersAmount + ".country");
                properties.remove("producer." + producersAmount + ".fileType");

                producersAmount--;
                properties.put("Producers.amount", producersAmount+"");
                break;
            }

        }
        save(properties, PRODUCER_DATA_SOURCE_FILE);

    }

    @Override
    public void update(Producer currentProducer, Producer candidateProducer, FileType fileType) {
        Properties properties = load(PRODUCER_DATA_SOURCE_FILE);

        String producersAmountValue = properties.getProperty("Producers.amount", "0");
        int producersAmount = Integer.parseInt(producersAmountValue);
        for (int i = 1; i <= producersAmount; i++) {
            String name = properties.getProperty("producer." + i + ".name");
            String country = properties.getProperty("producer." + i + ".country");


            Producer existingProducer = new Producer.ProducerBuilder()
                    .name(name).country(country).build();


            if(existingProducer.equals(currentProducer)){
                properties.put("producer." +i+ ".name", candidateProducer.getName());
                properties.put("producer." +i+ ".country", candidateProducer.getCountry());
                properties.put("producer." +i+ ".fileType", fileType.name());
                break;
            }

        }
        save(properties, PRODUCER_DATA_SOURCE_FILE);

    }

    @Override
    public Map<Producer, FileType> findAllProducers() {
        Properties properties = load(PRODUCER_DATA_SOURCE_FILE);
        Map<Producer,FileType> result = new HashMap<>();
        String producersAmountValue = properties.getProperty("Producers.amount", "0");
        int producersAmount = Integer.parseInt(producersAmountValue);
        for (int i = 1; i <= producersAmount; i++) {
            String name = properties.getProperty("producer." + i + ".name");
            String country = properties.getProperty("producer." + i + ".country");
            String fileTypeName = properties.getProperty("producer." + i + ".fileType");
            FileType fileType = FileType.valueOf(fileTypeName);
            Producer producer = new Producer.ProducerBuilder()
                    .name(name).country(country).build();
            result.put(producer,fileType);
        }

        return result;
    }

    //load and save (file content):
    private Properties load(File file){
        Properties properties = new Properties();
        if (file.exists()) {
            try (InputStream in = new FileInputStream(file)) {
                properties.load(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }

    private void save(Properties properties, File file){
        try (OutputStream out = new FileOutputStream(file);) {
            properties.store(out, "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


