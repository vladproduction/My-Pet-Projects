package vladproduction.client.api;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import vladproduction.models.Producer;
import vladproduction.services.producer.FileType;
import vladproduction.services.producer.ProducerService;

import java.io.File;
import java.util.Map;

import static org.testng.Assert.assertSame;

public class ProducerTest {

    @BeforeTest
    public void setUp(){
        cleanUp();
    }

    @Test
    public void allSouvenirsByProducerTest(){
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
        assertSame(allProducers.size(), 5);

        assertSame(allProducers.get(real), FileType.JSON);
        assertSame(allProducers.get(mu), FileType.XML);
        assertSame(allProducers.get(bayern), FileType.CSV);
        assertSame(allProducers.get(ajax), FileType.XLS);
        assertSame(allProducers.get(dynamo), FileType.JAVA_TXT_SERIALIZE);

        boolean res = producerService.add(real, FileType.JSON);
        assertSame(res, false);

        allProducers = producerService.findAllProducers();
        assertSame(allProducers.size(), 5);

        res = producerService.add(real, FileType.CSV);
        assertSame(res, false);

        allProducers = producerService.findAllProducers();
        assertSame(allProducers.size(), 5);

        producerService.remove(real);
        allProducers = producerService.findAllProducers();
        assertSame(allProducers.size(), 4);

        Producer muCandidate = new Producer.ProducerBuilder().name("ManchesterUnited").country("ENG").build();
        producerService.update(mu,muCandidate,FileType.XLS);
        allProducers = producerService.findAllProducers();
        assertSame(allProducers.size(), 4);
        assertSame(allProducers.get(muCandidate),FileType.XLS);


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
