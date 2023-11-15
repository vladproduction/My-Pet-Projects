package vladproduction.solution.exporter;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CustomerExporterFactoryTest {

    @Test
    public void givesHTMLExporterPositive(){
        CustomerExporter customerExporter = CustomerExporterFactory.getCustomerExporter(CustomerExporterType.HTML);
        assertTrue(customerExporter.getClass()==HTMLCustomerExporter.class);
    }

    @Test
    public void givesTextExporterPositive(){
        CustomerExporter customerExporter = CustomerExporterFactory.getCustomerExporter(CustomerExporterType.TEXT);
        assertTrue(customerExporter.getClass()==TextCustomerExporter.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givesException(){
        CustomerExporterFactory.getCustomerExporter(null);

    }

}
