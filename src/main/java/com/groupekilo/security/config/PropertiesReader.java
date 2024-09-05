package com.groupekilo.security.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;

    public PropertiesReader(String propertyFileName) throws IOException {
        this.properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFileName)) {
            if (is == null) {
                throw new IOException("Property file '" + propertyFileName + "' not found in the classpath");
            }
            this.properties.load(is);
        } catch (IOException e) {
            System.err.println("Failed to load properties from file: " + e.getMessage());
            throw e; // Rethrow the exception to notify caller
        }
    }

    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
}
