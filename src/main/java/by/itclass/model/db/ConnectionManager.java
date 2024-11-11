package by.itclass.model.db;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/2309_store";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection cn;

    @SneakyThrows
    public static void init() {
        Class.forName(DRIVER);
    }

    public static Connection getConnection() throws SQLException {
        if (cn == null || cn.isClosed()) {
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return cn;
    }
}
