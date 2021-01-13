package com.mavrenkov.utils;

import org.apache.commons.compress.utils.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
// to avoid hardcoding values in a long run - I will use ConfigurationReader and .properties file
public class ConfigurationReader {
    //prevents any other class from instantiating
    private ConfigurationReader(){}

    //The Properties class represents a persistent set of properties. which can be saved to a stream or loaded from it.
    // Additionally, the key and the corresponding value in the property list is a string.
    private static Properties properties = new Properties();
    //Create a new FileInputStream containing file-name, to be read from.
    private static InputStream in;

    static {
        try {
            in = new FileInputStream("configuration.properties");
            properties.load(in); // It reads a property list of key and element pairs from the input character stream in a simple line-oriented format.
        } catch (IOException e) {
            System.out.println("Error while reading properties file! "+ e.getMessage());
            e.printStackTrace();
        }finally {
            if (in != null) {
                IOUtils.closeQuietly(in);
            }
        }

    }
    /**
     * Getter that will
     * @param key - take a parameter (key) and will
     * @return - return its corresponding value as a String
     */
    public static String getProperty(String key){
        return properties.getProperty(key);
    }


}
