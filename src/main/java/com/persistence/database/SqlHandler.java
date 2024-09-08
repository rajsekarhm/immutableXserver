package com.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlHandler implements ISqlHandler {
    private  String url;
    private  String username;
    private  String password;
    private  String dataBase;
    Statement statement;

    public  void setDBUtilsProperty(String url,String username,String password,String dataBase){
        this.url = url;
        this.username = username;
        this.password= password;
        this.dataBase = dataBase;
    }
    public Connection getConnection() throws SQLException {
         return DriverManager.getConnection(this.url.concat(this.dataBase),this.username,this.password);
    }
    public Statement createStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public Statement prepareStatement(String query) throws SQLException {
        return  getConnection().prepareStatement(query);
    }
}
