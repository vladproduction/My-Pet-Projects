package vladproduction.client.api;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import vladproduction.models.Producer;
import vladproduction.models.Souvenir;
import vladproduction.services.producer.FileType;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.souvenir.SouvenirService;
import vladproduction.services.souvenir.Utils;

import java.io.File;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;

public class ClientApiTest {

    @BeforeTest
    public void setUp(){
        cleanUp();
    }

    @Test
    public void producerTest(){
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


        //allSouvenirsByProducer:
        Set<Souvenir> allSouvenirsByReal = clientApi.findAllSouvenirsByProducer(real);
        assertSame(allSouvenirsByReal.size(), 3);
        Set<Souvenir> allSouvenirsByDynamo = clientApi.findAllSouvenirsByProducer(dynamo);
        assertSame(allSouvenirsByDynamo.size(), 1);

        //allSouvenirsByCountry:
        assertSame(clientApi.findAllSouvenirsByCountry("UKR").size(), 1);
        assertSame(clientApi.findAllSouvenirsByCountry("ENG").size(), 2);
        assertSame(clientApi.findAllSouvenirsByCountry("SP").size(), 3);
        assertSame(clientApi.findAllSouvenirsByCountry("GER").size(), 2);
        assertSame(clientApi.findAllSouvenirsByCountry("NTH").size(), 2);

        //infoByLessPrice:
        assertSame(clientApi.findAllProducersByLessPrice(1.0).size(), 0);
        assertSame(clientApi.findAllProducersByLessPrice(2000.0).size(), 5);
        assertSame(clientApi.findAllProducersByLessPrice(25.0).size(), 4);

        //infoAboutAllProducersAndSouvenirs:
        String infoAboutAll = clientApi.getInfoAboutAllProducersAndSouvenirs();
        assertNotNull(infoAboutAll);

        //allProducersBySouvenirAndYear
        assertSame(clientApi.findAllProducersBySouvenirAndYear(realCap, 1950).size(), 0);
        assertSame(clientApi.findAllProducersBySouvenirAndYear(realCup, 2020).size(), 1);

        //allSouvenirsByYear:
        assertSame(clientApi.findAllSouvenirsByProducedYear(2019).size(), 1);
        assertSame(clientApi.findAllSouvenirsByProducedYear(2020).size(), 2);
        assertSame(clientApi.findAllSouvenirsByProducedYear(2021).size(), 0);
        assertSame(clientApi.findAllSouvenirsByProducedYear(2022).size(), 4);
        assertSame(clientApi.findAllSouvenirsByProducedYear(2023).size(), 3);


    }
    private void cleanUp(){
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
    @AfterTest
    public void afterTest(){
        cleanUp();
    }
}
