1)Project was done in accordance with task("КР1.pdf") assignment;
Design Patterns:
    -Builder;
    -Singleton;
    -Factory;
    -Template;
Concurrency:
    -ExecutorService;
    -Callable;
    -Future;
Support of six different file types:
    -properties (for store Producers data);
    -java serialization;
    -json(com.fasterxml.jackson.*);
    -xml(jaxb);
    -csv(com.opencsv);
    -xls(org.apache.poi);
Used collections and Streams.
Implemented tests for different scenarios.

2)models
-Producer:class representing serialization of the objects and unique by 'serialVersionUID';
Builder (creational Pattern) is used for create new instances of Producer;
-Souvenir:also implements Serializable, but ordinary constructor help us to create new object of souvenir;
field creationDate has type Data (java.util.Date).
I decided to create class Utils with:
private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy")
and two methods for working with Date by using this FORMATTER;

3)services package representing two folders:
    -producer: FileType is enum (different type of files), our Producer could work with;
    Define of state Producer were implementing by class ProducerServiceImpl, private methods
    'save' and 'load' respectively save and load by OutputStream/InputStream file(.properties) with our
    Producer/s information;

    -souvenir:as we have several types of file our potential Producers could be working with,
    I decided to implement SouvenirService interface through AbstractSouvenirService abstract class
    where common state of Souvenir implemented by interface methods ;
    but by abstract methods:
    *protected abstract void saveToFile(Set<Souvenir> souvenirs, File file);
    *protected abstract Set<Souvenir> loadFromFile(File file);
    save and load representing in case of FileType by certain package of each type using 'filesystem';
    SouvenirServiceFactory class help us easy to switch among different fileTypes by producerService;
    (there is used Factory Pattern);

4)client.api aggregates logic of all services (souvenir and producer).
It provides convenient api to solve our tasks;
ClientApi implemented as a Singleton (Pattern);

5)Concurrency to speed up the processing of retrieving information about all producers and souvenirs,
executors api is used (please check getInfoAboutAllProducersAndSouvenirs(){...};)
The idea is to have executor service with 5 threads in pool (amount of threads in pool is a place
for negotiation).
And then collect information per each producer in a separate Callable task.
Then combine all Future<String> results into List.
Once all futures will be completed we get all individual results and form result String.

6)Test
TestNG is used for verification.

7)main
Example of api usage
