package com.dependencies.utils;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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

    public String resolvePath(String... args){
        if (args.length == 0) {
            throw new IllegalArgumentException("At least one path argument is required");
        }
        return  Paths.get(args[0],Arrays.copyOfRange(args,1,args.length)).toAbsolutePath().toString();
    }

    public  String getRootDirectoryOfProject(){
        return  System.getProperty("user.dir");
    }
}
