package com.dbUtils.sql;

import org.apache.kafka.common.protocol.types.Field;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlHandler implements ISqlHandler{
    private  String url;
    private  String username;
    private  String password;
    Statement statement;

    public  void setDBUtilsProperty(String url,String username,String password){
        this.url = url;
        this.username = username;
        this.password= password;
    }
    public Connection getConnection() throws SQLException {
         return DriverManager.getConnection(this.url,this.username,this.password);
    }
    public Statement createStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public Statement prepareStatement(String query) throws SQLException {
        return  getConnection().prepareStatement(query);
    }
}
