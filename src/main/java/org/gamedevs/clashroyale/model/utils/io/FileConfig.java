package org.gamedevs.clashroyale.model.utils.io;

import java.io.*;
import java.util.Properties;

/**
 * This class reads and writes on config file!
 * Used to save game property!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class FileConfig {

    /**
     * Path of directory of config file.
     */
    private final String DIR = "./config/";
    /**
     * Config file name
     */
    private final String CONFIG_FILE_NAME = "config.properties";

    /**
     * This value is set to current account property
     */
    public final static String CURRENT_ACCOUNT = "CURRENT_ACCOUNT";

    /**
     * Constructor
     */
    public FileConfig(){}

    /**
     * Write information to property file
     * @param propertyName name of property
     * @param value value of property will be set
     * @return true if it could write successfully
     */
    public boolean write(String propertyName, String value){
        if(propertyName == null)
            return false;
        if(value == null)
            value = "";
        try {
            OutputStream outputStream = new FileOutputStream(DIR + CONFIG_FILE_NAME);
            Properties properties = new Properties();
            // set the properties value
            properties.setProperty(propertyName, value);
            // save properties to project root folder
            properties.store(outputStream, null);
            return true;
        } catch (FileNotFoundException e){
            create();
        } catch (IOException ignored){}
        return false;
    }

    /**
     * Reads from property file
     * @param propertyName name of property
     * @return string if found else will be null
     */
    public String read(String propertyName){
        if(propertyName == null)
            return null;
        try {
            InputStream inputStream = new FileInputStream(DIR + CONFIG_FILE_NAME);
            // load a properties file
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(propertyName);
        } catch (FileNotFoundException e) {
            create();
        }catch (IOException ignored){}
        return null;
    }

    /**
     * Creates a new property file
     */
    private void create(){
        try {
            File propFile = new File(DIR + CONFIG_FILE_NAME);
            propFile.createNewFile();
        }catch (IOException ignored){}
    }

}
