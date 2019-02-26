package com.company.database;

import java.util.Properties;

public class DatabaseConfig {
    static Properties getDatabaseConnectionProperties() {
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "0000");

        return properties;
    }

    static String getUrl() { return "jdbc:postgresql://localhost/iprwc"; }
}
