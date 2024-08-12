package com.dbUtils.sql;

import com.configuration.utils.PropertiesUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHandler implements ISqlHandler{
    private  String url;
    private  String username;
    private  String password;

    public  void setDBUtilsProperty(String url,String username,String password){
        this.url = url;
        this.username = username;
        this.password= password;
    }
    public Connection getConnection() throws SQLException {
         return DriverManager.getConnection(this.url,this.username,this.password);
    }
}
