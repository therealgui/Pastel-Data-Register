package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public final class PropertiesLoader {

    public static File loadFile(String filepath) {
        File f = new File(filepath);
        if (f.exists()) {
            return f;
        }
        //Main.LOGGER.log(Level.INFO, "File doesnt't exists");
        return null;
    }

    public static File loadFileFromResources(String filePath) {
        //get file from resources package
        ClassLoader classLoader = PropertiesLoader.class.getClassLoader();
        URL url = classLoader.getResource(filePath);

        if(url != null){
            String s = classLoader.getResource(filePath).getFile();
            return loadFile(s);
        }

        return null;
    }

    public static Properties getProperties(String propFile) {
        InputStream input = null;
        Properties prop = null;

        try{
            prop = new Properties();

            //Get properties file
            File file = loadFileFromResources(propFile);

            if(file == null){
                return null;
            }

            input = new FileInputStream(file);

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
