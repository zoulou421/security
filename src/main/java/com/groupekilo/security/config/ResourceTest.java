package com.groupekilo.security.config;

import java.io.InputStream;

public class ResourceTest {
    public static void main(String[] args) {
        InputStream inputStream = ResourceTest.class.getClassLoader().getResourceAsStream("database.properties");
        if (inputStream == null) {
            System.err.println("The file 'database.properties' was not found.");
        } else {
            System.out.println("The file 'database.properties' was found.");
        }
    }
}

