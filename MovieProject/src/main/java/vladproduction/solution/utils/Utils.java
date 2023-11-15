package vladproduction.solution.utils;

import java.io.InputStream;

public class Utils {

    public static String readContent(String filePath){

        try(InputStream in = Utils.class.getResourceAsStream(filePath)){
            byte [] data = new byte[in.available()];
            in.read(data);
            return new String(data);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
