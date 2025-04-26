/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package knims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WISE
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/knims"; // Change to your actual DB
    private static final String USER = "root"; // Change if necessary
    private static final String PASSWORD = "Wi0797563115#"; // Change if necessary

    public static Connection connectToDatabase() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed!");
        }
    }
}
