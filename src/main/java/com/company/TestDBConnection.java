package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class TestDBConnection {

    public static void main(String[] args) {
        String Url = "jdbc:postgresql://localhost/iprwc";
        try {
            Properties prop = new Properties();
            prop.setProperty("user", "postgres");
            prop.setProperty("password", "0000");
            Class.forName("org.postgresql.Driver");
            System.out.println("Trying to connect");
            Connection connection = DriverManager.getConnection(Url, prop);

            System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
                    + connection.getMetaData().getDatabaseProductName());
        } catch (Exception e) {
            System.out.println("Unable to make connection with DB");
            e.printStackTrace();
        }
    }
}