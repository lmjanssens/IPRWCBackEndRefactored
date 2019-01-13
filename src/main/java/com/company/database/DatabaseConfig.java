package com.company.database;

import java.util.Properties;

public class DatabaseConfig {
    private final String url = "jdbc:postgresql://localhost/iprwc";
    private final String username = "postgres";
    private final String password = "0000";

    public Properties getDatabaseConnectionProperties() {
        Properties properties = new Properties();
        properties.put("user", username);
        properties.put("password", password);

        return properties;
    }

    public String getUrl() { return this.url; }
}
