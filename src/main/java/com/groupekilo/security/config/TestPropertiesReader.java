package com.groupekilo.security.config;

import java.io.IOException;

public class TestPropertiesReader {
    public static void main(String[] args) {
        try {
            PropertiesReader reader = new PropertiesReader("database.properties");
            System.out.println("DB URL: " + reader.getProperty("db.urlDev"));
            System.out.println("DB Username: " + reader.getProperty("db.username"));
            System.out.println("DB Password: " + reader.getProperty("db.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

