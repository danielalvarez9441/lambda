package lambdafunction.repository;

import java.sql.*;

public class ConexionDao {

    public static Connection getConnection(String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD ) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException  e) {
            e.printStackTrace();
            System.out.println("Connection error: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while closing connection: " + e.getMessage());
        }
    }
}
