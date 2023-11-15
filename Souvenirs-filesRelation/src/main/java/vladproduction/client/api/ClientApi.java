package vladproduction.client.api;

import vladproduction.models.Producer;
import vladproduction.models.Souvenir;
import vladproduction.services.producer.FileType;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.producer.ProducerServiceImpl;
import vladproduction.services.souvenir.SouvenirService;
import vladproduction.services.souvenir.SouvenirServiceFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ClientApi {

    private static final ClientApi INSTANCE = new ClientApi();
    private ProducerService producerService;

    private ExecutorService executorService;
    private ClientApi(){
        producerService = new ProducerServiceImpl();
        executorService= Executors.newFixedThreadPool(5);
    }
    public static ClientApi getInstance(){
        return INSTANCE;
    }

    public Set<Souvenir> findAllSouvenirsByProducer(Producer producer){
        SouvenirService souvenirService = SouvenirServiceFactory.getSouvenirService(producer, producerService);
        Set<Souvenir> allSouvenirsByProducer = souvenirService.findAllSouvenirsByProducer(producer);
        return allSouvenirsByProducer;
    }
    public Set<Souvenir> findAllSouvenirsByCountry(String country){
        Set<Souvenir> result = new HashSet<>();
        List<Producer> producerList = producerService.findAllProducers().keySet()
                .stream().filter(producer -> producer.getCountry().equals(country))
                .collect(Collectors.toList());
        for (Producer producer : producerList) {
            Set<Souvenir> allSouvenirsByProducer = findAllSouvenirsByProducer(producer);
            result.addAll(allSouvenirsByProducer);
        }
        return result;
    }

    public Set<Producer> findAllProducersByLessPrice(double price){
        return producerService.findAllProducers().keySet()
                .stream().filter(producer -> isProducerLessPrice(price,producer))
                .collect(Collectors.toSet());
    }

    public String getInfoAboutAllProducersAndSouvenirs(){
        StringBuilder sb = new StringBuilder();
        Map<Producer, FileType> allProducers = producerService.findAllProducers();
        Set<Producer> keys = allProducers.keySet();
        List<Future<String>> futureTaskResultList = new ArrayList<>();
        for (Producer key : keys) {
            Callable<String> task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(key.toString());
                    sb2.append("\n");
                    FileType fileType = allProducers.get(key);
                    sb2.append("fileType: ");
                    sb2.append(fileType);
                    Set<Souvenir> allSouvenirsByProducer = findAllSouvenirsByProducer(key);
                    sb2.append("\n############\n");
                    for (Souvenir souvenir : allSouvenirsByProducer) {
                        sb2.append(souvenir.toString());
                        sb2.append("\n");
                    }
                    sb2.append("\n-----------\n");
                    return sb2.toString();
                }
            };
            Future<String> futureTaskResult = executorService.submit(task);
            futureTaskResultList.add(futureTaskResult);
        }
        for (Future<String> stringFuture : futureTaskResultList) {
            try {
                String  result = stringFuture.get();
                sb.append(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sb.toString();
    }

    public Set<Producer> findAllProducersBySouvenirAndYear(Souvenir souvenir, int year){
      return producerService.findAllProducers().keySet()
              .stream().filter(producer -> hasProducerSouvenirsByYear(producer,souvenir.getName(),year))
              .collect(Collectors.toSet());
    }

    public Set<Souvenir> findAllSouvenirsByProducedYear(int year){
        Set<Souvenir> result = new HashSet<>();
        Map<Producer, FileType> allProducers = producerService.findAllProducers();
        Set<Producer> keys = allProducers.keySet();
        for (Producer key : keys) {
            findAllSouvenirsByProducer(key).stream()
                    .filter(souvenir -> isProducedInYear(souvenir,year))
                    .forEach(souvenir -> result.add(souvenir));
        }
        return result;
    }

    public void removeProducerData(Producer producer){
        SouvenirService souvenirService = SouvenirServiceFactory.getSouvenirService(producer, producerService);
        souvenirService.removeAll(producer);
        producerService.remove(producer);

    }

    public SouvenirService getSouvenirServiceByProducer(Producer producer){
        return SouvenirServiceFactory.getSouvenirService(producer, producerService);
    }

    public void shutdown(){
        executorService.shutdown();
    }

    //private methods:----------------------
    private boolean isProducerLessPrice(double price, Producer producer){
        Set<Souvenir> allSouvenirsByProducer = findAllSouvenirsByProducer(producer);
        for (Souvenir souvenir : allSouvenirsByProducer) {
            if(souvenir.getPrice() <= price){
                return true;
            }
        }
        return false;
    }

    private boolean hasProducerSouvenirsByYear(Producer producer, String souvenirName, int year){
        Set<Souvenir> allSouvenirsByProducer = findAllSouvenirsByProducer(producer);
        for (Souvenir souvenir : allSouvenirsByProducer) {
            if(souvenir.getName().equals(souvenirName)){
                int producedYear = findProducedYear(souvenir);
                if(producedYear == year){
                    return true;
                }
            }
        }
        return false;
    }

    private int findProducedYear(Souvenir souvenir){
        Date date = souvenir.getCreationDate();
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDate.ofInstant(instant, ZoneId.of("UTC")).getYear();
    }

    private boolean isProducedInYear(Souvenir souvenir, int year){
        int producedYear = findProducedYear(souvenir);
        return producedYear == year;
    }

    public ProducerService getProducer(){
        return producerService;
    }
}
