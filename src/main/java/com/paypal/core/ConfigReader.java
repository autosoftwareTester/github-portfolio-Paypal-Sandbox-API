package com.paypal.core;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key, int defaultValue) {
        String v = props.getProperty(key);
        if (v == null) return defaultValue;
        return Integer.parseInt(v);
    }
}
