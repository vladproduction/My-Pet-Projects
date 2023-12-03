package org.solution2.commonUtily;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    public static String getConfigProperty(String key){
        try(InputStream inS = ConfigUtils.class.getResourceAsStream("/app.properties")){
            Properties properties = new Properties();
            properties.load(inS);
            String value = properties.getProperty(key);
            return value;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
