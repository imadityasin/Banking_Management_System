// DatabaseConnection.java
import java.sql.*;

public class DatabaseConnection {
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Banking_Management"; // Enter your DB name
        String user = "root"; // Enter your DB user
        String password = "Adityahindustantimes@123"; // Enter your DB password
        return DriverManager.getConnection(url, user, password);
    }
}