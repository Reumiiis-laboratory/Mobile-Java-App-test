package db.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnects {

    // Singleton instance of the connection
    private static Connection con = null;

    // Private constructor to prevent instantiation
    private dbconnects() {}

    // Public method to get the connection (only one instance will be created)
    public static Connection connectDB() {
        if (con == null) {
            try {
                // Initialize the connection if it's null
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:finals.sqlite");
                System.out.println("Database connection established.");
            } catch (Exception e) {
                System.out.println("Database connection failed: " + e.getMessage());
            }
        }
        return con;
    }

    // Optional: Method to close the connection
    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
