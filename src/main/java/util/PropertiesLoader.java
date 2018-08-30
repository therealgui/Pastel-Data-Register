package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public final class PropertiesLoader {

    public static Properties loadProperties(String propFile) {
        InputStream input = null;
        Properties prop = null;

        try{
            prop = new Properties();

            input = PropertiesLoader.class.getClassLoader().getResourceAsStream(propFile);

            //load properties file
            prop.load(input);

            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
}
