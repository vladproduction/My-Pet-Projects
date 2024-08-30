package com.example.io;


public class DataProcessFactory {


    public static DataProcess getDataProcess(int typeDataProcess) {

        DataProcess x = null;
        if (typeDataProcess == 0) {
            x = new DataStreamDataProcess();
        }
        if (typeDataProcess == 1) {
            x = new ObjectStreamDataProcess();
        }
        if (typeDataProcess == 2) {
            x = new PropertiesDataProcess();
        }
        if (typeDataProcess == 3) {
            x = new JSONDataProcess();
        }
        if (typeDataProcess == 4) {
            x = new XMLSAXDataProcess();
        }
        return x;
    }
}
