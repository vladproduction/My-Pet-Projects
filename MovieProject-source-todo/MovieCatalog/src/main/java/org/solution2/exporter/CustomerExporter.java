package org.solution2.exporter;

import org.solution2.MyCustomer;


//factory Pattern
public interface CustomerExporter {

    public String export(MyCustomer myCustomer);
}
