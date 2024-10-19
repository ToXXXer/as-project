package com.bxrbasov.project.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@UtilityClass
public class ConnectionManager {
    private static String URL = "db.url";
    private static String USERNAME = "db.user";
    private static String PASSWORD = "db.password";
    private static String DRIVER = "db.driver";
    private static BlockingQueue<Connection> connections = new LinkedBlockingQueue<>();

    static {
        loadDriver();
        for (int i = 0; i < 20; i++){
            try {
                connections.put(ConnectionManager.openConnection());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SneakyThrows
    public static Connection takeConnection() {
        while (connections.isEmpty()) {
            connections.wait(1000);
        }
        System.out.println(connections.size());
        return connections.take();
    }

    @SneakyThrows
    public static void putConnection(Connection connection) {
        connections.put(connection);
    }

    @SneakyThrows
    private static void loadDriver() {
            Class.forName(PropertiesUtil.get(DRIVER));
    }

    @SneakyThrows
    public static Connection openConnection() {
        return DriverManager.getConnection(PropertiesUtil.get(URL), PropertiesUtil.get(USERNAME), PropertiesUtil.get(PASSWORD));
    }
}
