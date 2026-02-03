package br.dev.hygino.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConfig {

    private static final String URL
            = "jdbc:mysql://localhost:3306/game_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static final String USER = "root";
    private static final String PASSWORD = "89631139";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL não encontrado", e);
        }
    }

    private MySQLConfig() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
