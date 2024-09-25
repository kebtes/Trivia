package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=TRIVIA;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "trivia_user"; 
    private static final String PASSWORD = "trivia"; 

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}