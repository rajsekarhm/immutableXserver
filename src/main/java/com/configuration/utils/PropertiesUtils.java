package com.configuration.utils;
import java.io.*;
import java.util.Properties;
public class PropertiesUtils {
    public Properties properties;
    public InputStream readFile;
    public OutputStream writeFile;
    public PropertiesUtils() {
        this.properties =  new Properties();
    }

    public void readFile(String pathOfFile) {
        try {
            this.readFile = new FileInputStream(pathOfFile);
            this.properties.load(this.readFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void  writeFile(String pathOfFile){
        try{
        this.writeFile = new FileOutputStream(pathOfFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
