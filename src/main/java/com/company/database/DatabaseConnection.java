package com.company.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DatabaseConfig.getUrl(),
                        DatabaseConfig.getDatabaseConnectionProperties());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
