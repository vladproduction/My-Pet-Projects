package vladproduction.solution.exporter;

public class CustomerExporterFactory {

    public static CustomerExporter getCustomerExporter(CustomerExporterType type){
        if(type==null){
            throw new IllegalArgumentException("Incorrect value CustomerExporterType = "+type);
        }
        switch (type){
            case TEXT : return new TextCustomerExporter();
            case HTML : return new HTMLCustomerExporter();
            default : throw new IllegalArgumentException(
                    "Incorrect value CustomerExporterType = "+type
            );
        }
    }
}
