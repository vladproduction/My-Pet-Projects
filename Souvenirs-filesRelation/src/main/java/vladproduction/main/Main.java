package vladproduction.main;

import vladproduction.client.api.ClientApi;
import vladproduction.models.Producer;
import vladproduction.models.Souvenir;
import vladproduction.services.producer.FileType;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.souvenir.SouvenirService;
import vladproduction.services.souvenir.Utils;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        //to delete old files:
        cleanUp();

        //making content:
        initProducers();
        initSouvenirs();

        //working with api:

        //allSouvenirsByProducer:
        System.out.println("allSouvenirsByProducer:");
        ClientApi clientApi = ClientApi.getInstance();
        Producer real = new Producer.ProducerBuilder().name("RealMadrid").country("SP").build();
        Set<Souvenir> allSouvenirsByReal = clientApi.findAllSouvenirsByProducer(real);
        System.out.println("allSouvenirsByReal:"+allSouvenirsByReal.size());
        Producer dynamo = new Producer.ProducerBuilder().name("DynamoK").country("UKR").build();
        Set<Souvenir> allSouvenirsByDynamo = clientApi.findAllSouvenirsByProducer(dynamo);
        System.out.println("allSouvenirsByDynamo:"+allSouvenirsByDynamo.size());

        //allSouvenirsByCountry:
        System.out.println("allSouvenirsByCountry:");
        System.out.println("Ukraine = "+clientApi.findAllSouvenirsByCountry("UKR").size());
        System.out.println("England = "+clientApi.findAllSouvenirsByCountry("ENG").size());
        System.out.println("Spaine = "+clientApi.findAllSouvenirsByCountry("SP").size());
        System.out.println("Germany = "+clientApi.findAllSouvenirsByCountry("GER").size());
        System.out.println("Holland = "+clientApi.findAllSouvenirsByCountry("NTH").size());

        //infoByLessPrice:
        System.out.println("infoByLessPrice:");
        System.out.println("Less 1.0 = "+clientApi.findAllProducersByLessPrice(1.0).size());
        System.out.println("Less 2000.0 = "+clientApi.findAllProducersByLessPrice(2000.0).size());
        System.out.println("Less 25.0 = "+clientApi.findAllProducersByLessPrice(25.0).size());


        //allProducersBySouvenirAndYear:
        System.out.println("allProducersBySouvenirAndYear:");
        Souvenir realCap = new Souvenir
                ("realCap", Utils.createDateFromString("10-06-2019"), 10.99, real);
        Souvenir realCup = new Souvenir
                ("realCup", Utils.createDateFromString("16-06-2020"), 50.99, real);
        System.out.println("amount souvenirs by year = "+clientApi.findAllProducersBySouvenirAndYear(realCap, 1950).size());
        System.out.println("amount souvenirs by year = "+clientApi.findAllProducersBySouvenirAndYear(realCup, 2020).size());

        //allSouvenirsByYear:
        System.out.println("allSouvenirsByYear:");
        System.out.println("souvenirs produced in 2019: "+clientApi.findAllSouvenirsByProducedYear(2019).size());
        System.out.println("souvenirs produced in 2020: "+clientApi.findAllSouvenirsByProducedYear(2020).size());
        System.out.println("souvenirs produced in 2021: "+clientApi.findAllSouvenirsByProducedYear(2021).size());
        System.out.println("souvenirs produced in 2022: "+clientApi.findAllSouvenirsByProducedYear(2022).size());
        System.out.println("souvenirs produced in 2023: "+clientApi.findAllSouvenirsByProducedYear(2023).size());

        //infoAboutAllProducersAndSouvenirs:
        System.out.println("infoAboutAllProducersAndSouvenirs:");
        String infoAboutAll = clientApi.getInfoAboutAllProducersAndSouvenirs();
        System.out.println(infoAboutAll);

        //after work: so the executors stop;
        clientApi.shutdown();

    }
    private static void initProducers(){
        ClientApi clientApi = ClientApi.getInstance();
        ProducerService producerService = clientApi.getProducer();

        Producer real = new Producer.ProducerBuilder().name("RealMadrid").country("SP").build();
        producerService.add(real, FileType.JSON);
        Producer mu = new Producer.ProducerBuilder().name("MU").country("ENG").build();
        producerService.add(mu, FileType.XML);
        Producer bayern = new Producer.ProducerBuilder().name("Bayern").country("GER").build();
        producerService.add(bayern, FileType.CSV);
        Producer ajax = new Producer.ProducerBuilder().name("AjaxA").country("NTH").build();
        producerService.add(ajax, FileType.XLS);
        Producer dynamo = new Producer.ProducerBuilder().name("DynamoK").country("UKR").build();
        producerService.add(dynamo, FileType.JAVA_TXT_SERIALIZE);

        Map<Producer, FileType> allProducers = producerService.findAllProducers();
        System.out.println("---all Producers:---");
        System.out.println(allProducers);

        System.out.println("---add existing Producer(but other type of file):---");
        boolean res = producerService.add(real, FileType.JSON);
        System.out.println("res = "+res);
        System.out.println("---all Producers:---");
        allProducers = producerService.findAllProducers();
        System.out.println(allProducers);

        System.out.println("---add existing Producer(but other type of file):---");
        res = producerService.add(real, FileType.CSV);
        System.out.println("res = "+res);
        System.out.println("---all Producers:---");
        allProducers = producerService.findAllProducers();
        System.out.println(allProducers);


        System.out.println("---update existing Producer:---");
        Producer muCandidate = new Producer.ProducerBuilder().name("ManchesterUnited").country("ENG").build();
        producerService.update(mu,muCandidate,FileType.XLS);
        System.out.println("---all Producers:---");
        allProducers = producerService.findAllProducers();
        System.out.println(allProducers);

    }

    private static void initSouvenirs(){
        ClientApi clientApi = ClientApi.getInstance();

        Producer real = new Producer.ProducerBuilder().name("RealMadrid").country("SP").build();
        Producer mu = new Producer.ProducerBuilder().name("ManchesterUnited").country("ENG").build();
        Producer bayern = new Producer.ProducerBuilder().name("Bayern").country("GER").build();
        Producer ajax = new Producer.ProducerBuilder().name("AjaxA").country("NTH").build();
        Producer dynamo = new Producer.ProducerBuilder().name("DynamoK").country("UKR").build();


        SouvenirService souvenirServiceByReal = clientApi.getSouvenirServiceByProducer(real);
        SouvenirService souvenirServiceByMU = clientApi.getSouvenirServiceByProducer(mu);
        SouvenirService souvenirServiceByBayern = clientApi.getSouvenirServiceByProducer(bayern);
        SouvenirService souvenirServiceByAjax = clientApi.getSouvenirServiceByProducer(ajax);
        SouvenirService souvenirServiceByDynamoK = clientApi.getSouvenirServiceByProducer(dynamo);


        Souvenir realBall = new Souvenir
                ("realBall", Utils.createDateFromString("10-06-2023"), 13.99, real);
        Souvenir realCap = new Souvenir
                ("realCap", Utils.createDateFromString("10-06-2019"), 10.99, real);
        Souvenir realCup = new Souvenir
                ("realCup", Utils.createDateFromString("16-06-2020"), 50.99, real);
        souvenirServiceByReal.add(realBall);
        souvenirServiceByReal.add(realCap);
        souvenirServiceByReal.add(realCup);

        Souvenir muBall = new Souvenir
                ("muBall", Utils.createDateFromString("10-06-2022"), 9.99, mu);
        Souvenir muCap = new Souvenir
                ("muCap", Utils.createDateFromString("10-06-2020"), 20.99, mu);
        souvenirServiceByMU.add(muBall);
        souvenirServiceByMU.add(muCap);

        Souvenir bayernBall = new Souvenir
                ("bayernBall", Utils.createDateFromString("09-10-2023"), 11.99, bayern);
        Souvenir bayernCap = new Souvenir
                ("bayernCap", Utils.createDateFromString("23-12-2022"), 20.99, bayern);
        souvenirServiceByBayern.add(bayernBall);
        souvenirServiceByBayern.add(bayernCap);

        Souvenir ajaxBall = new Souvenir
                ("ajaxBall", Utils.createDateFromString("01-03-2022"), 13.99, ajax);
        Souvenir ajaxCap = new Souvenir
                ("ajaxCap", Utils.createDateFromString("10-06-2023"), 11.99, ajax);
        souvenirServiceByAjax.add(ajaxBall);
        souvenirServiceByAjax.add(ajaxCap);

        Souvenir dynamoBall = new Souvenir
                ("dynamoBall", Utils.createDateFromString("11-12-2022"), 30.99, dynamo);
        souvenirServiceByDynamoK.add(dynamoBall);

    }

    private static void cleanUp(){
        File f1 = new File("RealMadrid-SP.json");
        File f2 = new File("MU-ENG.xml");
        File f3 = new File("Bayern-GER.csv");
        File f4 = new File("AjaxA-NTH.xls");
        File f5 = new File("DynamoK-UKR.java_txt_serialize");
        File f6 = new File("ManchesterUnited-ENG.xls");
        File f7 = new File("producers.properties");
        if(f1.exists()){
            f1.delete();
        }
        if(f2.exists()){
            f2.delete();
        }
        if(f3.exists()){
            f3.delete();
        }
        if(f4.exists()){
            f4.delete();
        }
        if(f5.exists()){
            f5.delete();
        }
        if(f6.exists()){
            f6.delete();
        }
        if(f7.exists()){
            f7.delete();
        }
    }
}
