package com.company.database;

import java.util.Properties;

public class DatabaseConfig {
    static Properties getDatabaseConnectionProperties() {
        Properties properties = new Properties();
        properties.put("user", "doadmin");
        properties.put("password", "jbky7no07e3fb7r7");

        return properties;
    }

    static String getUrl() {
        return "jdbc:postgresql://iprwc-do-user-6980273-0.db.ondigitalocean.com:25060/defaultdb";
    }
}
