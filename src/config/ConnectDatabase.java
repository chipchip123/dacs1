package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {
    public static Connection getConnection() {
        try {
            // Load driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://CHIPZZZ\\SQLEXPRESS;databaseName=DoAnCs1;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String password = "12345678";

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
