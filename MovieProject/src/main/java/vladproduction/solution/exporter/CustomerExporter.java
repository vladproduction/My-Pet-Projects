package vladproduction.solution.exporter;


import vladproduction.solution.model.MyCustomer;

//factory Pattern
public interface CustomerExporter {

    public String export(MyCustomer myCustomer);
}
