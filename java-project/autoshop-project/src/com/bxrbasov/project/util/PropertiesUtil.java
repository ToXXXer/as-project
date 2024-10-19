package com.bxrbasov.project.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import java.util.Properties;

@UtilityClass
public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    @SneakyThrows
    private static void loadProperties() {
        var resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
        PROPERTIES.load(resourceAsStream);
    }

    public static String get(String s) {
        return PROPERTIES.getProperty(s);
    }
}
