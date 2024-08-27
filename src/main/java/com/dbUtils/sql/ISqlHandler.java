package com.dbUtils.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISqlHandler {
    public Connection getConnection() throws SQLException;
}
