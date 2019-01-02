package com.company.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private DatabaseConfig databaseConfig;

    public Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(databaseConfig.getUrl(),
                             databaseConfig.getDatabaseConnectionProperties());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
