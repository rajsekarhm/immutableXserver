package com.persistence.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISqlHandler {
    public Connection getConnection() throws SQLException;
}
